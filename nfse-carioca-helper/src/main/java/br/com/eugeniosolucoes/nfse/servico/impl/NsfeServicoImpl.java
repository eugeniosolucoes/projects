/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.nfse.servico.impl;

import br.com.eugeniosolucoes.nfse.model.CancelarNfseEnvio;
import br.com.eugeniosolucoes.nfse.model.CancelarNfseResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseRpsResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarSituacaoLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarSituacaoLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.model.EnviarLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.EnviarLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.servico.NsfeServico;
import static br.com.eugeniosolucoes.nfse.util.Config.PROP;
import br.com.eugeniosolucoes.nfse.util.Subscriber;
import br.com.eugeniosolucoes.nfse.util.XmlUtils;
import br.com.eugeniosolucoes.nfse.ws.ConsultarLoteRpsRequest;
import br.com.eugeniosolucoes.nfse.ws.ConsultarLoteRpsResponse;
import br.com.eugeniosolucoes.nfse.ws.ConsultarNfsePorRpsRequest;
import br.com.eugeniosolucoes.nfse.ws.ConsultarNfsePorRpsResponse;
import br.com.eugeniosolucoes.nfse.ws.ConsultarNfseRequest;
import br.com.eugeniosolucoes.nfse.ws.ConsultarNfseResponse;
import br.com.eugeniosolucoes.nfse.ws.ConsultarSituacaoLoteRpsRequest;
import br.com.eugeniosolucoes.nfse.ws.ConsultarSituacaoLoteRpsResponse;
import br.com.eugeniosolucoes.nfse.ws.Nfse;
import br.com.eugeniosolucoes.nfse.ws.NfseSoap;
import br.com.eugeniosolucoes.nfse.ws.RecepcionarLoteRpsRequest;
import br.com.eugeniosolucoes.nfse.ws.RecepcionarLoteRpsResponse;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.xml.namespace.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NsfeServicoImpl implements NsfeServico {

    private static final Logger LOG = LoggerFactory.getLogger( NsfeServicoImpl.class );

    private static final QName SERVICE_NAME = new QName( "http://notacarioca.rio.gov.br/", "Nfse" );

    private static final String XSD1 = "/schemas/tipos_nfse_v01.xsd";

    private static final String XSD2 = "/schemas/xmldsig-core-schema_v01.xsd";

    private void autenticar() {

        String caminhoDoCertificadoDoCliente = PROP.getProperty( "path.certificado" );
        String senhaDoCertificadoDoCliente = PROP.getProperty( "passwd.certificado" );
        String caminhoDoKeyStore = PROP.getProperty( "path.keystore" );
        String senhaDoKeyStore = PROP.getProperty( "passwd.keystore" );

        System.setProperty( "java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol" );

        System.setProperty( "javax.net.ssl.keyStoreType", "PKCS12" );
        System.setProperty( "javax.net.ssl.keyStore", caminhoDoCertificadoDoCliente );
        System.setProperty( "javax.net.ssl.keyStorePassword", senhaDoCertificadoDoCliente );

        System.setProperty( "javax.net.ssl.trustStoreType", "JKS" );
        System.setProperty( "javax.net.ssl.trustStore", caminhoDoKeyStore );
        System.setProperty( "javax.net.ssl.trustStorePassword", senhaDoKeyStore );

        Authenticator.setDefault( new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return ( new PasswordAuthentication(
                        PROP.getProperty( "user.proxy" ),
                        PROP.getProperty( "passwd.proxy" ).toCharArray() ) );
            }
        } );

        Properties systemProperties = System.getProperties();
        // -Djava.net.useSystemProxies=true
        systemProperties.setProperty( "java.net.useSystemProxies", PROP.getProperty( "java.net.useSystemProxies" ) );
        systemProperties.setProperty( "http.proxyHost", PROP.getProperty( "http.proxyHost" ) );
        systemProperties.setProperty( "http.proxyPort", PROP.getProperty( "http.proxyPort" ) );
    }

    @Override
    public NfseSoap conectar() throws Exception {
        autenticar();
        Nfse ss = new Nfse( Nfse.WSDL_LOCATION, SERVICE_NAME );
        NfseSoap port = ss.getNfseSoap();
        return port;
    }

    @Override
    public EnviarLoteRpsResposta enviarLoteRps( EnviarLoteRpsEnvio envio ) {
        try {
            String xml = XmlUtils.createXmlFromObject( envio );
            xml = XmlUtils.format( xml );
            xml = xml.replaceAll( ".\\d\\d\\d[+,-]\\d\\d:\\d\\d</DataEmissao>", "</DataEmissao>" );
            String xmlAssinado = Subscriber.getInstance().assinarLoteRps( xml );
            xmlAssinado = XmlUtils.format( xmlAssinado );
            InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
            InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
            XmlUtils.validateXml( xmlAssinado, xsd1, xsd2 );
            RecepcionarLoteRpsRequest parameters = new RecepcionarLoteRpsRequest();
            parameters.setInputXML( xmlAssinado );
            LOG.info( xmlAssinado );
            RecepcionarLoteRpsResponse resposta = conectar().recepcionarLoteRps( parameters );
            return XmlUtils.createObjectFromXml( resposta.getOutputXML(), EnviarLoteRpsResposta.class );
        } catch ( Exception e ) {
            LOG.error( e.getMessage(), e );
            throw new IllegalStateException( e );
        }
    }

    @Override
    public ConsultarSituacaoLoteRpsResposta consultarSituacaoLoteRps( ConsultarSituacaoLoteRpsEnvio envio ) {
        try {
            String xml = XmlUtils.createXmlFromObject( envio );
            InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
            InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
            String xmlFormatado = XmlUtils.format( xml );
            XmlUtils.validateXml( xmlFormatado, xsd1, xsd2 );
            ConsultarSituacaoLoteRpsRequest parameters = new ConsultarSituacaoLoteRpsRequest();
            parameters.setInputXML( xmlFormatado );
            ConsultarSituacaoLoteRpsResponse resposta = conectar().consultarSituacaoLoteRps( parameters );
            return XmlUtils.createObjectFromXml( resposta.getOutputXML(), ConsultarSituacaoLoteRpsResposta.class );
        } catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
    }

    @Override
    public ConsultarNfseRpsResposta consultarNfseRps( ConsultarNfseRpsEnvio envio ) {
        try {
            String xml = XmlUtils.createXmlFromObject( envio );
            InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
            InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
            String xmlFormatado = XmlUtils.format( xml );
            XmlUtils.validateXml( xmlFormatado, xsd1, xsd2 );
            ConsultarNfsePorRpsRequest parameters = new ConsultarNfsePorRpsRequest();
            parameters.setInputXML( xmlFormatado );
            ConsultarNfsePorRpsResponse resposta = conectar().consultarNfsePorRps( parameters );
            return XmlUtils.createObjectFromXml( resposta.getOutputXML(), ConsultarNfseRpsResposta.class );
        } catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
    }

    @Override
    public ConsultarNfseResposta consultarNfse( ConsultarNfseEnvio envio ) {
        try {
            String xml = XmlUtils.createXmlFromObject( envio );
            InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
            InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
            String xmlFormatado = XmlUtils.format( xml );
            XmlUtils.validateXml( xmlFormatado, xsd1, xsd2 );
            ConsultarNfseRequest parameters = new ConsultarNfseRequest();
            parameters.setInputXML( xmlFormatado );
            ConsultarNfseResponse resposta = conectar().consultarNfse( parameters );
            return XmlUtils.createObjectFromXml( resposta.getOutputXML(), ConsultarNfseResposta.class );
        } catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
    }

    @Override
    public ConsultarLoteRpsResposta consultarLoteRps( ConsultarLoteRpsEnvio envio ) {
        try {
            String xml = XmlUtils.createXmlFromObject( envio );
            InputStream xsd1 = this.getClass().getResourceAsStream( XSD1 );
            InputStream xsd2 = this.getClass().getResourceAsStream( XSD2 );
            String xmlFormatado = XmlUtils.format( xml );
            XmlUtils.validateXml( xmlFormatado, xsd1, xsd2 );
            ConsultarLoteRpsRequest parameters = new ConsultarLoteRpsRequest();
            parameters.setInputXML( xmlFormatado );
            ConsultarLoteRpsResponse resposta = conectar().consultarLoteRps( parameters );
            return XmlUtils.createObjectFromXml( resposta.getOutputXML(), ConsultarLoteRpsResposta.class );
        } catch ( Exception e ) {
            throw new IllegalStateException( e );
        }        
    }

    @Override
    public CancelarNfseResposta cancelarNfse( CancelarNfseEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
