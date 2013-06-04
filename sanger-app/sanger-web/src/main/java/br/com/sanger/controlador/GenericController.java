/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.servico.GenericService;
import br.com.sanger.util.MyStrings;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author eugenio
 */
@SuppressWarnings( "rawtypes" )
public abstract class GenericController<T extends IEntidade> {

    protected GenericService<T> servico;

    protected T obj;

    protected String entidade;

    public GenericController() {
    }

    /**
     * RequestMapping( value = "/entidade/{id}", method = RequestMethod.GET )
     *
     * @param id
     * @param model
     * @return
     */
    public String editar( Long id, Model model ) {
        try {
            obj = servico.retornar( id );
            model.addAttribute( entidade, obj );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
        }
        return entidade + "/formulario";
    }

    /**
     * RequestMapping( value = "/entidade/excluir/{id}", method =
     * RequestMethod.GET )
     *
     * @param id
     * @param model
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public String excluir( Long id, Model model ) {
        try {
            obj.setId( id );
            servico.excluir( obj );
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_SUCCESS );
            model.addAttribute( "mensagem", "Registro excluído com sucesso!" );
            List<T> lista = servico.listar();
            model.addAttribute( "lista", lista );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
            return editar( id, model );
        }
        return entidade + "/listagem";
    }

    /**
     * RequestMapping( value = "/entidade/novo" )
     *
     * @param model
     * @return
     */
    public String formulario( Model model ) {
        return entidade + "/formulario";
    }

    @InitBinder
    public void initBinder( WebDataBinder webDataBinder ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        dateFormat.setLenient( false );
        webDataBinder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, true ) );
    }

    /**
     *
     * RequestMapping( value = "/entidade/listar" )
     *
     * @param model
     * @return
     */
    public String listar( Model model ) {
        try {
            List<T> lista = servico.listar();
            model.addAttribute( "lista", lista );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
        }
        return entidade + "/listagem";
    }

    /**
     * RequestMapping( value = "/entidade/salvar", method = RequestMethod.POST,
     * params = { "tabIndex" } )
     *
     * @param obj
     * @param tabIndex
     * @param model
     * @return
     */
    public String salvar( T obj, String tabIndex, Model model ) {
        try {
            servico.salvar( obj );
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_SUCCESS );
            model.addAttribute( "mensagem", "Registro salvo com sucesso!" );
        } catch ( Exception ex ) {
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( ex.getMessage() ) );
        }
        model.addAttribute( entidade, obj );
        return entidade + "/formulario";
    }

    public <E extends IEntidade> IEntidade getEntidade( Object id, List<E> lista ) throws Exception {
        int i = lista.indexOf( id );
        if ( i < 0 ) {
            return null;
        }
        E objeto = lista.get( i );
        return objeto;
    }

    public static Double currencyToDouble( String currency ) {
        if ( MyStrings.isNullOrEmpty( currency ) ) {
            return null;
        }
        try {
            Double value = Double.valueOf( currency );
            return value;
        } catch ( NumberFormatException e ) {
            currency = currency.replace( "R$", "" );
            if ( currency.indexOf( "," ) == currency.length() - 3 ) {
                currency = currency.replace( ".", "" );
                currency = currency.replace( ",", "." );
            } else {
                currency = currency.replace( ",", "" );
            }
            Double value = Double.valueOf( currency );
            return value;
        } catch ( Exception ex ) {
            return null;
        }
    }

    public static Date stringTimeToDate( String time ) {
        if ( time == null ) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );
        try {
            return sdf.parse( time );
        } catch ( Exception e ) {
            return null;
        }
    }
}
