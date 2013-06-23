/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import modelo.jpa.Licenca;
import modelo.jpa.Militar;
import modelo.jpa.Oficial;
import modelo.jpa.Praca;
import org.json.JSONArray;
import org.json.JSONException;
import static org.junit.Assert.*;
import org.junit.Test;
import repositorio.MilitarDAO;
import servico.impl.LicencaServicoImpl;
import servico.impl.MilitarServicoImpl;

/**
 *
 * @author eugenio
 */
public class LicencaServicoTest {

    public LicencaServicoTest() {
    }

    @Test
    public void testCriarMilitares() {
        System.out.println( "criarMilitares" );

        MilitarDAO dao = new MilitarDAO();

        Oficial of1 = new Oficial( Oficial.Posto.CAPITAO_TENENTE, "96094265",
                "Márcia Maria Tenório Kouzmine", "Márcia Tenório" );

        Oficial of2 = new Oficial( Oficial.Posto.CAPITAO_TENENTE, "02204959",
                "Luciana Rodrigues de Almeida Saboia", "Luciana Almeida" );

        try {
            dao.criar( of1 );
            dao.criar( of2 );
        } catch ( Exception e ) {
        }


        Praca p1 = new Praca( Praca.Graduacao.PRIMEIRO_SARGENTO,
                "86795694", "Alexandre Eugênio da Silva", "Eugênio" );

        Praca p2 = new Praca( Praca.Graduacao.PRIMEIRO_SARGENTO,
                "05866243", "Evaldo Pereira Rêgo", "Evaldo" );

        Praca p3 = new Praca( Praca.Graduacao.SEGUNDO_SARGENTO,
                "97096890", "Madalena de Jesus Vieira Nogueira", "Madalena" );

        Praca p4 = new Praca( Praca.Graduacao.SEGUNDO_SARGENTO,
                "99232456", "Alessandra Queres Barboza", "Alessandra Queres" );

        Praca p5 = new Praca( Praca.Graduacao.SEGUNDO_SARGENTO,
                "00117781", "Felipe Augusto Pinheiro Rodrigues", "Augusto" );

        Praca p6 = new Praca( Praca.Graduacao.TERCEIRO_SARGENTO,
                "02204941", "Larissa Gonçalves de Menezes", "Larissa" );

        Praca p7 = new Praca( Praca.Graduacao.TERCEIRO_SARGENTO,
                "99187281", "Anderson de Souza Leite", "Leite" );

        Praca p8 = new Praca( Praca.Graduacao.CABO,
                "86115847", "André Luiz Pires Ferreira de Araújo", "André" );

        Praca[] pracas = { p1, p2, p3, p4, p5, p6, p7, p8 };

        for ( Praca p : pracas ) {
            try {
                dao.criar( p );
            } catch ( Exception e ) {
            }
        }
    }

    //@Test
    public void testSalvar() {
        System.out.println( "salvar" );

        MilitarServico ps = new MilitarServicoImpl();
        LicencaServico ls = new LicencaServicoImpl();

        Militar militar = ps.retornar( 1L );

        Licenca obj = new Licenca( "teste" );

        obj.setMilitar( militar );

        ls.salvar( obj );
    }
    
    @Test
    public void testTamanhoMotivo(){
        LicencaServicoImpl servico = new LicencaServicoImpl();
        Licenca licenca = new Licenca( "hospital");
        Oficial oficial = new Oficial( "77777");
        oficial.setId( Long.MIN_VALUE );
        licenca.setMilitar( oficial );
        servico.validacao( licenca );
    }

    //@Test
    public void testExcluir() {
        System.out.println( "excluir" );
        Licenca obj = null;
        LicencaServicoImpl instance = new LicencaServicoImpl();
        instance.excluir( obj );
        fail( "The test case is a prototype." );
    }

    @Test
    public void testListarPorAnoMes() {
        System.out.println( "listarPorAnoMes" );
        LicencaServicoImpl instance = new LicencaServicoImpl();
        List<Licenca> result = instance.listarPorAnoMes( 2013, 6 );
        assertNotEquals( null, result );
        for ( Licenca lic : result ) {
            System.out.println( lic.getDataLicenca() );

        }
    }

    @Test
    public void testListarPorAnoMesJSON() throws JSONException {
        System.out.println( "listarPorAnoMesJSON" );
        LicencaServicoImpl instance = new LicencaServicoImpl();
        JSONArray result = instance.listarPorAnoMesJSON( 2013, 6 );
        System.out.println( result );
    }

    //@Test
    public void testListarPorMilitar() {
        System.out.println( "listarPorMilitar" );
        MilitarServico ps = new MilitarServicoImpl();
        LicencaServico ls = new LicencaServicoImpl();
        Militar obj = ps.retornar( 1L );
        List expResult = null;
        List result = ls.listarPorMilitar( obj );
        assertNotEquals( expResult, result );
    }

}
