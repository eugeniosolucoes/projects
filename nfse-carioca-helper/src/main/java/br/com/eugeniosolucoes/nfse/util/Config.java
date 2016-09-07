/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.nfse.util;

import br.com.eugeniosolucoes.nfse.servico.impl.NsfeServicoImpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eugenio
 */
public class Config {

    private static final Logger LOG = LoggerFactory.getLogger( Subscriber.class );    
    
    public static final Properties PROP = new Properties();

    static {
        try {
            PROP.load( new FileInputStream( "application.properties" ) );
        } catch ( FileNotFoundException ex ) {
           LOG.error( ex.getMessage(), ex );
        } catch ( IOException ex ) {
           LOG.error( ex.getMessage(), ex );
        }
    }
}
