/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.pessoas.funcionarios.impl;

import br.com.sanger.servico.funcionarios.impl.AutonomoService;
import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.funcionarios.Autonomo;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class AutonomoServiceTest {

    public AutonomoServiceTest() {
    }

    @Test
    public void popularAutonomo() throws Exception {
        System.out.println( "populando autonomos" );
        Random random = new Random();
        AutonomoService service = new AutonomoService();
////        int qtd = 100;
//        for ( int i = 1; i < qtd; i++ ) {
//            Autonomo autonomo = new Autonomo();
//            autonomo.setNome( String.format( "Fulano Beltrano %s", random.nextInt( (int) (i + 1e11) ) ) );
//            autonomo.setCpf( String.format( "%s", random.nextInt( (int) (i + 1e11) ) ) );
//            Endereco endereco = new Endereco();
//            endereco.setLogradouro( "rua ciclano");
//            autonomo.setEndereco( endereco );
//            service.salvar( autonomo );
//        }
    }
}
