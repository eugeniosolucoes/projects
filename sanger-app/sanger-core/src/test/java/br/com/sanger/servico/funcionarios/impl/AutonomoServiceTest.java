/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.funcionarios.impl;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.funcionarios.Autonomo;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class AutonomoServiceTest {
    
    public AutonomoServiceTest() {
    }

    //@Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Autonomo obj = new Autonomo("autonomo", "123");
        Autonomo obj1 = new Autonomo("autonomo1", "1234");
        obj.setEndereco(new Endereco());
        obj.getEndereco().setLogradouro("rua a");
        obj1.setEndereco(new Endereco());
        obj1.getEndereco().setLogradouro("rua b");
        AutonomoService instance = new AutonomoService();
        instance.salvar(obj);
        instance.salvar(obj1);
    }

    //@Test
    public void testEditar() throws Exception {
        System.out.println("editar");
        AutonomoService instance = new AutonomoService();
        List<Autonomo> lista = instance.listar();

        for(Autonomo obj : lista){
            
            obj.setNome(obj.getNome() + " alt ");

            instance.editar(obj);
        }

    }
    
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        AutonomoService instance = new AutonomoService();
        List<Autonomo> lista = instance.listar();

        for(Autonomo obj : lista){
            
            System.out.println(obj.getNome());
            System.out.println(obj.getEndereco().getLogradouro());
        }
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        AutonomoService instance = new AutonomoService();
        List<Autonomo> lista = instance.listar();

        for(Autonomo obj : lista){
            
            instance.excluir(obj);
        }
        
    }
    

}
