/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class ClientePessoaFisicaServiceTest {

    public ClientePessoaFisicaServiceTest() {
    }

    //@Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        ClientePessoaFisica obj = new ClientePessoaFisica("eugenio", "123");
        ClientePessoaFisica obj1 = new ClientePessoaFisica("eugenio1", "1234");
        obj.setEndereco(new Endereco());
        obj.getEndereco().setLogradouro("rua a");
        obj1.setEndereco(new Endereco());
        obj1.getEndereco().setLogradouro("rua b");
        ClienteService instance = new ClienteService();
        instance.salvar(obj);
        instance.salvar(obj1);
    }

    //@Test
    public void testEditar() throws Exception {
        System.out.println("editar");
        ClientePessoaFisicaService instance = new ClientePessoaFisicaService();
        List<ClientePessoaFisica> lista = instance.listar();

        for ( ClientePessoaFisica pessoaFisica : lista ) {

            pessoaFisica.setNome(pessoaFisica.getNome() + " alt ");

            instance.editar(pessoaFisica);
        }

    }

   // @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        ClientePessoaFisicaService instance = new ClientePessoaFisicaService();
        List<ClientePessoaFisica> lista = instance.listar();
        for ( ClientePessoaFisica obj : lista ) {
            System.out.println(obj.getNome());
            System.out.println(obj.getEndereco().getLogradouro());
        }
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        ClientePessoaFisicaService instance = new ClientePessoaFisicaService();
        List<ClientePessoaFisica> lista = instance.listar();

        for ( ClientePessoaFisica pessoaFisica : lista ) {

            instance.excluir(pessoaFisica);
        }

    }

}
