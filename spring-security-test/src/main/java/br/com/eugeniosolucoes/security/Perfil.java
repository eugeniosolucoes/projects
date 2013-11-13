/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.security;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author eugenio
 */
@Entity
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false, unique = true )
    private String descricao;

    @ManyToMany( mappedBy = "perfis" )
    private List<Usuario> usuarios;

    @ManyToMany( mappedBy = "perfis" )
    private List<Funcionalidade> funcionalidades;

    protected Perfil() {
    }

    public Perfil( String descricao ) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Perfil ) ) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eugenisolucoes.security.Perfil[ id=" + id + " ]";
    }

    public String getAuthority() {
        return descricao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Funcionalidade> getFuncionalidades() {
        return funcionalidades;
    }
}
