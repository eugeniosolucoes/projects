/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.funcionarios;

import br.com.sanger.modelo.Fisica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author eugenio
 */
@Entity
public class Autonomo extends Funcionario implements Fisica, Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    
    @Column( unique = true )
    private String cpf;

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date admissao;

    @Temporal( javax.persistence.TemporalType.DATE )
    private Date demissao;

    @Lob
    @Column( length = 512 )
    private String descricao;

    public Autonomo() {
    }

    public Autonomo( String nome, String cpf ) {
        this.nome = nome;
        this.cpf = cpf;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Autonomo ) ) {
            return false;
        }
        Autonomo other = (Autonomo) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sanger.modelo.pessoas.Autonomo[ id=" + id + " ]";
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao( Date admissao ) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao( Date demissao ) {
        this.demissao = demissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
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
