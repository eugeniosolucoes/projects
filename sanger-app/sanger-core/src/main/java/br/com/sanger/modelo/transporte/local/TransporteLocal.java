/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.local;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Transporte;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

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
    
    @Temporal( javax.persistence.TemporalType.TIME )
    Date saida;
    
    @Temporal( javax.persistence.TemporalType.TIME )
    Date chegadaCliente;
    
    @Temporal( javax.persistence.TemporalType.TIME )
    Date saidaCliente;

    @Temporal( javax.persistence.TemporalType.TIME )
    Date retorno;
    
    Double precoPorHora;
    
    Double minimoDeHoras;
    
    Double precoPorCaixa;
    
    Double precoRetorno;
    
    Double total;
    
    @Lob
    @Column( length = 512 )            
    String observacoes;

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
