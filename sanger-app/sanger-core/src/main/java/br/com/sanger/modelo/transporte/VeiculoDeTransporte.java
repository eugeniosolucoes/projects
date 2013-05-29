/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.apoio.Endereco;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author cpo-202
 */
@Entity
public class VeiculoDeTransporte extends Veiculo {

    private String proprietario;

    @OneToOne
    private Endereco endereco;

    @Column( name = "TELEFONE_RESIDENCIAL" )
    private String telefoneResidencial;

    @Column( name = "TELEFONE_MOVEL" )
    private String telefoneMovel;

    @Column( name = "TELEFONE_COMERCIAL" )
    private String telefoneComercial;

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario( String proprietario ) {
        this.proprietario = proprietario;
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
