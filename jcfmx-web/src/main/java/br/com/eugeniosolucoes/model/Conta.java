/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

import br.com.eugeniosolucoes.repository.Entidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
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
public class Conta implements Entidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String banco;

    private String agencia;

    private String conta;

    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToMany( mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true )
    private final Set<Movimentacao> movimentacoes = new HashSet<>();

    @ManyToOne
    private Cliente cliente;

    protected Conta() {
    }

    public Conta( String banco, String agencia, String conta ) {
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco( String banco ) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia( String agencia ) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta( String conta ) {
        this.conta = conta;
    }
    
    @Override
    public Long getId() {
        return id;
    }    
    
    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal creditar( BigDecimal saldo ) {
        this.saldo = this.saldo.add( saldo );
        return this.saldo;
    }

    public BigDecimal deditar( BigDecimal saldo ) {
        this.saldo = this.saldo.subtract( saldo );
        return this.saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }

    public boolean adicionarMovimentacao( Movimentacao movimentacao ) {
        if ( this.movimentacoes.add( movimentacao ) ) {
            movimentacao.setConta( this );
            return true;
        } else {
            return false;
        }
    }

    public Set<Movimentacao> getMovimentacoes() {
        return Collections.unmodifiableSet( movimentacoes );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode( this.banco );
        hash = 29 * hash + Objects.hashCode( this.agencia );
        hash = 29 * hash + Objects.hashCode( this.conta );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Conta other = (Conta) obj;
        if ( !Objects.equals( this.banco, other.banco ) ) {
            return false;
        }
        if ( !Objects.equals( this.agencia, other.agencia ) ) {
            return false;
        }
        return Objects.equals( this.conta, other.conta );
    }

    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", banco=" + banco + ", agencia=" + agencia + ", conta=" + conta + '}';
    }



}
