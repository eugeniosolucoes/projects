/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.jpa.Licenca;
import modelo.jpa.Militar;

/**
 *
 * @author eugenio
 */
public class LicencaDAO extends AbstractDAO<Licenca> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicencaDAO() {
        super( Licenca.class );
        factory = Persistence.createEntityManagerFactory( AbstractDAO.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public List<Licenca> listarPorAnoMes( Integer ano, Integer mes ) {
        Query query = em.createQuery( "SELECT DISTINCT L FROM Licenca L "
                + "WHERE EXTRACT(YEAR FROM L.dataLicenca) = ?1 "
                + "AND EXTRACT(MONTH FROM L.dataLicenca) = ?2 " );
        query.setParameter( 1, ano );
        query.setParameter( 2, mes );
        return query.getResultList();
    }

    public List<Licenca> listarPorMilitar( Militar militar ) {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT lic FROM Licenca lic "
                + "WHERE lic.militar = ?1 " );
        query.setParameter( 1, militar );
        return query.getResultList();
    }

    public List<Integer> listarAnos() {
        Query query = em.createQuery( "SELECT DISTINCT CAST(EXTRACT(YEAR FROM "
                + "L.dataLicenca)AS INT) FROM Licenca L" );
        List<Integer> lista = query.getResultList();
        return lista;
    }
}