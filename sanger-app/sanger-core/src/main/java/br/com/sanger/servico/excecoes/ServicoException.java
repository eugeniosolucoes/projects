/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.excecoes;

import javax.persistence.NoResultException;

/**
 *
 * @author eugenio
 */
public class ServicoException extends Exception {

    private static final String FORMATACAO_INVALIDA =
            "Formato invalido!";

    private int status = 500;

    public ServicoException() {
        super();
    }

    public ServicoException( Throwable cause ) {
        super( cause );
    }

    public ServicoException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ServicoException( String message ) {
        super( message );
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {

        // FK_TRANSPORTELOCAL_INVENTARIANTE_ID
        if ( getCause().getMessage().toUpperCase().indexOf( "FK_TRANSPORTELOCAL_INVENTARIANTE_ID" ) != -1 ) {
            return "Falha na exclusão: Não é possível excluir o inventariante registrado em um transporte local!";
        }
        // FK_TRANSPORTELOCAL_CLIENTE_ID
        if ( getCause().getMessage().toUpperCase().indexOf( "FK_TRANSPORTELOCAL_CLIENTE_ID" ) != -1 ) {
            return "Falha na exclusão: Não é possível excluir o cliente registrado em um transporte local!";
        }
        if ( getCause().getMessage().indexOf( "usuario_login_key" ) != -1 ) {
            return "Falha: Login existente!";
        }
        if ( getCause().getMessage().indexOf( "usuario_nome_key" ) != -1 ) {
            return "Falha: Nome existente!";
        }
        if ( getCause().getMessage().indexOf( "Duplicate entry" ) != -1
                && getCause().getMessage().indexOf( "CPF" ) != -1 ) {
            return "Falha na inclusao: CPF existente!";
        }
        if ( getCause().getMessage().indexOf( "Duplicate entry" ) != -1
                && getCause().getMessage().indexOf( "CNPJ" ) != -1 ) {
            return "Falha na inclusao: CNPJ existente!";
        }
        if ( getCause().getMessage().indexOf( "Duplicate entry" ) != -1
                && getCause().getMessage().indexOf( "NOME" ) != -1 ) {
            return "Falha na inclusao: Nome existente!";
        }
        if ( getCause().getMessage().indexOf( "CommunicationsException" ) != -1 ) {
            return "Falha ao contectar ao banco de dados!";
        }
        if ( getCause() instanceof NoResultException ) {
            return "A consulta nao retornou nenhum resultado!";
        }
        if ( getCause() instanceof java.lang.NumberFormatException ) {
            return FORMATACAO_INVALIDA;
        } else {
            return getCause().getMessage();
        }
    }
}
