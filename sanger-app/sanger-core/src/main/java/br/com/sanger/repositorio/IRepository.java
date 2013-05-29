/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio;

import java.util.List;

/**
 *
 * @author eugenio
 */
public interface IRepository<T> {

    int contar() throws Exception;

    void criar( T entity ) throws Exception;

    void editar( T entity ) throws Exception;

    void excluir( T entity ) throws Exception;

    T retornar( Object id ) throws Exception;

    List<T> retornarFaixa( int[] range ) throws Exception;

    List<T> listar() throws Exception;
}
