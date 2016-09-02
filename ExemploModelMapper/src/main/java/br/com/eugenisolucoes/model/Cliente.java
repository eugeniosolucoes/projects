/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author eugenio
 */
public class Cliente {

    private String cpf;

    private String nome;

    private Endereco endereco;

    private Set<Telefone> telefones;

    public Cliente() {
        telefones = new HashSet<>();
    }

    public Cliente( String cpf, String nome ) {
        this();
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf( String cpf ) {
        this.cpf = cpf;
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

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public boolean adicionarTelefone( int codigoDeArea, String numero ) {
        return this.telefones.add( new Telefone( codigoDeArea, numero ) );
    }

    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", telefones=" + telefones + '}';
    }

}
