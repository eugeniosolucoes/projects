/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.ui.swing.MainDesktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.annotation.PostConstruct;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugenio
 */
@Component
public class UsuarioFormController implements ActionListener {

    @Autowired
    private MainDesktop mainDesktop;

    @PostConstruct
    public void init() {
        mainDesktop.getBtnSalvar().addActionListener( this );
        mainDesktop.getBtnCancelar().addActionListener( this );
        mainDesktop.getUsuarioMenuItem().addActionListener( this );
        
    }

    public void actionPerformed( ActionEvent e ) {
        if(e.getSource().equals( mainDesktop.getUsuarioMenuItem()) ) {
            mainDesktop.getUsuarioForm().setVisible( true );
        }
    }

    public void show() {
    }

}
