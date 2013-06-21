/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import java.util.List;
import modelo.jpa.Praca;
import repositorio.PracaDAO;
import servico.PracaServico;

/**
 *
 * @author eugenio
 */
public class PracaServicoImpl implements PracaServico {

    private PracaDAO dao;
    
    public PracaServicoImpl() {
        dao = new PracaDAO();
    }

    @Override
    public List<Praca> listar() {
        return dao.listar();
    }

    @Override
    public Praca retornar( Object id ) {
        return dao.retornar( id );
    }
    
}
