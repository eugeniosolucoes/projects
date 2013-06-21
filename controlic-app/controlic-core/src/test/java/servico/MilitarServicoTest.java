/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.List;
import modelo.jpa.Militar;
import static org.junit.Assert.*;
import servico.impl.MilitarServicoImpl;

/**
 *
 * @author eugenio
 */
public class MilitarServicoTest {
    
    public MilitarServicoTest() {
    }

    //@Test
    public void testListar() {
        System.out.println( "listar" );
        MilitarServico instance = new MilitarServicoImpl();
        List expResult = null;
        List result = instance.listar();
        assertEquals( expResult, result );
        fail( "The test case is a prototype." );
    }

    //@Test
    public void testRetornar() {
        System.out.println( "retornar" );
        Object id = null;
        MilitarServico instance = new MilitarServicoImpl();
        Militar expResult = null;
        Militar result = instance.retornar( id );
        assertEquals( expResult, result );
        fail( "The test case is a prototype." );
    }
    
}
