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
import br.com.eugeniosolucoes.nfse.ws.Nfse;
import br.com.eugeniosolucoes.nfse.ws.NfseSoap;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.xml.namespace.QName;
import static br.com.eugeniosolucoes.nfse.util.Config.PROP;

public class NsfeServicoImpl implements NsfeServico {

    private static final QName SERVICE_NAME = new QName( "http://notacarioca.rio.gov.br/", "Nfse" );



    private void autenticar( boolean viaProxy ) {

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

        if ( viaProxy ) {
            Authenticator.setDefault( new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return (new PasswordAuthentication( 
                            PROP.getProperty( "user.proxy" ), 
                            PROP.getProperty( "passwd.proxy" ).toCharArray() ));
                }

            } );

            Properties systemProperties = System.getProperties();
            // -Djava.net.useSystemProxies=true
            systemProperties.setProperty( "java.net.useSystemProxies", PROP.getProperty( "java.net.useSystemProxies" ) );
            systemProperties.setProperty( "http.proxyHost", PROP.getProperty( "http.proxyHost" ) );
            systemProperties.setProperty( "http.proxyPort", PROP.getProperty( "http.proxyPort" ) );
        }
    }

    @Override
    public NfseSoap conectar() throws Exception {
        autenticar( true );
        Nfse ss = new Nfse( Nfse.WSDL_LOCATION, SERVICE_NAME );
        NfseSoap port = ss.getNfseSoap();
        return port;
    }

    @Override
    public EnviarLoteRpsResposta enviarLoteRps( EnviarLoteRpsEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultarSituacaoLoteRpsResposta consultarSituacaoLoteRps( ConsultarSituacaoLoteRpsEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultarNfseRpsResposta consultarNfseRps( ConsultarNfseRpsEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultarNfseResposta consultarNfse( ConsultarNfseEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultarLoteRpsResposta consultarLoteRps( ConsultarLoteRpsEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CancelarNfseResposta cancelarNfse( CancelarNfseEnvio envio ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
