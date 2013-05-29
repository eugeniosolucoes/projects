/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eugenio
 */
@Controller
public class IndexController {

    @RequestMapping( "/index" )
    public ModelAndView retornaPrincipal() {
        ModelAndView view = new ModelAndView( "index" );
        return view;
    }
}
