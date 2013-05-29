/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.local;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Transporte;

/**
 *
 * @author eugenio
 */
@Entity
public class TransporteLocal extends Transporte {

    @ManyToOne
    private Autonomo inventariante;

    @ManyToOne
    private Motorista motorista;

    @OneToMany
    private List<Autonomo> ajudantes;

    public Autonomo getInventariante() {
        return inventariante;
    }

    public void setInventariante( Autonomo inventariante ) {
        this.inventariante = inventariante;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista( Motorista motorista ) {
        this.motorista = motorista;
    }

    public List<Autonomo> getAjudantes() {
        return ajudantes;
    }

    public void setAjudantes( List<Autonomo> ajudantes ) {
        this.ajudantes = ajudantes;
    }
}
