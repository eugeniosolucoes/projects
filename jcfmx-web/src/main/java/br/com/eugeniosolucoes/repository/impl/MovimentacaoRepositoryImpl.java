/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.repository.impl;

import br.com.eugeniosolucoes.model.Movimentacao;
import br.com.eugeniosolucoes.repository.MovimentacaoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovimentacaoRepositoryImpl extends AbstractRepository<Movimentacao, Long> implements MovimentacaoRepository {

    public MovimentacaoRepositoryImpl() {
        super( Movimentacao.class );
    }

}
