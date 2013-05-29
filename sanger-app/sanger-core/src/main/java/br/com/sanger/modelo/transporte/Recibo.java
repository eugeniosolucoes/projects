/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.IEntidade;

/**
 *
 * @author eugenio
 */
@Entity
public class Recibo implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date emissao;

    private double quantia;

    private String extenso;

    private String referente;

    private String pagamento;

    @ManyToOne
    private Cliente cliente;

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
        if ( !( object instanceof Recibo ) ) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.Recibo[ id=" + id + " ]";
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao( Date emissao ) {
        this.emissao = emissao;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia( double quantia ) {
        this.quantia = quantia;
    }

    public String getExtenso() {
        return extenso;
    }

    public void setExtenso( String extenso ) {
        this.extenso = extenso;
    }

    public String getReferente() {
        return referente;
    }

    public void setReferente( String referente ) {
        this.referente = referente;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento( String pagamento ) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }
}
