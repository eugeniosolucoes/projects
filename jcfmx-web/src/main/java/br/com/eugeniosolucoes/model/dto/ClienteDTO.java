/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model.dto;

import br.com.eugeniosolucoes.model.Cliente;
import br.com.eugeniosolucoes.model.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eugenio
 */
public class ClienteDTO extends BaseDTO<Cliente> {

    @NotEmpty( message = "{cliente.nome.requiredo}" )
    private String nome;

    @NotEmpty( message = "{cliente.email.requiredo}" )
    @Pattern( regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "{cliente.email.invalido}" )
    private String email;

    List<ContaDTO> contas = new ArrayList<>();

    public ClienteDTO() {
    }

    public ClienteDTO( String nome, String email ) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setContas( List<ContaDTO> contas ) {
        this.contas = contas;
    }

    public List<ContaDTO> getContas() {
        return contas;
    }

    @Override
    public Cliente builder() {
        Cliente cliente = new Cliente( this.nome, this.email );
        if ( !isNew() ) {
            cliente.setId( Long.valueOf( this.id ) );
        }
        return cliente;
    }

    public static ClienteDTO builder( Cliente entidade ) {
        ClienteDTO clienteDTO = new ClienteDTO( entidade.getNome(), entidade.getEmail() );
        if ( entidade.getId() != null ) {
            clienteDTO.setId( entidade.getId().toString() );
            for ( Conta conta : entidade.getContas() ) {
                clienteDTO.getContas().add( ContaDTO.builder( conta ) );
            }
        }
        return clienteDTO;
    }

    @Override
    public String toString() {
        return String.format( "%s (%s)", this.nome, this.email );
    }

}
