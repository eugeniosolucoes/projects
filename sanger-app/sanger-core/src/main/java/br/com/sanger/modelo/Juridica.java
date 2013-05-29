/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo;

/**
 *
 * @author eugenio
 */
public interface Juridica {

    String getCnpj();

    String getInscricaoMunicipal();

    void setCnpj( String cnpj );

    void setInscricaoMunicipal( String inscricaoMunicipal );
}
