/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.cliente.crud;

import br.com.eugeniosolucoes.model.Movimentacao;
import br.com.eugeniosolucoes.model.MovimentacaoParcelada;
import br.com.eugeniosolucoes.model.types.TipoMovimentacao;
import java.math.BigDecimal;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class ParcelasTest {

    @Test
    public void testParcelas() {

        Movimentacao movimentacao = new Movimentacao( "uniformes", new BigDecimal( 1306 ),
                TipoMovimentacao.SAIDA );

        movimentacao.setPrimeiraParcela( LocalDate.parse( "2014-10-11" ).toDate() );

        movimentacao.setParcelas( 3 );

        movimentacao.gerarParcelas();

        for ( MovimentacaoParcelada parcelada : movimentacao.getParceladasOrdenadas() ) {
            System.out.println( parcelada );
        }

        Assert.assertEquals( movimentacao.getParcelas(), movimentacao.getParceladasOrdenadas().size() );

    }

}
