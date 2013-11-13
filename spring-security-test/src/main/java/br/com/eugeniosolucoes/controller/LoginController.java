/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.ui.swing.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugenio
 */
@Component
public class LoginController implements ActionListener {

    @Autowired
    private LoginForm loginForm;

    @PostConstruct
    public void init() {
        loginForm.getLoginButton().addActionListener( this );
        loginForm.getCancelarButton().addActionListener( this );
    }

    public void actionPerformed( ActionEvent e ) {
        if ( e.getSource().equals( loginForm.getLoginButton() ) ) {
            JOptionPane.showMessageDialog( null, "OK" );
        }
        if ( e.getSource().equals( loginForm.getCancelarButton() ) ) {
            System.exit( 0 );
        }
    }

    public void show() {
        loginForm.setVisible( true );
    }
}
