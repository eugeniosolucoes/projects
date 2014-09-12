/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.cliente.crud;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugenio
 */
public class ClienteServiceBaseTest {

    static ClienteDTO c1 = new ClienteDTO( "test1", "test1@email.com" );

    static ClienteDTO c2 = new ClienteDTO( "test2", "test2@email.com" );

    static List<ClienteDTO> list = new ArrayList<>();

    static {
        list.add( c1 );
        list.add( c2 );
    }

}
