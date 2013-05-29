/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.funcionarios;

import br.com.sanger.modelo.Fisica;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
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
    private List<TransporteLocal> servicos;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Motorista ) ) {
            return false;
        }
        Motorista other = (Motorista) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

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
    public List<TransporteLocal> getServicos() {
        return servicos;
    }

    @Override
    public void setServicos( List<TransporteLocal> servicos ) {
        this.servicos = servicos;
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
    
}
