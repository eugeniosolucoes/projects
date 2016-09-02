/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.datatable;

import java.util.ArrayList;

/**
 *
 * @author cpo-202
 */
public interface IOutputDataTable {

    ArrayList getAaData();

    int getiTotalDisplayRecords();

    int getiTotalRecords();

    String getsEcho();

    void setAaData( ArrayList aaData );

    void setiTotalDisplayRecords( int iTotalDisplayRecords );

    void setiTotalRecords( int iTotalRecords );

    void setsEcho( String sEcho );
    
}
