/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.cliente.crud;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.service.ClienteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eugenio
 */
//@RunWith( SpringJUnit4ClassRunner.class )
//@WebAppConfiguration
//@ContextConfiguration( "classpath:/spring/config-test.xml" )
public class ClienteServiceIncluirTest extends ClienteServiceBaseTest {

    @Autowired
    ClienteService<ClienteDTO> instance;

    public ClienteServiceIncluirTest() {
    }

    @Test
    public void testSalvar() {
        System.out.println( "salvar" );
        for ( ClienteDTO c : list ) {
            instance.salvar( c );
        }
    }

}
