/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugenio
 */
@Service
public class AuditService {

    private static Logger logger = Logger.getLogger( AuditService.class.getName() );

    /**
     * Audit this screen against the current user name
     *
     * It's more useful to put this info into a database so that that you can
     * count visits to pages and figure out how often they're used. That way,
     * you can focus your design on the popular parts of your application. The
     * logger is just for demo purposes.
     *
     * @param screenName
     */
    public void audit( String screenName ) {

        String userName = getCurrentUser();

        logger.log( Level.INFO, "Audit: {0} - {1}", new Object[]{ userName, screenName } );

    }

    /**
     * Get the current logged on user name by whatever mechanism available
     */
    private String getCurrentUser() {
        return "Eugenio";
    }

}
