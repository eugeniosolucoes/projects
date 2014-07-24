/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.model;

import java.util.Objects;

/**
 *
 * @author eugenio
 */
public class Telefone {

    private int codigoDeArea;

    private String numero;

    public Telefone() {
    }

    public Telefone( int codigoDeArea, String numero ) {
        this.codigoDeArea = codigoDeArea;
        this.numero = numero;
    }

    public int getCodigoDeArea() {
        return codigoDeArea;
    }

    public void setCodigoDeArea( int codigoDeArea ) {
        this.codigoDeArea = codigoDeArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero( String numero ) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode( this.numero );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Telefone other = (Telefone) obj;
        if ( !Objects.equals( this.numero, other.numero ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Telefone{" + "numero=" + numero + '}';
    }

}
