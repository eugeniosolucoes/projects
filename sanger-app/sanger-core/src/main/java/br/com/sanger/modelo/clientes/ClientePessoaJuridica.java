/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.clientes;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.Juridica;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author eugenio
 */
@Entity
public class ClientePessoaJuridica extends Cliente implements Juridica, Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    @Column( unique = true )
    private String nome;

    @Column( name = "INSCRICAO_ESTADUAL" )
    private String inscricaoEstadual;

    public ClientePessoaJuridica() {
    }

    public ClientePessoaJuridica( String nome, String cnpj ) {
        this.nome = nome;
        this.cnpj = cnpj;
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
        if ( !( object instanceof ClientePessoaJuridica ) ) {
            return false;
        }
        ClientePessoaJuridica other = (ClientePessoaJuridica) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.clientes.ClientePessoaJuridica[ id=" + id + " ]";
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getInscricaoMunicipal() {
        return inscricaoEstadual;
    }

    public void setCnpj( String cnpj ) {
        this.cnpj = cnpj;
    }

    @Override
    public void setInscricaoMunicipal( String inscricaoMunicipal ) {
        this.inscricaoEstadual = inscricaoMunicipal;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual( String inscricaoEstadual ) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
    
    
}
