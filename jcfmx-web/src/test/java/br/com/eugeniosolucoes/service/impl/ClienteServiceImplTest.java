/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.impl;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.model.dto.ContaDTO;
import br.com.eugeniosolucoes.service.ClienteService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author eugenio
 */
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration( "classpath:/spring/config-test.xml" )
public class ClienteServiceImplTest {

    @Autowired
    ClienteService<ClienteDTO> instance;

    public ClienteServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class ClienteServiceImpl.
     */
    @Test
    public void testSalvar() {
        System.out.println( "salvar" );
        List<ClienteDTO> list = instance.listar();
        for ( ClienteDTO cdto : list ) {
            System.out.println( cdto );
            for ( ContaDTO contaDTO : cdto.getContas() ) {
                System.out.println( contaDTO );
            }
        }
    }
}
