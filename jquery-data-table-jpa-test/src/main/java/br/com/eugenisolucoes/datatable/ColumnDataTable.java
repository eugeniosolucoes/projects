/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.datatable;

/**
 *
 * @author eugenio
 */
public class ColumnDataTable implements Comparable<ColumnDataTable> {

    public enum SortDirection {

        ASC, DESC
    }

    private String name;

    private boolean sortable;
    
    private boolean searchable;

    private SortDirection sortDirection;

    private int sortIndex;

    public ColumnDataTable( String name ) {
        this.name = name;
        this.sortDirection = SortDirection.ASC;
    }

    public ColumnDataTable( String name, boolean sortable ) {
        this( name );
        this.sortable = sortable;
    }

    public ColumnDataTable( String name, boolean sortable, SortDirection sortDirection ) {
        this( name, sortable );
        this.sortDirection = sortDirection;
    }

    public void setSortable( boolean sortable ) {
        this.sortable = sortable;
    }

    public void setSortDirection( SortDirection sortDirection ) {
        this.sortDirection = sortDirection;
    }

    public String getName() {
        return name;
    }

    public boolean isSortable() {
        return sortable;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex( int sortIndex ) {
        this.sortIndex = sortIndex;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable( boolean searchable ) {
        this.searchable = searchable;
    }

    @Override
    public int compareTo( ColumnDataTable o ) {
        return this.sortIndex == o.sortIndex ? 0 : this.sortIndex > o.sortIndex ? 1 : -1;
    }
}
