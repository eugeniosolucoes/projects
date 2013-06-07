/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.local;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Transporte;
import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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


    @OneToOne
    private VeiculoDeTransporte veiculoDeTransporte;

    @Temporal( javax.persistence.TemporalType.TIME )
    private Date saida;

    @Temporal( javax.persistence.TemporalType.TIME )
    private Date chegadaCliente;

    @Temporal( javax.persistence.TemporalType.TIME )
    private Date saidaCliente;

    @Temporal( javax.persistence.TemporalType.TIME )
    private Date retorno;

    private Double precoPorHora;

    private Double minimoDeHoras;

    private Double precoPorCaixa;

    private Double precoRetorno;

    private Double total;

    @Lob
    @Column( length = 512 )
    private String observacoes;

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

    public VeiculoDeTransporte getVeiculoDeTransporte() {
        return veiculoDeTransporte;
    }

    public void setVeiculoDeTransporte( VeiculoDeTransporte veiculoDeTransporte ) {
        this.veiculoDeTransporte = veiculoDeTransporte;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida( Date saida ) {
        this.saida = saida;
    }

    public Date getChegadaCliente() {
        return chegadaCliente;
    }

    public void setChegadaCliente( Date chegadaCliente ) {
        this.chegadaCliente = chegadaCliente;
    }

    public Date getSaidaCliente() {
        return saidaCliente;
    }

    public void setSaidaCliente( Date saidaCliente ) {
        this.saidaCliente = saidaCliente;
    }

    public Date getRetorno() {
        return retorno;
    }

    public void setRetorno( Date retorno ) {
        this.retorno = retorno;
    }

    public Double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora( Double precoPorHora ) {
        this.precoPorHora = precoPorHora;
    }

    public Double getMinimoDeHoras() {
        return minimoDeHoras;
    }

    public void setMinimoDeHoras( Double minimoDeHoras ) {
        this.minimoDeHoras = minimoDeHoras;
    }

    public Double getPrecoPorCaixa() {
        return precoPorCaixa;
    }

    public void setPrecoPorCaixa( Double precoPorCaixa ) {
        this.precoPorCaixa = precoPorCaixa;
    }

    public Double getPrecoRetorno() {
        return precoRetorno;
    }

    public void setPrecoRetorno( Double precoRetorno ) {
        this.precoRetorno = precoRetorno;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal( Double total ) {
        this.total = total;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes( String observacoes ) {
        this.observacoes = observacoes;
    }

    public String getListaAjudantes() {
        if ( ajudantes != null && !ajudantes.isEmpty() ) {
            StringBuilder sb = new StringBuilder();
            for ( Autonomo autonomo : ajudantes ) {
                sb.append( autonomo.getNome().concat( ", " ) );
            }
            return sb.substring( 0, sb.length() - ", ".length() );
        }
        return null;
    }
}
