/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.clientes;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.Fisica;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author eugenio
 */
@Entity
public class ClientePessoaFisica extends Cliente implements Fisica, Serializable {

    private static final long serialVersionUID = 1L;

    @Column( unique = true )
    private String cpf;

    public ClientePessoaFisica() {
    }

    public ClientePessoaFisica( String nome ) {
        this.nome = nome;
    }

    public ClientePessoaFisica( String nome, String cpf ) {
        this( nome );
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.clientes.ClientePessoaFisica[ id=" + id + " ]";
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }
    
    
}
