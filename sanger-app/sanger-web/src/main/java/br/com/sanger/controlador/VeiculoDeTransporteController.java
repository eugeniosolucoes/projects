/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import br.com.sanger.modelo.apoio.Estado;
import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import br.com.sanger.servico.transporte.impl.VeiculoDeTransporteService;
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
public class VeiculoDeTransporteController extends GenericController<VeiculoDeTransporte> {

    public VeiculoDeTransporteController() {
        servico = new VeiculoDeTransporteService();
        obj = new VeiculoDeTransporte();
        entidade = obj.getClass().getSimpleName().toLowerCase();
    }

    @Override
    @RequestMapping( value = "/veiculodetransporte/{id}", method = RequestMethod.GET )
    public String editar( @PathVariable Long id, Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", 0 );
        return super.editar( id, model );
    }

    @Override
    @RequestMapping( value = "/veiculodetransporte/excluir/{id}", method = RequestMethod.GET )
    public String excluir( @PathVariable Long id, Model model ) {
        return super.excluir( id, model );
    }

    @Override
    @RequestMapping( value = "/veiculodetransporte/novo" )
    public String formulario( Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", 0 );
        return super.formulario( model );
    }

    @Override
    @RequestMapping( value = "/veiculodetransporte/listar" )
    public String listar( Model model ) {
        return super.listar( model );
    }

    @Override
    @RequestMapping( value = "/veiculodetransporte/salvar", method = RequestMethod.POST, params = { "tabIndex" } )
    public String salvar( VeiculoDeTransporte obj, @RequestParam String tabIndex, Model model ) {
        model.addAttribute( "estados", Estado.values() );
        model.addAttribute( "tabIndex", tabIndex );
        return super.salvar( obj, tabIndex, model );
    }
}
