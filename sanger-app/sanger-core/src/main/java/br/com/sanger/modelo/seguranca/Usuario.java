/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.seguranca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.IUsuario;

/**
 *
 * @author eugenio
 */
@Entity
public class Usuario implements IUsuario, IEntidade<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true, nullable = false )
    private String login;

    @Column( unique = true, nullable = false )
    private String nome;

    private String senha;

    @ManyToMany
    public List<Perfil> perfis;

    public Usuario() {
        perfis = new ArrayList<Perfil>();
    }

    public Usuario( Long id ) {
        this();
        this.id = id;
    }

    public Usuario( String login ) {
        this();
        this.login = login;
    }

    public Usuario( String login, String senha ) {
        this( login );
        this.senha = senha;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin( String login ) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha( String senha ) {
        this.senha = senha;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome( String nome ) {
        this.nome = nome;
    }

    @Override
    public List<Perfil> getPerfis() {
        return perfis;
    }

    @Override
    public void setPerfis( List<Perfil> perfis ) {
        this.perfis = perfis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Usuario ) ) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eugeniosolucoes.modelo.seguranca.Usuario[ id=" + id + " ]";
    }
}
