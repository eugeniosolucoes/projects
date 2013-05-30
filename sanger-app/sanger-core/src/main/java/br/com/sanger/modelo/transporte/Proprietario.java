/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.Pessoa;
import br.com.sanger.modelo.apoio.Endereco;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author eugenio
 */
@Entity
public class Proprietario extends Pessoa implements Serializable {

    private String nome;
    
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false )
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco( Endereco endereco ) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
    
    
}
