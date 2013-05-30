/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.apoio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.sanger.modelo.IEntidade;

/**
 *
 * @author eugenio
 */
@Entity
public class Endereco implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String logradouro;

    private String numero;

    private String complemento;

    private String cep;

    private String bairro;

    private String cidade;

    private Estado estado;

    public Endereco() {
        estado = Estado.RJ;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro( String bairro ) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento( String complemento ) {
        this.complemento = complemento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado( Estado estado ) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro( String logradouro ) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero( String numero ) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade( String cidade ) {
        this.cidade = cidade;
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
        if ( !( object instanceof Endereco ) ) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.apoio.Endereco[ id=" + id + " ]";
    }

    public String getCep() {
        return cep;
    }

    public void setCep( String cep ) {
        this.cep = cep;
    }
}
