/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.jpa.Licenca;
import modelo.jpa.Militar;
import org.json.JSONArray;
import org.json.JSONObject;
import repositorio.LicencaDAO;
import servico.LicencaServico;
import util.MyStrings;

/**
 *
 * @author eugenio
 */
public class LicencaServicoImpl implements LicencaServico {

    private LicencaDAO dao;

    public static final int MOTIVO_MIN_SIZE = 6;
    
    public LicencaServicoImpl() {
        dao = new LicencaDAO();
    }

    @Override
    public void salvar( Licenca obj ) {
        validacao( obj );
        try {
            if ( obj.getId() == null ) {
                dao.criar( obj );
            } else {
                dao.editar( obj );
            }
        } catch ( Exception e ) {
            if ( e.getMessage().toLowerCase().indexOf( "unq_licenca_0" ) != -1 ) {
                throw new IllegalStateException( "O militar só pode ter uma licença no mesmo dia!" );
            }
            throw new IllegalStateException( e );
        }
    }

    @Override
    public void excluir( Licenca obj ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        Date hoje = new Date();
        try {
            hoje = sdf.parse( sdf.format( new Date() ) );
        } catch ( ParseException ex ) {
            Logger.getLogger( LicencaServicoImpl.class.getName() ).log( Level.SEVERE, null, ex );
        }
        if ( obj.getDataLicenca().before( hoje ) ) {
            throw new IllegalStateException( "Não é possível excluir no passado!" );
        }
        dao.excluir( obj );
    }

    @Override
    public Licenca retornar( Object id ) {
        return dao.retornar( id );
    }

    @Override
    public List<Licenca> listarPorAnoMes( Integer ano, Integer mes ) {
        if ( ano == null || mes == null ) {
            throw new NullPointerException( "O ano e o mes devem ser fornecidos!" );
        }
        return dao.listarPorAnoMes( ano, mes );
    }

    @Override
    public List<Licenca> listarPorMilitar( Militar obj ) {
        if ( obj == null ) {
            throw new NullPointerException( "O militar deve ser informado!" );
        }
        return dao.listarPorMilitar( obj );
    }

    public void validacao( Licenca obj ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        Date hoje = new Date();
        try {
            hoje = sdf.parse( sdf.format( new Date() ) );
        } catch ( ParseException ex ) {
            Logger.getLogger( LicencaServicoImpl.class.getName() ).
                    log( Level.SEVERE, null, ex );
        }
        if ( obj == null ) {
            throw new IllegalStateException( "Operação inválida!" );
        }
        if ( obj.getMilitar() == null || obj.getMilitar().getId() == null ) {
            throw new IllegalStateException( "Operação inválida!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getMotivo() ) ) {
            throw new IllegalStateException( "O motivo deve ser informado!" );
        }
        obj.setMotivo( obj.getMotivo().trim() );
        if ( obj.getMotivo().length() > 255 ) {
            throw new IllegalStateException( "O motivo deve ter no máximo "
                    + "255 caracteres!" );
        }
        if ( obj.getMotivo().length() < MOTIVO_MIN_SIZE  ) {
            throw new IllegalStateException( "O motivo deve ter pelo menos "
                    + "uma palavra de no mínimo seis (06) caracteres!" );
        }
        if ( obj.getDataLicenca().before( hoje ) ) {
            throw new IllegalStateException( "Não é possível registrar no passado!" );
        }
    }

    @Override
    public JSONArray listarPorAnoMesJSON( Integer ano, Integer mes ) {
        List<Licenca> lista = dao.listarPorAnoMes( ano, mes );
        JSONArray jsona = new JSONArray();
        for ( Licenca licenca : lista ) {
            JSONObject jsono = new JSONObject( licenca );
            jsona.put( jsono );
        }
        return jsona;
    }
    
    @Override
    public List<Integer> listarAnos() {
        List<Integer> lista = dao.listarAnos();
        return lista;
    }    

}
