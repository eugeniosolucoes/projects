/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.interestadual;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.transporte.Transporte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author eugenio
 */
@Entity
public class TransporteInterestadual extends Transporte implements IEntidade<Long>, Serializable {

    private int numero;

    @OneToMany
    private List<Bem> bens;

    public List<Bem> getBens() {
        return bens;
    }

    public void setBens( List<Bem> bens ) {
        this.bens = bens;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero( int numero ) {
        this.numero = numero;
    }

}
