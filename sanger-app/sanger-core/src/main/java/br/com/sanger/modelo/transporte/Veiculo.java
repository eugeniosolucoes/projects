/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.IEntidade;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author cpo-202
 */
@MappedSuperclass
public abstract class Veiculo implements IEntidade<Long>, Serializable {

    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;
    
    protected Integer ano;

    protected String chassi;

    protected String cor;

    protected String marca;

    protected String modelo;

    protected String placa;

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Veiculo ) ) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    public Integer getAno() {
        return ano;
    }

    public String getChassi() {
        return chassi;
    }

    public String getCor() {
        return cor;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    public void setAno( Integer ano ) {
        this.ano = ano;
    }

    public void setChassi( String chassi ) {
        this.chassi = chassi;
    }

    public void setCor( String cor ) {
        this.cor = cor;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    public void setMarca( String marca ) {
        this.marca = marca;
    }

    public void setModelo( String modelo ) {
        this.modelo = modelo;
    }

    public void setPlaca( String placa ) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.Automovel[ id=" + id + " ]";
    }
    
}
