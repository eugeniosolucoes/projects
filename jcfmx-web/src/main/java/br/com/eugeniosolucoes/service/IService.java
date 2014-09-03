/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service;

import br.com.eugeniosolucoes.repository.Entidade;
import java.util.List;

/**
 *
 * @author eugenio
 * @param <T>
 * @param <K>
 */
public interface IService<T extends Entidade<K>, K> {

    T salvar( T obj );

    void excluir( T obj );

    void excluir( K id );

    List<T> listar();

}
