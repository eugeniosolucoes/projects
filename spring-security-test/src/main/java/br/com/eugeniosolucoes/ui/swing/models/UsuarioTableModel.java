package br.com.eugeniosolucoes.ui.swing.models;

import br.com.eugeniosolucoes.security.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Define um TableModel para entidade
 * <code>Usuario</code>, considerando as colunas:
 * <ul>
 * <li>Nome;</li>
 * <li>Ativo;</li>
 * </ul>
 *
 * @author eugenio
 */
public class UsuarioTableModel extends AbstractTableModel {

    enum Colunas {

        NOME( "Nome", String.class ),
        ATIVO( "Ativo", Boolean.class );

        String descricao;

        Class<?> tipo;

        private Colunas( String descricao, Class<?> tipo ) {
            this.descricao = descricao;
            this.tipo = tipo;
        }

        public static Colunas get( int ordem ) {
            return Colunas.values()[ordem];
        }
    }

    private List<Usuario> usuarios;

    public UsuarioTableModel() {
    }

    public void reload( List<Usuario> usuarios ) {
        this.usuarios = usuarios;
        //atualiza o componente na tela
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass( int coluna ) {
        return Colunas.get( coluna ).tipo;
    }

    @Override
    public int getColumnCount() {
        return Colunas.values().length;
    }

    @Override
    public String getColumnName( int coluna ) {
        return Colunas.get( coluna ).descricao;
    }

    @Override
    public int getRowCount() {
        if ( usuarios == null ) {
            return 0;
        }
        return usuarios.size();
    }

    @Override
    public Object getValueAt( int linha, int coluna ) {
        Usuario usuario = usuarios.get( linha );
        switch ( Colunas.get( coluna ) ) {
            case NOME:
                return usuario.getNome();
            case ATIVO:
                return usuario.isAtivo();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable( int rowIndex, int columnIndex ) {
        return false;
    }

    public Usuario getUsuarioAt( int index ) {
        return usuarios.get( index );
    }

    @Override
    public void setValueAt( Object aValue, int rowIndex, int columnIndex ) {
        Usuario usuario = getUsuarioAt( rowIndex );

        switch ( Colunas.get( columnIndex ) ) {
            case NOME:
                usuario.setNome( String.valueOf( aValue ) );
                break;
            case ATIVO:
                usuario.setAtivo( ( (Boolean) aValue ).booleanValue() );
                break;
        }

        fireTableCellUpdated( rowIndex, columnIndex );
    }
}
