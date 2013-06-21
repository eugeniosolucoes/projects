/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.acesso;

/**
 *
 * @author eugenio
 */
public interface Usuario {

    void setLogin( String login );

    String getLogin();

    void setNome( String nome );

    String getNome();

    String getLoginNome();
    
    void setSenha( String senha );

    String getSenha();
}
