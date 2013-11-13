/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.service.UsuarioService;
import br.com.eugeniosolucoes.ui.swing.MainDesktop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugenio
 */
@Component
public class MainController {

    private MainDesktop mainDesktop;

    public MainController( MainDesktop mainDesktop ) {
        this.mainDesktop = mainDesktop;
    }

    public void execute() {
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                mainDesktop.setVisible( true );
            }
        } );
    }
}
