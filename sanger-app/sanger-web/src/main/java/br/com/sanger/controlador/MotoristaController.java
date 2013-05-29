/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.apoio.Estado;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.servico.funcionarios.impl.MotoristaService;
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
public class MotoristaController extends GenericController<Motorista> {

    public MotoristaController() {
        servico = new MotoristaService();
        obj = new Motorista();
        entidade = obj.getClass().getSimpleName().toLowerCase();
    }

    @Override
    @RequestMapping( value = "/motorista/{id}", method = RequestMethod.GET )
    public String editar( @PathVariable Long id, Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", 0 );
        return super.editar( id, model );
    }

    @Override
    @RequestMapping( value = "/motorista/excluir/{id}", method = RequestMethod.GET )
    public String excluir( @PathVariable Long id, Model model ) {
        return super.excluir( id, model );
    }

    @Override
    @RequestMapping( value = "/motorista/novo" )
    public String formulario( Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", 0 );
        return super.formulario( model );
    }

    @Override
    @RequestMapping( value = "/motorista/listar" )
    public String listar( Model model ) {
        return super.listar( model );
    }

    @Override
    @RequestMapping( value = "/motorista/salvar", method = RequestMethod.POST, params = { "tabIndex" } )
    public String salvar( Motorista obj, @RequestParam String tabIndex, Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", tabIndex );
        return super.salvar( obj, tabIndex, model );
    }
}
