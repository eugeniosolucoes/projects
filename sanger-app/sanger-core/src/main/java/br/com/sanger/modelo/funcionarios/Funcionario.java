/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.funcionarios;

import br.com.sanger.modelo.Pessoa;
import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.Transporte;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author eugenio
 */
@Entity
public abstract class Funcionario extends Pessoa implements Serializable {

    @OneToMany( mappedBy = "inventariante" )
    @ManyToMany( mappedBy = "ajudantes" )
    private List<Transporte> servicos;

    private static final long serialVersionUID = 1L;

    @Column( unique = true )
    private String identidade;
    
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
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
        if ( !( object instanceof Funcionario ) ) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.Funcionario[ id=" + id + " ]";
    }

    public List<Transporte> getServicos() {
        return servicos;
    }

    public void setServicos( List<Transporte> servicos ) {
        this.servicos = servicos;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade( String identidade ) {
        this.identidade = identidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco( Endereco endereco ) {
        this.endereco = endereco;
    }
    
}
