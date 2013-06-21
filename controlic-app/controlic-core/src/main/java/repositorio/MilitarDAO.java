/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.jpa.Militar;

/**
 *
 * @author eugenio
 */
public class MilitarDAO extends AbstractDAO<Militar> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilitarDAO() {
        super( Militar.class );
        factory = Persistence.createEntityManagerFactory( AbstractDAO.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public Militar retornarPorNip( String nip ) {
        getEntityManager().clear();
        TypedQuery<Militar> query = em.createQuery(
                "SELECT m FROM Militar m WHERE m.nip = :nip", Militar.class );
        return query.setParameter( "nip", nip ).getSingleResult();
    }
}
