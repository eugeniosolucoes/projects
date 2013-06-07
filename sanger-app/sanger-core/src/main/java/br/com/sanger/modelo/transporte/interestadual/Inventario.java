/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.interestadual;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.transporte.apoio.Localizacao;
import br.com.sanger.modelo.transporte.apoio.Simbolo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author eugenio
 */
@Entity
public class Inventario implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private Integer numero;
    
    private Integer quantidade;

    private Double seguro;

    private String descricao;

    @ManyToOne
    private Localizacao localizacao;

    @ManyToOne
    private Simbolo simbolo;

   
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Inventario ) ) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.inventarios.Bem[ id=" + id + " ]";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao( Localizacao localizacao ) {
        this.localizacao = localizacao;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo( Simbolo simbolo ) {
        this.simbolo = simbolo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade( Integer quantidade ) {
        this.quantidade = quantidade;
    }

    public Double getSeguro() {
        return seguro;
    }

    public void setSeguro( Double seguro ) {
        this.seguro = seguro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero( Integer numero ) {
        this.numero = numero;
    }
    
}
