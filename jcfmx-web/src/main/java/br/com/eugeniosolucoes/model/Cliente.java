/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

import br.com.eugeniosolucoes.repository.Entidade;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe cliente do sistema.
 *
 * @author eugenio
 */
@Entity
public class Cliente implements Entidade<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    @Column( unique = true )
    private String email;

    @OneToMany( mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true )
    private final Set<Conta> contas = new HashSet<>();

    protected Cliente() {
    }

    /**
     * Cria um cliente com nome e email.
     *
     * @param nome nome do cliente.
     * @param email email do cliente.
     */
    public Cliente( String nome, String email ) {
        this.nome = nome;
        this.email = email;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Id do sistema.
     *
     * @return Long ID do sistema.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Nome do cliente.
     *
     * @return String nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera nome do cliente.
     *
     * @param nome String nome do cliente.
     */
    public void setNome( String nome ) {
        this.nome = nome;
    }

    /**
     * Retorna o email do cliente.
     *
     * @return String email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Altera email do cliente.
     *
     * @param email String email do cliente.
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Adiciona uma conta ao cliente.
     *
     * @param conta Nova conta para o cliente.
     * @return Retorna true ou falso ao inserir a conta.
     */
    public boolean adicionarConta( Conta conta ) {
        if ( this.contas.add( conta ) ) {
            conta.setCliente( this );
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna a lista de contas do cliente.
     *
     * @return Conjunto de contas.
     */
    public Set<Conta> getContas() {
        return Collections.unmodifiableSet( contas );
    }

    /**
     * HashCode baseado no email do cliente.
     *
     * @return int hashcode.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode( this.email );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if ( !Objects.equals( this.email, other.email ) ) {
            return false;
        }
        return true;
    }

    /**
     * toString com id + nome + email do cliente.
     *
     * @return String que representa o cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + '}';
    }

}
