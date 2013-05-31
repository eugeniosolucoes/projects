/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.clientes.ClientePessoaJuridica;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author cpo-202
 */
public class ClientePessoaJuridicaServiceTest {
    
    public ClientePessoaJuridicaServiceTest() {
    }

    //@Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        ClientePessoaJuridica obj = new ClientePessoaJuridica("empresa", "cnpj123");
        ClientePessoaJuridica obj1 = new ClientePessoaJuridica("empresa1", "cnpj1234");
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
        ClientePessoaJuridicaService instance = new ClientePessoaJuridicaService();
        List<ClientePessoaJuridica> lista = instance.listar();

        for(ClientePessoaJuridica obj : lista){
            
            obj.setNome(obj.getNome() + " alt-pj ");

            instance.editar(obj);
        }

    }
    
    //@Test
    public void testListar() throws Exception {
        System.out.println("listar");
        ClientePessoaJuridicaService instance = new ClientePessoaJuridicaService();
        List<ClientePessoaJuridica> lista = instance.listar();

        for(ClientePessoaJuridica obj : lista){
            
            System.out.println(obj.getNome());
            System.out.println(obj.getEndereco().getLogradouro());
        }
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        ClientePessoaJuridicaService instance = new ClientePessoaJuridicaService();
        List<ClientePessoaJuridica> lista = instance.listar();

        for(ClientePessoaJuridica obj : lista){
            
            instance.excluir(obj);
        }
        
    }
}
