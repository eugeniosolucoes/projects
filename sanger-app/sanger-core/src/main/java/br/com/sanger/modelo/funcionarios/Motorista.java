/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.funcionarios;

import br.com.sanger.modelo.Fisica;
import br.com.sanger.modelo.transporte.Transporte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author eugenio
 */
@Entity
public class Motorista extends Funcionario implements Fisica, Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;

    private String habilitacao;

    private String pronturario;
    
    private String nome;    

    public Motorista() {
    }

    public Motorista( String nome ) {
        this.nome = nome;
    }

    public Motorista( String nome, String cpf ) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @OneToMany( mappedBy = "motorista" )
    private List<Transporte> servicos;

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.Motorista[ id=" + id + " ]";
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao( String habilitacao ) {
        this.habilitacao = habilitacao;
    }

    public String getPronturario() {
        return pronturario;
    }

    public void setPronturario( String pronturario ) {
        this.pronturario = pronturario;
    }

    @Override
    public List<Transporte> getServicos() {
        return servicos;
    }

    @Override
    public void setServicos( List<Transporte> servicos ) {
        this.servicos = servicos;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
    
}
