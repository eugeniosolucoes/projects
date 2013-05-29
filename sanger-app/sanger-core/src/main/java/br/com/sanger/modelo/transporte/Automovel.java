/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author cpo-202
 */
@Entity
public class Automovel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date entrada;

    private String placa;

    private Double kilometragemEntrada;

    private String marcaModelo;

    private String cor;

    private Integer ano;

    private String chassi;

    private String seguro;

    @Lob
    @Column( length = 512 )
    private String acessorios;

    @Lob
    @Column( length = 512 )
    private String avariasParteInterna;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Automovel ) ) {
            return false;
        }
        Automovel other = (Automovel) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.transporte.Automovel[ id=" + id + " ]";
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada( Date entrada ) {
        this.entrada = entrada;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca( String placa ) {
        this.placa = placa;
    }

    public Double getKilometragemEntrada() {
        return kilometragemEntrada;
    }

    public void setKilometragemEntrada( Double kilometragemEntrada ) {
        this.kilometragemEntrada = kilometragemEntrada;
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo( String marcaModelo ) {
        this.marcaModelo = marcaModelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor( String cor ) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno( Integer ano ) {
        this.ano = ano;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi( String chassi ) {
        this.chassi = chassi;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro( String seguro ) {
        this.seguro = seguro;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios( String acessorios ) {
        this.acessorios = acessorios;
    }

    public String getAvariasParteInterna() {
        return avariasParteInterna;
    }

    public void setAvariasParteInterna( String avariasParteInterna ) {
        this.avariasParteInterna = avariasParteInterna;
    }
    
    
}
