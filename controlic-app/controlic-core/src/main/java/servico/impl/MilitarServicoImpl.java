/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import java.util.List;
import modelo.jpa.Militar;
import modelo.jpa.Praca;
import repositorio.MilitarDAO;
import servico.MilitarServico;

/**
 *
 * @author eugenio
 */
public class MilitarServicoImpl implements MilitarServico {

    private MilitarDAO dao;
    
    public MilitarServicoImpl() {
        dao = new MilitarDAO();
    }

    @Override
    public List<Militar> listar() {
        return dao.listar();
    }

    @Override
    public Militar retornar( Object id ) {
        return dao.retornar( id );
    }
    
}
