/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.servico.transporte.impl.TransporteLocalService;
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

    public TransporteLocalController() {
        servico = new TransporteLocalService();
        obj = new TransporteLocal();
        entidade = obj.getClass().getSimpleName().toLowerCase();
    }

    @Override
    @RequestMapping( value = "/transportelocal/{id}", method = RequestMethod.GET )
    public String editar( @PathVariable Long id, Model model ) {
        model.addAttribute( "tabIndex", 0 );
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
        return super.formulario( model );
    }

    @Override
    @RequestMapping( value = "/transportelocal/listar" )
    public String listar( Model model ) {
        return super.listar( model );
    }

    @Override
    @RequestMapping( value = "/transportelocal/salvar", method = RequestMethod.POST, params = { "tabIndex" } )
    public String salvar( TransporteLocal obj, @RequestParam String tabIndex, Model model ) {
        model.addAttribute( "tabIndex", tabIndex );
        return super.salvar( obj, tabIndex, model );
    }
}
