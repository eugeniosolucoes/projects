/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

import br.com.eugeniosolucoes.model.types.TipoMovimentacao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.LocalDate;

@Entity
@Table( name = "movimentacao_parcelada" )
public class MovimentacaoParcelada implements Serializable, Comparable<MovimentacaoParcelada> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private int parcela;

    private BigDecimal valor;

    @Column( name = "data_movimentacao" )
    @Temporal( TemporalType.DATE )
    private Date data;

    @ManyToOne
    private Movimentacao origem;

    protected MovimentacaoParcelada() {
    }

    public MovimentacaoParcelada( int parcela, BigDecimal valor, Date data, Movimentacao origem ) {
        this.parcela = parcela;
        this.valor = valor;
        this.data = data;
        this.origem = origem;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Movimentacao getOrigem() {
        return origem;
    }

    public void setOrigem( Movimentacao origem ) {
        this.origem = origem;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela( int parcela ) {
        this.parcela = parcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor( BigDecimal valor ) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData( Date data ) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.parcela;
        hash = 29 * hash + Objects.hashCode( this.valor );
        hash = 29 * hash + Objects.hashCode( this.data );
        hash = 29 * hash + Objects.hashCode( this.origem );
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
        final MovimentacaoParcelada other = (MovimentacaoParcelada) obj;
        if ( this.parcela != other.parcela ) {
            return false;
        }
        if ( !Objects.equals( this.valor, other.valor ) ) {
            return false;
        }
        if ( !Objects.equals( this.data, other.data ) ) {
            return false;
        }
        if ( !Objects.equals( this.origem, other.origem ) ) {
            return false;
        }
        return true;
    }

    public String getDescricao() {
        return origem.getDescricao();
    }

    public TipoMovimentacao getTipo() {
        return origem.getTipo();
    }

    public Conta getConta() {
        return origem.getConta();
    }

    public Set<String> getCategorias() {
        return origem.getCategorias();
    }

    @Override
    public String toString() {
        return "MovimentacaoParcelada{" + "id=" + id + ", parcela=" + parcela + ", valor=" + String.format( "%.2f", valor.doubleValue() ) + ", data=" + LocalDate.fromDateFields( data ).toString( "dd/MM/yyyy", Locale.getDefault() ) + ", origem=" + origem + '}';
    }

    @Override
    public int compareTo( MovimentacaoParcelada o ) {
        return this.data.compareTo( o.getData() );
    }

}
