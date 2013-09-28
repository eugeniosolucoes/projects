/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.datatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author eugenio
 */
public class InputDataTable {

    private HttpServletRequest request;

    private boolean[] bRegex;

    private boolean[] bSearchable;

    private boolean[] bSortable;

    private int iColumns;

    private int iDisplayLength;

    private int iDisplayStart;

    private int[] iSortCol;

    private int iSortingCols;

    private int[] mDataProp;

    private String sColumns;

    private String sEcho;

    private String sSearch;

    private String[] sSortDir;

    private int iTotalRecords;

    private List<ColumnDataTable> colunas;

    private List<String> nomesDasColunas;

    public InputDataTable( HttpServletRequest request, String... colunas ) {
        this.request = request;
        this.colunas = new ArrayList<ColumnDataTable>();
        this.nomesDasColunas = new ArrayList<String>();
        this.nomesDasColunas.addAll( Arrays.asList( colunas ) );
        recuperarParametros();
    }

    public String getsEcho() {
        return sEcho;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public List<String> getNomesDasColunas() {
        return nomesDasColunas;
    }

    private void recuperarParametros() {
        this.iColumns = Integer.parseInt( request.getParameter( "iColumns" ) );
        this.iDisplayLength = Integer.parseInt( request.getParameter( "iDisplayLength" ) );
        this.iDisplayStart = Integer.parseInt( request.getParameter( "iDisplayStart" ) );
        this.iSortingCols = Integer.parseInt( request.getParameter( "iSortingCols" ) );
        this.sColumns = request.getParameter( "sColumns" );
        this.sEcho = request.getParameter( "sEcho" );
        this.sSearch = request.getParameter( "sSearch" );
        this.bRegex = new boolean[this.iColumns];
        this.bSearchable = new boolean[this.iColumns];
        this.bSortable = new boolean[this.iColumns];
        this.iSortCol = new int[this.iSortingCols];
        this.mDataProp = new int[this.iColumns];
        this.sSortDir = new String[this.iSortingCols];
        configurarParametros();
        configurarParametrosDaOrdenacao();
        configurarColunas();
        Collections.sort( colunas );
    }

    private void configurarParametros() {
        for ( int i = 0; i < this.iColumns; i++ ) {
            if ( request.getParameter( "bRegex_" + i ) != null ) {
                bRegex[i] = Boolean.parseBoolean( request.getParameter( "bRegex_" + i ) );
            }
            if ( request.getParameter( "bSearchable_" + i ) != null ) {
                bSearchable[i] = Boolean.parseBoolean( request.getParameter( "bSearchable_" + i ) );
            }
            if ( request.getParameter( "bSortable_" + i ) != null ) {
                bSortable[i] = Boolean.parseBoolean( request.getParameter( "bSortable_" + i ) );
            }
            if ( request.getParameter( "iSortCol_" + i ) != null ) {
                iSortCol[i] = Integer.parseInt( request.getParameter( "iSortCol_" + i ) );
            }

            if ( request.getParameter( "mDataProp_" + i ) != null ) {
                mDataProp[i] = Integer.parseInt( request.getParameter( "mDataProp_" + i ) );
            }
        }
    }

    private void configurarParametrosDaOrdenacao() {
        for ( int i = 0; i < this.iSortingCols; i++ ) {
            if ( request.getParameter( "sSortDir_" + i ) != null ) {
                sSortDir[i] = request.getParameter( "sSortDir_" + i );
            }
        }
    }

    private void configurarColunas() {
        for ( int i = 0; i < this.iColumns; i++ ) {
            this.colunas.add( new ColumnDataTable( this.nomesDasColunas.get( i ) ) );
        }
        // configurando parametros de ordenacao dos objeto ColumnDataTable
        for ( int i = 0; i < this.iSortingCols; i++ ) {
            this.colunas.get( this.iSortCol[i] ).setSortable( true );
            this.colunas.get( this.iSortCol[i] ).setSortDirection( ColumnDataTable.SortDirection.valueOf( this.sSortDir[i].toUpperCase() ) );
            this.colunas.get( this.iSortCol[i] ).setSortIndex( i );
        }
    }

    /**
     * Retorna uma lista de colunas configuradas com
     * propriedades de ordenação.
     * 
     * @return Uma lista de objetos ColumnDataTable 
     */
    public List<ColumnDataTable> getColunas() {
        return colunas;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords( int iTotalRecords ) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getsSearch() {
        return sSearch;
    }

    public int getiSortingCols() {
        return iSortingCols;
    }

    /**
     * Retorna a string SQL de ordenação.
     * Ex: "ORDER BY [alias].col1 ASC, [alias].col2 DESC
     * 
     * @param alias Alias referente a tabela ou entidade especificada na cláusula FROM.
     * @return  String SQL de ordenação.
     */
    public String getSortSQL( String alias ) {
        String sql = " ";
        if ( this.getiSortingCols() > 0 ) {
            sql = "ORDER BY ";
            String separador = ", ";
            for ( ColumnDataTable coluna : this.getColunas() ) {
                if ( coluna.isSortable() ) {
                    sql += String.format( "%s.%s %s",
                            alias,
                            coluna.getName(),
                            coluna.getSortDirection() );
                    sql += separador;
                }
            }
            sql = sql.substring( 0, sql.length() - separador.length() );
        }
        return sql;
    }
}
