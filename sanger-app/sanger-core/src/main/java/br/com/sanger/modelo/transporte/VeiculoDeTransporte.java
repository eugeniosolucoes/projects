/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.apoio.Estado;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author cpo-202
 */
@Entity
public class VeiculoDeTransporte extends Veiculo {

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false, orphanRemoval = true )
    private Proprietario proprietario;

    private String cidade;

    private Estado estado;

    public String getCidade() {
        return cidade;
    }

    public void setCidade( String cidade ) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado( Estado estado ) {
        this.estado = estado;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario( Proprietario proprietario ) {
        this.proprietario = proprietario;
    }
}
