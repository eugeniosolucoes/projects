/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.repositorio;

import br.com.eugenisolucoes.datatable.ColumnDataTable;
import br.com.eugenisolucoes.datatable.InputDataTable;
import br.com.eugenisolucoes.modelo.Dado;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author cpo-202
 */
public class DadoRepository extends AbstractRepository<Dado> {

    public DadoRepository() {
        super( Dado.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Dado> listar( InputDataTable inputDataTable ) {

        String sql = "SELECT d FROM Dado d WHERE 1=1 ";
        
        sql += inputDataTable.getSortSQL( "d" );

        Long total = (Long) em.createQuery( "SELECT COUNT(d) FROM Dado d " ).getSingleResult();

        inputDataTable.setiTotalRecords( total.intValue() );

        TypedQuery<Dado> query = em.createQuery( sql, Dado.class );

        query.setMaxResults( inputDataTable.getiDisplayLength() );

        query.setFirstResult( inputDataTable.getiDisplayStart() );

        return query.getResultList();
    }
}
