/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model.types;

/**
 *
 * @author eugenio
 */
public enum TipoMovimentacao {

    ENTRADA( "Entrada" ), 
    SAIDA( "Saída" );

    private final String descricao;

    private TipoMovimentacao( String descricao ) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
