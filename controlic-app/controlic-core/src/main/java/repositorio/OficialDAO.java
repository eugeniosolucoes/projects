/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import modelo.jpa.Oficial;
import modelo.jpa.Praca;

/**
 *
 * @author eugenio
 */
public class OficialDAO extends AbstractDAO<Oficial> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficialDAO() {
        super( Oficial.class );
        factory = Persistence.createEntityManagerFactory( AbstractDAO.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
