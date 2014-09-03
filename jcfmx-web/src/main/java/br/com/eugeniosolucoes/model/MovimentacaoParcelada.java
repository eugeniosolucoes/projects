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
public class MovimentacaoParcelada extends Movimentacao {

    private int parcela;

    protected MovimentacaoParcelada() {
    }

    public MovimentacaoParcelada( String descricao, BigDecimal valor, TipoMovimentacao tipo ) {
        super( descricao, valor, tipo );
    }

    public MovimentacaoParcelada( String descricao, double valor, TipoMovimentacao tipo ) {
        super( descricao, valor, tipo );
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela( int parcela ) {
        this.parcela = parcela;
    }

}
