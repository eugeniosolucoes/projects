/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.interestadual;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.transporte.Transporte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author eugenio
 */
@Entity
public class TransporteInterestadual extends Transporte implements IEntidade<Long>, Serializable {

    private Integer numero;

    @OneToMany
    private List<Inventario> inventarioDeBens;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero( Integer numero ) {
        this.numero = numero;
    }

    public List<Inventario> getInventarioDeBens() {
        return inventarioDeBens;
    }

    public void setInventarioDeBens( List<Inventario> inventarioDeBens ) {
        this.inventarioDeBens = inventarioDeBens;
    }

}
