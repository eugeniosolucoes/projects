/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.apoio.Endereco;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author eugenio
 */
@Entity
public class Destinatario implements IEntidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false )
    private Endereco endereco;

    @Column( name = "TELEFONE_RESIDENCIAL" )
    private String telefoneResidencial;

    @Column( name = "TELEFONE_MOVEL" )
    private String telefoneMovel;

    @Column( name = "TELEFONE_COMERCIAL" )
    private String telefoneComercial;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Destinatario ) ) {
            return false;
        }
        Destinatario other = (Destinatario) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.clientes.Destinatario[ id=" + id + " ]";
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId( Long id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco( Endereco endereco ) {
        this.endereco = endereco;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial( String telefoneResidencial ) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneMovel() {
        return telefoneMovel;
    }

    public void setTelefoneMovel( String telefoneMovel ) {
        this.telefoneMovel = telefoneMovel;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial( String telefoneComercial ) {
        this.telefoneComercial = telefoneComercial;
    }
}
