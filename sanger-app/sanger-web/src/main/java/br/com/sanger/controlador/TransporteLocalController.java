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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        obj = new TransporteLocal();
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

    @RequestMapping( value = "/transportelocal/imprimir/{id}" )
    public void imprimir( @PathVariable Long id, HttpServletResponse response ) throws Exception {
        ServletOutputStream output = response.getOutputStream();
        try {
            response.setContentType( "application/pdf" );
            response.addHeader( "content-disposition", "inline;filename=transporteLocal.pdf" );
            byte[] pdf = ( (TransporteLocalService) servico ).imprimir( id );
            output.write( pdf );
        } catch ( Exception e ) {
            Logger.getLogger( TransporteLocalController.class.getName() ).log( Level.WARNING, e.getMessage() );
        } finally {
            output.close();
        }
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

        String motoristaID = request.getParameter( "_motorista" );
        if ( motoristaID != null ) {
            Motorista motorista = new Motorista();
            motorista.setId( Long.valueOf( request.getParameter( "_motorista" ) ) );
            motorista = (Motorista) getEntidade( motorista, motoristas );
            obj.setMotorista( motorista );
        }

        VeiculoDeTransporte veiculo = new VeiculoDeTransporte();
        veiculo.setId( Long.valueOf( request.getParameter( "_veiculoDeTransporte" ) ) );
        veiculo = (VeiculoDeTransporte) getEntidade( veiculo, veiculos );

        obj.setCliente( cliente );
        obj.setVeiculoDeTransporte( veiculo );

        obj.setSaida( GenericController.stringTimeToDate( request.getParameter( "_saida" ) ) );
        obj.setChegadaCliente( GenericController.stringTimeToDate( request.getParameter( "_chegadaCliente" ) ) );
        obj.setSaidaCliente( GenericController.stringTimeToDate( request.getParameter( "_saidaCliente" ) ) );
        obj.setRetorno( GenericController.stringTimeToDate( request.getParameter( "_retorno" ) ) );

        obj.setPrecoPorHora( GenericController.currencyToDouble( request.getParameter( "_precoPorHora" ) ) );
        obj.setPrecoPorCaixa( GenericController.currencyToDouble( request.getParameter( "_precoPorCaixa" ) ) );
        obj.setPrecoRetorno( GenericController.currencyToDouble( request.getParameter( "_precoRetorno" ) ) );
        obj.setTotal( GenericController.currencyToDouble( request.getParameter( "_total" ) ) );

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
