/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model;

/**
 *
 * @author eugenio
 */
public class Oficial implements Militar {

    private String nip;

    private String nome;

    private String nomeDeGuerra;

    public Oficial( String nip, String nome, String nomeDeGuerra ) {
        this.nip = nip;
        this.nome = nome;
        this.nomeDeGuerra = nomeDeGuerra;
    }

    public String getNip() {
        return nip;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeDeGuerra() {
        return nomeDeGuerra;
    }

    public String getLogin() {
        return nip;
    }
}
