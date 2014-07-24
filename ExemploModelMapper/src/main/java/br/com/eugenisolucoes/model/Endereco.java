/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.model;

/**
 *
 * @author eugenio
 */
public class Endereco {

    private String rua;

    private String bairro;

    public Endereco() {
    }

    public Endereco( String rua, String bairro ) {
        this.rua = rua;
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua( String rua ) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro( String bairro ) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", bairro=" + bairro + '}';
    }

}
