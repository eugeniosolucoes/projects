/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.apoio;

import br.com.sanger.modelo.IEntidade;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author eugenio
 */
@Entity
public class Simbolo implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true )
    private String sigla;

    @Column( unique = true )
    private String descricao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if ( !( object instanceof Simbolo ) ) {
            return false;
        }
        Simbolo other = (Simbolo) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.apoio.Simbolo[ id=" + id + " ]";
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla( String sigla ) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }
}
