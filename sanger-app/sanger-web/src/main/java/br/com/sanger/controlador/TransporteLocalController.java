/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.apoio.Estado;
import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.servico.clientes.impl.ClienteService;
import br.com.sanger.servico.funcionarios.impl.AutonomoService;
import br.com.sanger.servico.funcionarios.impl.MotoristaService;
import br.com.sanger.servico.transporte.impl.TransporteLocalService;
import br.com.sanger.servico.transporte.impl.VeiculoDeTransporteService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eugenio
 */
@Controller
public class TransporteLocalController extends GenericController<TransporteLocal> {

    List<Cliente> clientes;

    List<Autonomo> autonomos;

    List<Motorista> motoristas;

    List<VeiculoDeTransporte> veiculos;

    @Autowired
    ServletContext context;

    @Autowired
    HttpServletRequest request;

    public TransporteLocalController() {
        servico = new TransporteLocalService();
        obj = new TransporteLocal();
        entidade = obj.getClass().getSimpleName().toLowerCase();
    }

    @Override
    @RequestMapping( value = "/transportelocal/{id}", method = RequestMethod.GET )
    public String editar( @PathVariable Long id, Model model ) {
        model.addAttribute( "tabIndex", 0 );
        try {
            carregarListas( model );
        } catch ( Exception ex ) {
            Logger.getLogger( TransporteLocalController.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return super.editar( id, model );
    }

    @Override
    @RequestMapping( value = "/transportelocal/excluir/{id}", method = RequestMethod.GET )
    public String excluir( @PathVariable Long id, Model model ) {
        return super.excluir( id, model );
    }

    @Override
    @RequestMapping( value = "/transportelocal/novo" )
    public String formulario( Model model ) {
        model.addAttribute( "tabIndex", 0 );
        try {
            carregarListas( model );
        } catch ( Exception ex ) {
            Logger.getLogger( TransporteLocalController.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return super.formulario( model );
    }

    @Override
    @RequestMapping( value = "/transportelocal/listar" )
    public String listar( Model model ) {
        return super.listar( model );
    }

    @RequestMapping( value = "/transportelocal/salvar", method = RequestMethod.POST, params = { "tabIndex" } )
    @Override
    public String salvar( TransporteLocal obj, @RequestParam String tabIndex, Model model ) {
        model.addAttribute( "tabIndex", tabIndex );
        try {
            carregarListas( model );
            getObjetos( obj );
        } catch ( Exception ex ) {
            Logger.getLogger( TransporteLocalController.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return super.salvar( obj, tabIndex, model );
    }

    private void carregarListas( Model model ) throws Exception {
        clientes = new ClienteService().listar();
        autonomos = new AutonomoService().listar();
        motoristas = new MotoristaService().listar();
        veiculos = new VeiculoDeTransporteService().listar();
        model.addAttribute( "clientes", clientes );
        model.addAttribute( "autonomos", autonomos );
        model.addAttribute( "motoristas", motoristas );
        model.addAttribute( "veiculos", veiculos );
        model.addAttribute( "estados", Estado.values() );
    }

    private void getObjetos( TransporteLocal obj ) throws Exception {
        Cliente cliente = new ClientePessoaFisica();
        cliente.setId( Long.valueOf( request.getParameter( "_cliente" ) ) );
        cliente = (Cliente) getEntidade( cliente, clientes );
        VeiculoDeTransporte veiculo = new VeiculoDeTransporte();
        veiculo.setId( Long.valueOf( request.getParameter( "_veiculoDeTransporte" ) ) );
        veiculo = (VeiculoDeTransporte) getEntidade( veiculo, veiculos );
        obj.setCliente( cliente );
        obj.setVeiculoDeTransporte( veiculo );
    }
}
