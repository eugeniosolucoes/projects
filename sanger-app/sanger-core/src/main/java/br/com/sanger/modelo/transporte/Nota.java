/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.transporte.interestadual.Item;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author eugenio
 */
@Entity
public class Nota implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String numero;

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date emissao;

    private double aliquota;

    private double imposto;

    @OneToMany
    private List<Item> itens;

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
        if ( !( object instanceof Nota ) ) {
            return false;
        }
        Nota other = (Nota) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.Nota[ id=" + id + " ]";
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero( String numero ) {
        this.numero = numero;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao( Date emissao ) {
        this.emissao = emissao;
    }

    public double getAliquota() {
        return aliquota;
    }

    public void setAliquota( double aliquota ) {
        this.aliquota = aliquota;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto( double imposto ) {
        this.imposto = imposto;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens( List<Item> itens ) {
        this.itens = itens;
    }
}
