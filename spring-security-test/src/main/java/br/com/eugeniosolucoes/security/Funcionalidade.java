/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.security;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author eugenio
 */
@Entity
public class Funcionalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false, unique = true )
    private String descricao;

    @ManyToMany
    private List<Perfil> perfis;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
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
        if ( !( object instanceof Funcionalidade ) ) {
            return false;
        }
        Funcionalidade other = (Funcionalidade) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eugenisolucoes.security.Funcionalidade[ id=" + id + " ]";
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }
}
