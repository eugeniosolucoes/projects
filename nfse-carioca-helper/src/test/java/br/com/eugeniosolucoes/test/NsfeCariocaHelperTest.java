/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.test;

import br.com.eugeniosolucoes.nfse.model.ConsultarSituacaoLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.model.EnviarLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.servico.NsfeServico;
import br.com.eugeniosolucoes.nfse.servico.impl.NsfeServicoImpl;
import br.com.eugeniosolucoes.nfse.util.Subscriber;
import br.com.eugeniosolucoes.nfse.util.XmlUtils;
import br.com.eugeniosolucoes.nfse.ws.ConsultarSituacaoLoteRpsRequest;
import br.com.eugeniosolucoes.nfse.ws.ConsultarSituacaoLoteRpsResponse;
import br.com.eugeniosolucoes.nfse.ws.NfseSoap;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eugenio
 */
public class NsfeCariocaHelperTest {

    private static final Logger LOG = LoggerFactory.getLogger( NsfeCariocaHelperTest.class );

    private static final String ARQUIVO_XML = "/my-exemplos/rps_1.xml";

    private static final String XSD1 = "/schemas/tipos_nfse_v01.xsd";

    private static final String XSD2 = "/schemas/xmldsig-core-schema_v01.xsd";

    private final NsfeServico servico = new NsfeServicoImpl();

    /**
     *
     * @throws Exception
     */
    @Test
    public void testCreateObjectFromXml() throws Exception {

        InputStream is = this.getClass().getResourceAsStream( ARQUIVO_XML );

        String xml = XmlUtils.lerArquivo( is );

        EnviarLoteRpsEnvio objeto = XmlUtils.createObjectFromXml( xml, EnviarLoteRpsEnvio.class );

        assertNotNull( objeto );
    }

    @Test
    public void testCreateXmlFromObject() throws Exception {

        InputStream is = this.getClass().getResourceAsStream( ARQUIVO_XML );

        String xml = XmlUtils.lerArquivo( is );

        EnviarLoteRpsEnvio objeto = XmlUtils.createObjectFromXml( xml, EnviarLoteRpsEnvio.class );

        String result = XmlUtils.createXmlFromObject( objeto );

        assertNotNull( result );
    }

    @Test
    public void testValidateXml() throws Exception {

        InputStream is = this.getClass().getResourceAsStream( ARQUIVO_XML );

        String xml = XmlUtils.lerArquivo( is );

        InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
        InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );

        XmlUtils.validateXml( xml, xsd1, xsd2 );

    }

    @Test
    public void testAssinarValidarXml() throws Exception {

        InputStream is = this.getClass().getResourceAsStream( ARQUIVO_XML );

        String xml = XmlUtils.lerArquivo( is );

        String xmlAssinado = Subscriber.getInstance().assinarLoteRps( xml );

        InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
        InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );

        String xmlAssinadoFormatado = XmlUtils.format( xmlAssinado );

        System.out.println( xmlAssinadoFormatado );
        XmlUtils.validateXml( xmlAssinadoFormatado, xsd1, xsd2 );
    }

    @Test( expected = IllegalStateException.class )
    public void testAssinarValidarXmlComErro() throws Exception {
        InputStream is = this.getClass().getResourceAsStream( ARQUIVO_XML );
        String xml = XmlUtils.lerArquivo( is );
        String xmlAssinado = Subscriber.getInstance().assinarLoteRps( xml );
        InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
        InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
        String xmlAssinadoFormatado = XmlUtils.format( xmlAssinado );
        XmlUtils.validateXml( xmlAssinadoFormatado.replace( "<Cnpj>00000000000000</Cnpj>", "" ), xsd1, xsd2 );
    }

    @Test
    public void testAutenticar() throws Exception {
        NfseSoap conectar = servico.conectar();
        ConsultarSituacaoLoteRpsRequest request = new ConsultarSituacaoLoteRpsRequest();
        ConsultarSituacaoLoteRpsResponse consultarSituacaoLoteRps = conectar.consultarSituacaoLoteRps( request );
        ConsultarSituacaoLoteRpsResposta resposta = XmlUtils.createObjectFromXml(
                consultarSituacaoLoteRps.getOutputXML(), ConsultarSituacaoLoteRpsResposta.class );
        assertEquals( "E972", resposta.getListaMensagemRetorno().getMensagemRetorno().get( 0 ).getCodigo() );
    }
}
