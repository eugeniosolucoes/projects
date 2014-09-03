/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 *
 * @author eugenio
 */
public class Mensagem {

    public static enum Type {
        
        NOTICE("showNoticeToast", "notice"), 
        SUCESS("showSuccessToast", "success"), 
        WARNING("showWarningToast", "warning"), 
        ERROR("showErrorToast", "error");
        
        String description;
        String shortDescription;

        private Type( String description, String shortDescription ) {
            this.description = description;
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getShortDescription() {
            return shortDescription;
        }
    }

    public static void processarMensagemDeErro( BindingResult binding ) {
        if ( binding.hasErrors() ) {
            StringBuilder sb = new StringBuilder();
            sb.append( "<ul>" );
            for ( ObjectError erro : binding.getAllErrors() ) {
                sb.append( String.format( "<li>%s</li>", erro.getDefaultMessage() ) );
            }
            sb.append( "</ul>" );
            throw new IllegalStateException( sb.toString() );
        }
    }
}
