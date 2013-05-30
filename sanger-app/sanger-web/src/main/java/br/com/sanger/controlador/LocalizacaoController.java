/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.transporte.apoio.Localizacao;
import br.com.sanger.servico.transporte.apoio.impl.LocalizacaoService;
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
public class LocalizacaoController extends GenericController<Localizacao> {

    public LocalizacaoController() {
        servico = new LocalizacaoService();
        obj = new Localizacao();
        entidade = obj.getClass().getSimpleName().toLowerCase();
    }

    @Override
    @RequestMapping( value = "/localizacao/{id}", method = RequestMethod.GET )
    public String editar( @PathVariable Long id, Model model ) {
        model.addAttribute( "tabIndex", 0 );
        return super.editar( id, model );
    }

    @Override
    @RequestMapping( value = "/localizacao/excluir/{id}", method = RequestMethod.GET )
    public String excluir( @PathVariable Long id, Model model ) {
        return super.excluir( id, model );
    }

    @Override
    @RequestMapping( value = "/localizacao/novo" )
    public String formulario( Model model ) {
        model.addAttribute( "tabIndex", 0 );
        return super.formulario( model );
    }

    @Override
    @RequestMapping( value = "/localizacao/listar" )
    public String listar( Model model ) {
        return super.listar( model );
    }

    @Override
    @RequestMapping( value = "/localizacao/salvar", method = RequestMethod.POST, params = { "tabIndex" } )
    public String salvar( Localizacao obj, @RequestParam String tabIndex, Model model ) {
        model.addAttribute( "tabIndex", tabIndex );
        return super.salvar( obj, tabIndex, model );
    }
}
