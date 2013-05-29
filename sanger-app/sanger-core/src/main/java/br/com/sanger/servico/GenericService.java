/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico;

import java.util.List;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.repositorio.AbstractRepository;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public abstract class GenericService<T extends IEntidade> {

    protected AbstractRepository<T> dao;

    public GenericService( AbstractRepository<T> dao ) {
        this.dao = dao;
    }

    public List<T> listar() throws Exception {
        return dao.listar();
    }

    public void editar( T obj ) throws Exception {
        validacao( obj );
        dao.editar( obj );
    }

    public void excluir( T obj ) throws Exception {
        obj = dao.retornar( obj.getId() );
        if ( obj != null ) {
            dao.excluir( obj );
        }
    }

    public void salvar( T obj ) throws Exception {
        try {
            validacao( obj );
            if ( obj.getId() == null ) {
                dao.criar( obj );
            } else {
                dao.editar( obj );
            }

        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    public T retornar( Object id ) throws Exception {
        return dao.retornar( id );
    }

    /**
     * Este metodo deve lancar uma excecao caso os atributos sejam invalidos!
     *
     * @param obj
     * @throws Exception
     */
    public abstract void validacao( T obj ) throws Exception;

    /**
     * Este metodo eh um helper para mensagens de excecao.
     *
     * @param e
     * @param param
     * @param msg
     * @throws Exception
     */
    public void tratarMensagemDeExcecao( Exception e, String param, String msg ) throws Exception {
        if ( e.getMessage().toLowerCase().indexOf( param.toLowerCase() ) != -1 ) {
            throw new Exception( msg );
        }
    }
}
