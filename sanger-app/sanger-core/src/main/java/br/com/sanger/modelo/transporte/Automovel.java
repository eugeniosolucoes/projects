/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author cpo-202
 */
@Entity
public class Automovel extends Veiculo {

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date entrada;

    private Double kilometragemEntrada;

    private String seguro;

    @Lob
    @Column( length = 512 )
    private String acessorios;

    @Lob
    @Column( length = 512 )
    private String avariasParteInterna;

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada( Date entrada ) {
        this.entrada = entrada;
    }

    public Double getKilometragemEntrada() {
        return kilometragemEntrada;
    }

    public void setKilometragemEntrada( Double kilometragemEntrada ) {
        this.kilometragemEntrada = kilometragemEntrada;
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
