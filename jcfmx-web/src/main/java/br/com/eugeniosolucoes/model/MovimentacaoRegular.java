/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

import br.com.eugeniosolucoes.model.types.TipoMovimentacao;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class MovimentacaoRegular extends Movimentacao {

    protected MovimentacaoRegular() {
    }

    public MovimentacaoRegular( String descricao, BigDecimal valor, TipoMovimentacao tipo ) {
        super( descricao, valor, tipo );
    }

    public MovimentacaoRegular( String descricao, double valor, TipoMovimentacao tipo ) {
        super( descricao, valor, tipo );
    }

}
