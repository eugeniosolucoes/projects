/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.cliente.crud;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.service.ClienteService;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eugenio
 */
//@RunWith( SpringJUnit4ClassRunner.class )
//@WebAppConfiguration
//@ContextConfiguration( "classpath:/spring/config-test.xml" )
public class ClienteServiceExcluirTest extends ClienteServiceBaseTest {

    @Autowired
    ClienteService<ClienteDTO> instance;

    public ClienteServiceExcluirTest() {
    }

    @AfterClass
    public static void tearDownClass() {
        list.clear();
    }

    @Test
    public void testExcluir() {
        System.out.println( "excluir" );
        instance.excluir( c1 );
        instance.excluir( c2.getId() );
    }

}
