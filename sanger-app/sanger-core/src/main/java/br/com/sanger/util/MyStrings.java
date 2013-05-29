package br.com.sanger.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eugenio
 */
public final class MyStrings {

    public static boolean isNullOrEmpty( java.lang.String string ) {
        return ( string != null && !string.trim().equals( "" ) ) ? false : true;
    }

    public static java.lang.String cleanMessage( String msg ) {
        if ( msg != null ) {
            java.lang.String[] lixo = {
                "\n", "\r", "\"", "'"
            };
            for ( int i = 0; i < lixo.length; i++ ) {
                msg = msg.replaceAll( lixo[i], "" );
            }
        }
        return msg;
    }

    public static boolean validaParametros( java.lang.String... params ) {
        for ( java.lang.String p : params ) {
            if ( MyStrings.isNullOrEmpty( p ) ) {
                return false;
            }
        }
        return true;
    }

    public static String padLeft( Integer numero ) {
        return String.format( "%03d", numero );
    }

    public static String padLeft( int part, Integer numero ) {
        String fmt = "%0" + part + "d";
        return String.format( fmt, numero );
    }

    public static String recarregarPagina() {
        return "<script type='text/javascript'>window.location.reload();</script>";
    }

    public static String exibeMensagem( String msg ) {
        if ( msg != null ) {
            if ( !msg.equals( "" ) ) {
                msg = String.format( "<script type='text/javascript'>"
                        + "alert('%s')</script>", msg.replace( "'", "\\'" ).replace( "\n", "\\n" ) );
            }
        } else {
            msg = "";
        }
        return msg;
    }
}
