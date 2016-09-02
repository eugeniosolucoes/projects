/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.repository;

import br.com.eugeniosolucoes.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author eugenio
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query( "select o from Usuario o where o.nome = :nome " )
    Usuario consultarUsuarioPorNome( @Param("nome") String nome );
}
