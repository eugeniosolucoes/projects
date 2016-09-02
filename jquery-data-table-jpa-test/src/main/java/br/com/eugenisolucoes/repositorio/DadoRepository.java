/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.repositorio;

import br.com.eugenisolucoes.datatable.InputDataTable;
import br.com.eugenisolucoes.modelo.Dado;
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
        
        sql += inputDataTable.getSearchSQL( "d" );
        
        sql += inputDataTable.getSortSQL( "d" );
        
        String sqlTotal = "SELECT COUNT(d) FROM Dado d WHERE 1=1 "  + 
                inputDataTable.getSearchSQL( "d" );

        Long total = (Long) em.createQuery( sqlTotal ).getSingleResult();

        inputDataTable.setiTotalRecords( total.intValue() );

        TypedQuery<Dado> query = em.createQuery( sql, Dado.class );

        query.setMaxResults( inputDataTable.getiDisplayLength() );

        query.setFirstResult( inputDataTable.getiDisplayStart() );

        return query.getResultList();
    }
}
