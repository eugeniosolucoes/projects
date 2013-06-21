package util;

import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class Ldap {

    private String servidor;

    private int porta;

    private String usuario;

    private String senha;

    private String raiz;

    public Ldap() {
    }

    public Ldap( String servidor ) {
        this.setServidor( servidor );
    }

    public Ldap( String servidor, String raiz ) {
        this( servidor );
        this.raiz = raiz;
    }

    public Ldap( String servidor, String raiz, String usuario, String senha ) {
        this( servidor, raiz );
        this.usuario = usuario;
        this.senha = senha;
    }

    public String[] login( String usuario, String senha ) throws NamingException,
            Exception {
        String[] saida = new String[3];
        try {
            String usr = pesquisa( usuario );
            if ( usr == null ) {
                throw new Exception( "Usuário não encontrado!" );
            }
            Hashtable env = new Hashtable( 5, 0.75f );
            env.put( Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory" );
            env.put( Context.PROVIDER_URL, getServidor() );
            env.put( Context.SECURITY_AUTHENTICATION, "simple" );
            env.put( Context.SECURITY_PRINCIPAL, usr );
            env.put( Context.SECURITY_CREDENTIALS, senha );


            DirContext ctx = new InitialDirContext( env );
            Attributes attrs = ctx.getAttributes( usr );

            saida[0] = attrs.get( "cn" ).get().toString();
            saida[1] = attrs.get( "sn" ).get().toString();
            saida[2] = getParentOrganizationalUnit( usr ).toLowerCase();
        } catch ( AuthenticationException ae ) {
            throw new AuthenticationException( ae.getMessage() );
        } catch ( Exception ex ) {
            throw new Exception( ex.getMessage() );
        }
        return saida;

    }

    public String[] pesquisaUsuario( String usuario ) throws NamingException,
            Exception {
        Hashtable env = new Hashtable( 5, 0.75f );

        env.put( Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory" );
        env.put( Context.PROVIDER_URL, getServidor() );
        env.put( Context.SECURITY_AUTHENTICATION, "simple" );
        env.put( Context.SECURITY_PRINCIPAL, getUsuario() );
        env.put( Context.SECURITY_CREDENTIALS, getSenha() );

        String[] resultado = new String[3];
        try {
            DirContext ctx = new InitialDirContext( env );
            SearchControls constraints = new SearchControls();
            constraints.setCountLimit( 1 );
            constraints.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results = ctx.search( raiz, "cn=" + usuario,
                    constraints );
            if ( results != null && results.hasMore() ) {
                SearchResult si = (SearchResult) results.next();
                resultado[0] = si.getAttributes().get( "cn" ).get().toString();
                resultado[1] = si.getAttributes().get( "sn" ).get().toString();
                resultado[2] = getParentOrganizationalUnit(
                        si.getNameInNamespace() ).toLowerCase();
            } else {
                return null;
            }
        } catch ( NamingException e ) {
            throw new Exception( e );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage() );
        }
        return resultado;
    }

    public String pesquisa( String usuario ) throws NamingException, Exception {
        Hashtable env = new Hashtable( 5, 0.75f );

        env.put( Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory" );
        env.put( Context.PROVIDER_URL, getServidor() );
        env.put( Context.SECURITY_AUTHENTICATION, "simple" );
        env.put( Context.SECURITY_PRINCIPAL, getUsuario() );
        env.put( Context.SECURITY_CREDENTIALS, getSenha() );

        String resultado = null;
        try {
            DirContext ctx = new InitialDirContext( env );
            SearchControls constraints = new SearchControls();
            constraints.setCountLimit( 1 );
            constraints.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results = ctx.search( raiz, "cn=" + usuario,
                    constraints );
            if ( results != null && results.hasMore() ) {
                SearchResult si = (SearchResult) results.next();
                resultado = si.getNameInNamespace();
            }
        } catch ( NamingException e ) {
            throw new Exception( e );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage() );
        }
        return resultado;
    }

    public String getServidor() {
        return servidor;
    }

    public final void setServidor( String servidor ) {
        this.servidor = String.format( "ldap://%s:389", servidor );
    }

    public final void setServidor( String servidor, int porta ) {
        this.servidor = String.format( "ldap://%s:%d", servidor, porta );
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta( int porta ) {
        this.porta = porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario( String usuario ) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha ) {
        this.senha = senha;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz( String raiz ) {
        this.raiz = raiz;
    }

    private String getParentOrganizationalUnit( String dn ) throws Exception {
        int i = dn.indexOf( "ou=" );
        if ( i != -1 ) {
            int start = i + 3;
            String om = dn.substring( start );
            int end = om.indexOf( "," ) + start;
            return dn.substring( start, end );
        } else {
            throw new Exception( "O usuário dever possuir uma OM!" );
        }
    }
}
