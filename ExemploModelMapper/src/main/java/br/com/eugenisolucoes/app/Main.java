/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.app;

import br.com.eugenisolucoes.dto.ClienteDTO;
import br.com.eugenisolucoes.model.Cliente;
import br.com.eugenisolucoes.model.Endereco;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;

/**
 *
 * @author eugenio
 */
public class Main {

    public static void main( String... args ) throws Exception {

        Cliente cliente = new Cliente( "02427675750", "alexandre eugenio" );

        cliente.setEndereco( new Endereco( "Rua Tejupa", "Vila da Penha" ) );

        cliente.adicionarTelefone( 21, "30149240" );

        cliente.adicionarTelefone( 21, "99440517" );

        ModelMapper mapper = new ModelMapper();

        ClienteDTO cdto = mapper.map( cliente, ClienteDTO.class );

        System.out.println( cdto );

        System.out.println();

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println( objectMapper.writeValueAsString( cdto ) );

        System.out.println();

        JAXBContext context = JAXBContext.newInstance( ClienteDTO.class );

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        marshaller.marshal( cdto, System.out );
    }

}
