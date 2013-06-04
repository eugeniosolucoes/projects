/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class ClienteServiceTest {

    public ClienteServiceTest() {
    }

    //@Test
    public void testListar() throws Exception {
        System.out.println( "listar" );
        ClienteService instance = new ClienteService();
        List<Cliente> lista = instance.listar();

        for ( Cliente obj : lista ) {

            System.out.println( obj.getNome() );
            System.out.println( obj.getEndereco().getLogradouro() );
        }
    }

    //@Test
    public void testGetObject() throws Exception {
        System.out.println( "getobject" );
        ClienteService instance = new ClienteService();
        List<Cliente> lista = instance.listar();

        ClientePessoaFisica pf = new ClientePessoaFisica();
        pf.setId( 3L);
        Cliente obj = (Cliente) getEntidade( pf, lista );

        System.out.println( obj.getNome() );
    }

    private <E extends IEntidade> IEntidade getEntidade( Object id, List<E> lista ) throws Exception {
        E objeto = lista.get( lista.indexOf( id ) );
        return objeto;
    }
    
}
