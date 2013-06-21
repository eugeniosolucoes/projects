/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.List;
import modelo.jpa.Praca;
import org.junit.Test;
import static org.junit.Assert.*;
import servico.impl.PracaServicoImpl;

/**
 *
 * @author eugenio
 */
public class PracaServicoTest {
    
    public PracaServicoTest() {
    }

    //@Test
    public void testListar() {
        System.out.println( "listar" );
        PracaServico instance = new PracaServicoImpl();
        List expResult = null;
        List result = instance.listar();
        assertEquals( expResult, result );
        fail( "The test case is a prototype." );
    }

    //@Test
    public void testRetornar() {
        System.out.println( "retornar" );
        Object id = null;
        PracaServico instance = new PracaServicoImpl();
        Praca expResult = null;
        Praca result = instance.retornar( id );
        assertEquals( expResult, result );
        fail( "The test case is a prototype." );
    }
}
