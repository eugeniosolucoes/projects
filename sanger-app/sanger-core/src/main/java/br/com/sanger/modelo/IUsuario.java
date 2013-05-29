/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo;

import java.io.Serializable;
import java.util.List;

import br.com.sanger.modelo.seguranca.Perfil;

/**
 *
 * @author eugenio
 */
public interface IUsuario extends Serializable {

    String getLogin();

    String getNome();

    List<Perfil> getPerfis();

    String getSenha();

    void setLogin( String login );

    void setNome( String nome );

    void setPerfis( List<Perfil> perfis );

    void setSenha( String senha );
}
