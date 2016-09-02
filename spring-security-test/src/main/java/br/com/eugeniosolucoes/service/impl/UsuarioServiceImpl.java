/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.impl;

import br.com.eugeniosolucoes.repository.UsuarioRepository;
import br.com.eugeniosolucoes.security.Usuario;
import br.com.eugeniosolucoes.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugenio
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService, AuthenticationManager {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void autenticar( String nome, String senha ) {
        Usuario usuario = usuarioRepository.consultarUsuarioPorNome( nome );
        Authentication request = new UsernamePasswordAuthenticationToken( usuario, senha );
        authenticate( request );
        SecurityContextHolder.getContext().setAuthentication( request );
    }

    public UserDetails loadUserByUsername( String string ) throws UsernameNotFoundException {
        return usuarioRepository.consultarUsuarioPorNome( string );
    }

    @Transactional
    public Usuario salvar( Usuario usuario ) {
        return usuarioRepository.save( usuario );
    }

    public String getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails ) {
            return ( (Usuario) principal ).getNome();
        } else {
            return "Principal: " + principal.toString();
        }
    }

    public Authentication authenticate( Authentication auth ) throws AuthenticationException {
        if ( ( (UserDetails) auth.getPrincipal() ).getPassword().equals( auth.getCredentials() ) ) {
            Authentication authentication = new UsernamePasswordAuthenticationToken( auth.getName(),
                    auth.getCredentials(), new ArrayList<GrantedAuthority>() );
            return authentication;
        }
        throw new BadCredentialsException( "Usuário ou senha inválidos!" );
    }

    public Usuario getUsuarioPorNome( String nome ) {
        return usuarioRepository.consultarUsuarioPorNome( nome );
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listar( int page, int size ) {
        return usuarioRepository.findAll( new PageRequest( page, size ) ).getContent();
    }

    public Usuario getUsuario( Long id ) {
        return usuarioRepository.findOne( id );
    }
}
