/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.Transporte;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author eugenio
 */
@Entity
@Table( uniqueConstraints =
@UniqueConstraint( columnNames = { "dtype", "nome" } ) )
public abstract class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String nome;

    @OneToMany( mappedBy = "cliente" )
    private List<Transporte> servicos;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false )
    private Endereco endereco;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Cliente ) ) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.Cliente[ id=" + id + " ]";
    }

    public List<Transporte> getServicos() {
        return servicos;
    }

    public void setServicos( List<Transporte> servicos ) {
        this.servicos = servicos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco( Endereco endereco ) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
}
