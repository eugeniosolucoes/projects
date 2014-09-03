/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.impl;

import br.com.eugeniosolucoes.model.Movimentacao;
import br.com.eugeniosolucoes.repository.MovimentacaoRepository;
import br.com.eugeniosolucoes.service.MovimentacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository repository;

    @Autowired
    public MovimentacaoServiceImpl( MovimentacaoRepository repository ) {
        this.repository = repository;
    }

    @Override
    public Movimentacao salvar( Movimentacao obj ) {
        return repository.salvar( obj );
    }

    @Override
    public void excluir( Movimentacao obj ) {
        repository.excluir( obj );
    }

    @Override
    public void excluir( Long id ) {
        repository.excluir( id );
    }

    @Override
    public List<Movimentacao> listar() {
        return repository.listar();
    }

}
