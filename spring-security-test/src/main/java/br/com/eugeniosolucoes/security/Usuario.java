/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.security;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author eugenio
 */
@Entity
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "O nome não pode estar em branco!" )
    @NotEmpty( message = "O nome não pode estar vazio!" )
    @Size( min = 5, max = 30 )
    @Column( nullable = false, unique = true )
    private String nome;

    private String senha;

    private boolean ativo;

    protected Usuario() {
    }

    public Usuario( String nome, String senha ) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario( String nome, String senha, boolean ativo ) {
        this( nome, senha );
        this.ativo = ativo;
    }

    @ManyToMany
    private List<Perfil> perfis;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + '}';
    }

    public String getPassword() {
        return senha;
    }

    public String getUsername() {
        return nome;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return ativo;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha( String senha ) {
        this.senha = senha;
    }

    /**
     * @return the perfis
     */
    public List<Perfil> getPerfis() {
        return perfis;
    }

    /**
     * @param perfis the perfis to set
     */
    public void setPerfis( List<Perfil> perfis ) {
        this.perfis = perfis;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome( String nome ) {
        this.nome = nome;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo( boolean ativo ) {
        this.ativo = ativo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof Usuario ) ) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if ( nome == null ) {
            if ( other.nome != null ) {
                return false;
            }
        } else if ( !nome.equals( other.nome ) ) {
            return false;
        }
        return true;
    }
}
