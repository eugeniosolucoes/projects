/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo;

/**
 *
 * @author eugenio
 */
public interface IEntidade<T> {

    T getId();

    void setId( T id );
}
