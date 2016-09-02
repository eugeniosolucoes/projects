/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugenio
 * @param <T>
 */
@Transactional
public interface ClienteService<T extends ClienteDTO> {

    T salvar( T obj );

    T buscar( String id );

    void excluir( T obj );

    void excluir( String id );

    List<T> listar();

}
