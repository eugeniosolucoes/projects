/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.dto;

/**
 *
 * @author eugenio
 */
public class TelefoneDTO {

    int telefoneCodigoDeArea;

    String telefoneNumero;

    public int getTelefoneCodigoDeArea() {
        return telefoneCodigoDeArea;
    }

    public void setTelefoneCodigoDeArea( int telefoneCodigoDeArea ) {
        this.telefoneCodigoDeArea = telefoneCodigoDeArea;
    }

    public String getTelefoneNumero() {
        return telefoneNumero;
    }

    public void setTelefoneNumero( String telefoneNumero ) {
        this.telefoneNumero = telefoneNumero;
    }

    @Override
    public String toString() {
        return "TelefoneDTO{" + "telefoneCodigoDeArea=" + telefoneCodigoDeArea + ", telefoneNumero=" + telefoneNumero + '}';
    }

}
