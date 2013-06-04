/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.funcionarios.impl;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.funcionarios.Motorista;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author cpo-202
 */
public class MotoristaServiceTest {
    
    public MotoristaServiceTest() {
    }

    //@Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Motorista obj = new Motorista("motorista", "mo-123");
        Motorista obj1 = new Motorista("motorista1", "mo-1234");
        obj.setEndereco(new Endereco());
        obj.getEndereco().setLogradouro("rua a");
        obj1.setEndereco(new Endereco());
        obj1.getEndereco().setLogradouro("rua b");
        MotoristaService instance = new MotoristaService();
        instance.salvar(obj);
        instance.salvar(obj1);
    }

   // @Test
    public void testEditar() throws Exception {
        System.out.println("editar");
        MotoristaService instance = new MotoristaService();
        List<Motorista> lista = instance.listar();

        for(Motorista obj : lista){
            
            obj.setNome(obj.getNome() + " alt-pj ");

            instance.editar(obj);
        }

    }
    
    //@Test
    public void testListar() throws Exception {
        System.out.println("listar");
        MotoristaService instance = new MotoristaService();
        List<Motorista> lista = instance.listar();

        for(Motorista obj : lista){
            
            System.out.println(obj.getNome());
            System.out.println(obj.getEndereco().getLogradouro());
        }
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        MotoristaService instance = new MotoristaService();
        List<Motorista> lista = instance.listar();

        for(Motorista obj : lista){
            
            instance.excluir(obj);
        }
        
    }
}
