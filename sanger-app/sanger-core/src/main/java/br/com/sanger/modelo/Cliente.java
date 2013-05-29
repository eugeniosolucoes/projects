/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author eugenio
 */
@Entity
public abstract class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany( mappedBy = "cliente")
    private List<TransporteLocal> servicos;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private Endereco endereco;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !(object instanceof Cliente) ) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.Cliente[ id=" + id + " ]";
    }

    public List<TransporteLocal> getServicos() {
        return servicos;
    }

    public void setServicos( List<TransporteLocal> servicos ) {
        this.servicos = servicos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco( Endereco endereco ) {
        this.endereco = endereco;
    }
    
}
