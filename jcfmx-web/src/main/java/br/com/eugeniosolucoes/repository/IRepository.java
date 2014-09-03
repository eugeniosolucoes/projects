/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.repository;

import java.util.List;

/**
 *
 * @author eugenio
 * @param <T>
 * @param <K>
 */
public interface IRepository<T extends Entidade<K>, K> {

    T salvar( T entity );

    T retornar( K id );

    void excluir( T entity );

    void excluir( K id );

    List<T> listar();

    List<T> retornarFaixa( int[] range );

    int contar();

}
