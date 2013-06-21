/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import modelo.jpa.Praca;

/**
 *
 * @author eugenio
 */
public class PracaDAO extends AbstractDAO<Praca> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PracaDAO() {
        super( Praca.class );
        factory = Persistence.createEntityManagerFactory( AbstractDAO.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
