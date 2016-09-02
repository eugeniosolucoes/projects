/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.cliente.crud;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.service.ClienteService;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eugenio
 */
//@RunWith( SpringJUnit4ClassRunner.class )
//@WebAppConfiguration
//@ContextConfiguration( "classpath:/spring/config-test.xml" )
public class ClienteServiceListarTest extends ClienteServiceBaseTest {

    @Autowired
    ClienteService<ClienteDTO> instance;

    public ClienteServiceListarTest() {
    }

    /**
     * Test of listar method, of class ClienteServiceImpl.
     */
    @Test
    public void testListar() {
        System.out.println( "listar" );
        List<ClienteDTO> result = instance.listar();
        assertEquals( list.size(), result.size() );
    }

    /**
     * Test of buscar method, of class ClienteServiceImpl.
     */
    @Test
    public void testBuscar() {
        System.out.println( "buscar" );
        String id = c1.getId();
        ClienteDTO result = instance.buscar( id );
        assertEquals( id, result.getId() );
    }

}
