/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.security.Usuario;
import br.com.eugeniosolucoes.service.UsuarioService;
import br.com.eugeniosolucoes.ui.swing.MainDesktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugenio
 */
@Component
public class UsuarioFormController implements Serializable {

    @Autowired
    private MainDesktop mainDesktop;

    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void init() {
        mainDesktop.getBtnSalvar().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                salvar();
            }
        } );
        mainDesktop.getBtnCancelar().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hide();
            }
        } );
        mainDesktop.getUsuarioMenuItem().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                show();
            }
        } );
        mainDesktop.getUsuarioForm().addInternalFrameListener( new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing( InternalFrameEvent e ) {
                hide();
            }
        } );

    }

    private void salvar() {
        try {
            Usuario usuario = new Usuario( mainDesktop.getNome().getText(),
                    new String( mainDesktop.getSenha().getPassword() ), mainDesktop.getAtivo().isSelected() );
            usuarioService.salvar( usuario );
            limparForm();
            JOptionPane.showMessageDialog( mainDesktop, "Usuário salvo com sucesso!" );
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog( mainDesktop, e.getMessage() );
        }
    }

    private void show() {
        mainDesktop.getUsuarioForm().setVisible( true );
    }

    private void hide() {
        limparForm();
        mainDesktop.getUsuarioForm().setVisible( false );
    }

    private void limparForm() {
        mainDesktop.getNome().setText( "" );
        mainDesktop.getSenha().setText( "" );
        mainDesktop.getAtivo().setSelected( false );
    }
}
