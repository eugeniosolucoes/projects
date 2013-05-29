/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.sanger.modelo.IEntidade;

/**
 *
 * @author eugenio
 */
@Entity
public class Direito implements IEntidade<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( unique = true )
    private String descricao;

    @ManyToMany
    private List<Perfil> perfis;

    public Direito() {
        perfis = new ArrayList<Perfil>();
    }

    public Direito( Integer id ) {
        this();
        this.id = id;
    }

    public Direito( String descricao ) {
        this();
        this.descricao = descricao;
    }

    public Direito( Integer id, String descricao ) {
        this( id );
        this.descricao = descricao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId( Integer id ) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

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
        if ( !( object instanceof Direito ) ) {
            return false;
        }
        Direito other = (Direito) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eugeniosolucoes.modelo.seguranca.Direito[ id=" + id + " ]";
    }
}
