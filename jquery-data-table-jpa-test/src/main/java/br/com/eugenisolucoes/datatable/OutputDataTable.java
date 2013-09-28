/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.datatable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugenio
 */
public class OutputDataTable implements IOutputDataTable {

    private String sEcho;

    private int iTotalRecords;

    private int iTotalDisplayRecords;

    private ArrayList<ArrayList> aaData;

    @Override
    public String getsEcho() {
        return sEcho;
    }

    @Override
    public void setsEcho( String sEcho ) {
        this.sEcho = sEcho;
    }

    @Override
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    @Override
    public void setiTotalRecords( int iTotalRecords ) {
        this.iTotalRecords = iTotalRecords;
    }

    @Override
    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    @Override
    public void setiTotalDisplayRecords( int iTotalDisplayRecords ) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    @Override
    public ArrayList getAaData() {
        return aaData;
    }

    @Override
    public void setAaData( ArrayList aaData ) {
        this.aaData = aaData;
    }
}
