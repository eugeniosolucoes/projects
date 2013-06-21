/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import modelo.acesso.Usuario;

/**
 *
 * @author eugenio
 */
@Entity
public abstract class Militar implements Usuario, Serializable {

    private static final long serialVersionUID = -6249717659487117182L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    @Column( unique = true )
    protected String nome;

    @Column( unique = true )
    protected String nip;

    @Column( name = "nome_guerra" )
    protected String nomeDeGuerra;

    @OneToMany( mappedBy = "militar", orphanRemoval = true, cascade = CascadeType.REMOVE )
    protected List<Licenca> licencas;

    @Transient
    private String senha;

    protected Militar() {
    }

    public Militar( String nip, String nome ) {
        this.nip = nip;
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getNip() {
        return nip;
    }

    public void setNip( String nip ) {
        this.nip = nip;
    }

    public String getNomeDeGuerra() {
        return nomeDeGuerra;
    }

    public void setNomeDeGuerra( String nomeDeGuerra ) {
        this.nomeDeGuerra = nomeDeGuerra;
    }

    @Override
    public String getLoginNome() {
        return String.format( "%s", this.nomeDeGuerra );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public List<Licenca> getLicencas() {
        return licencas;
    }

    public void setLicencas( List<Licenca> licencas ) {
        this.licencas = licencas;
    }

    @Override
    public void setLogin( String login ) {
        nip = login;
    }

    @Override
    public String getLogin() {
        return nip;
    }

    @Override
    public void setSenha( String senha ) {
        this.senha = senha;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + ( this.id != null ? this.id.hashCode() : 0 );
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
        final Militar other = (Militar) obj;
        if ( this.id != other.id && ( this.id == null || !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }
}
