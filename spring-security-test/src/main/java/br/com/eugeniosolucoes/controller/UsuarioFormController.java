/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.security.Usuario;
import br.com.eugeniosolucoes.service.UsuarioService;
import br.com.eugeniosolucoes.ui.swing.forms.MainDesktop;
import br.com.eugeniosolucoes.ui.swing.forms.UsuarioForm;
import br.com.eugeniosolucoes.ui.swing.models.UsuarioTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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

    UsuarioTableModel model;

    @PostConstruct
    public void init() {

        final UsuarioForm form = mainDesktop.getUsuarioForm();

        model = new UsuarioTableModel();

        form.getTblUsuario().setModel( model );
        carregarTabela();

        form.getBtnSalvar().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                salvar();
            }
        } );

        form.getBtnFechar().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hide();
            }
        } );

        mainDesktop.getUsuarioMenuItem().addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                show();
            }
        } );

        form.addInternalFrameListener( new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing( InternalFrameEvent e ) {
                hide();
            }
        } );

        form.getTblUsuario().addPropertyChangeListener( new PropertyChangeListener() {
            public void propertyChange( PropertyChangeEvent evt ) {
                if ( evt.getSource() instanceof JTable ) {
                    JTable table = (JTable) evt.getSource();
                    if ( table.getEditingRow() != -1 ) {
                        UsuarioTableModel model = (UsuarioTableModel) table.getModel();
                        Usuario usuario = model.getUsuarioAt( table.getEditingRow() );
                        try {
                            usuarioService.salvar( usuario );
                        } catch ( Exception e ) {
                            carregarTabela();
                            JOptionPane.showMessageDialog( mainDesktop, e.getMessage() );
                        }
                    }
                }
            }
        } );

        form.getTblUsuario().addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 ) {
                    if ( form.getTblUsuario().getSelectedRow() != -1 ) {
                        UsuarioTableModel model = (UsuarioTableModel) form.getTblUsuario().getModel();
                        Usuario usuario = model.getUsuarioAt( form.getTblUsuario().getSelectedRow() );
                        fillForm( usuario );
                    }
                }
            }
        } );

    }

    private void salvar() {
        try {
            Usuario usuario = new Usuario(
                    mainDesktop.getUsuarioForm().getNome().getText(),
                    new String( mainDesktop.getUsuarioForm().getSenha().getPassword() ),
                    mainDesktop.getUsuarioForm().getAtivo().isSelected() );
            usuario.setId( mainDesktop.getUsuarioForm().getUsuarioID() );
            usuarioService.salvar( usuario );
            limparForm();
            carregarTabela();
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

    private void carregarTabela() {
        model.reload( usuarioService.listar() );
    }

    private void limparForm() {
        mainDesktop.getUsuarioForm().setUsuarioID( null );
        mainDesktop.getUsuarioForm().getNome().setText( "" );
        mainDesktop.getUsuarioForm().getSenha().setText( "" );
        mainDesktop.getUsuarioForm().getAtivo().setSelected( false );
    }

    private void fillForm( Usuario usuario ) {
        mainDesktop.getUsuarioForm().setUsuarioID( usuario.getId() );
        mainDesktop.getUsuarioForm().getNome().setText( usuario.getNome() );
        mainDesktop.getUsuarioForm().getSenha().setText( usuario.getPassword() );
        mainDesktop.getUsuarioForm().getAtivo().setSelected( usuario.isAtivo() );
    }
}
