/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model.dto;

import br.com.eugeniosolucoes.repository.Entidade;

/**
 *
 * @author eugenio
 * @param <T>
 */
public abstract class BaseDTO<T extends Entidade> {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null || this.id.isEmpty();
    }

    public abstract T builder();

}
