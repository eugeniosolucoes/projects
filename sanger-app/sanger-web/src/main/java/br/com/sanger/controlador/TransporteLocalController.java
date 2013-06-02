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
import br.com.sanger.util.MyStrings;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
            obj.setId( id );
            carregarListas( model );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
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
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
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
            tratarModelo( obj );
            servico.salvar( obj );
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_SUCCESS );
            model.addAttribute( "mensagem", "Registro salvo com sucesso!" );
            carregarListas( model );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
        }
        model.addAttribute( entidade, obj );
        return entidade + "/formulario";
    }

    private void carregarListas( Model model ) throws Exception {
        clientes = new ClienteService().listar();
        List<Autonomo> ajudantes = ( (TransporteLocalService) servico ).autonomosNaoAdicionados( obj );
        motoristas = new MotoristaService().listar();
        veiculos = new VeiculoDeTransporteService().listar();
        model.addAttribute( "clientes", clientes );
        model.addAttribute( "ajudantes", ajudantes );
        model.addAttribute( "motoristas", motoristas );
        model.addAttribute( "veiculos", veiculos );
        model.addAttribute( "estados", Estado.values() );
    }

    private void tratarModelo( TransporteLocal obj ) throws Exception {

        List<Autonomo> ajudantes = new AutonomoService().listar();

        Cliente cliente = new ClientePessoaFisica();
        cliente.setId( Long.valueOf( request.getParameter( "_cliente" ) ) );
        cliente = (Cliente) getEntidade( cliente, clientes );

        Autonomo inventariante = new Autonomo();
        String inventarianteID = request.getParameter( "_inventariante" );

        if ( inventarianteID != null ) {
            inventariante.setId( Long.valueOf( inventarianteID ) );
            inventariante = ajudantes.get( ajudantes.indexOf( inventariante ) );
            obj.setInventariante( inventariante );
        }
       
        VeiculoDeTransporte veiculo = new VeiculoDeTransporte();
        veiculo.setId( Long.valueOf( request.getParameter( "_veiculoDeTransporte" ) ) );
        veiculo = (VeiculoDeTransporte) getEntidade( veiculo, veiculos );

        obj.setCliente( cliente );
        obj.setVeiculoDeTransporte( veiculo );
        
        

        String[] ajudantesID = request.getParameterValues( "ajudantes_selecionados" );

        if ( ajudantesID != null ) {
            obj.setAjudantes( new ArrayList<Autonomo>() );

            for ( String id : ajudantesID ) {
                Autonomo a = new Autonomo();
                a.setId( Long.valueOf( id ) );
                a = ajudantes.get( ajudantes.indexOf( a ) );
                obj.getAjudantes().add( a );
            }
        }

    }
}
