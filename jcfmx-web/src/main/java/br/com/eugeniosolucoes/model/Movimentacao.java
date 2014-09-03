/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

import br.com.eugeniosolucoes.model.types.TipoFrequencia;
import br.com.eugeniosolucoes.model.types.TipoMovimentacao;
import br.com.eugeniosolucoes.repository.Entidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.LocalDate;

/**
 *
 * @author eugenio
 */
@Entity
public abstract class Movimentacao implements Entidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    protected String descricao;

    protected BigDecimal valor = BigDecimal.ZERO;

    @Enumerated( EnumType.STRING )
    protected TipoMovimentacao tipo = TipoMovimentacao.SAIDA;

    @Column( name = "data_movimentacao" )
    @Temporal( TemporalType.DATE )
    protected Date data = LocalDate.now().toDate();

    @Enumerated( EnumType.STRING )
    protected TipoFrequencia frequencia = TipoFrequencia.MENSAL;

    @ManyToOne
    protected Conta conta;

    @ElementCollection
    protected final Set<String> categorias = new HashSet<>();

    protected Movimentacao() {
    }

    public Movimentacao( String descricao, BigDecimal valor, TipoMovimentacao tipo ) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Movimentacao( String descricao, double valor, TipoMovimentacao tipo ) {
        this.descricao = descricao;
        this.valor = new BigDecimal( valor );
        this.tipo = tipo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor( BigDecimal valor ) {
        this.valor = valor;
    }

    public void setTipo( TipoMovimentacao tipo ) {
        this.tipo = tipo;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setConta( Conta conta ) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public boolean adicionarCategoria( String categoria ) {
        return this.categorias.add( categoria );
    }

    public Set<String> getCategorias() {
        return Collections.unmodifiableSet( categorias );
    }

    public TipoFrequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia( TipoFrequencia frequencia ) {
        this.frequencia = frequencia;
    }

    public Date getData() {
        return data;
    }

    public void setData( Date data ) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode( this.descricao );
        hash = 83 * hash + Objects.hashCode( this.valor );
        hash = 83 * hash + Objects.hashCode( this.tipo );
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
        final Movimentacao other = (Movimentacao) obj;
        if ( !Objects.equals( this.descricao, other.descricao ) ) {
            return false;
        }
        if ( !Objects.equals( this.valor, other.valor ) ) {
            return false;
        }
        return this.tipo == other.tipo;
    }

    @Override
    public String toString() {
        return "Movimentacao{" + "id=" + id + ", descricao=" + descricao + ", valor=" + valor + '}';
    }

}
