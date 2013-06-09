/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.funcionarios.Autonomo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eugenio
 */
@Entity
public abstract class Transporte implements IEntidade<Long>, Serializable {

    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    @Temporal( value = TemporalType.DATE )
    protected Date dataDoServico;

    @ManyToOne
    protected Cliente cliente;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false )
    protected Destinatario destinatario;

    @OneToMany
    protected List<Autonomo> ajudantes;

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Transporte ) ) {
            return false;
        }
        Transporte other = (Transporte) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getDataDoServico() {
        return dataDoServico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }

    public void setDestinatario( Destinatario destinatario ) {
        this.destinatario = destinatario;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    public void setDataDoServico( Date dataDoServico ) {
        this.dataDoServico = dataDoServico;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.Servico[ id=" + id + " ]";
    }

    public List<Autonomo> getAjudantes() {
        return ajudantes;
    }

    public void setAjudantes( List<Autonomo> ajudantes ) {
        this.ajudantes = ajudantes;
    }
}
