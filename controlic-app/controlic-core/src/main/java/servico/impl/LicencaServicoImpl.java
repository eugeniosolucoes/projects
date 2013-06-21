/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
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

    LicencaDAO dao;

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

    void validacao( Licenca obj ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        Date hoje = new Date();
        try {
            hoje = sdf.parse( sdf.format( new Date() ) );
        } catch ( ParseException ex ) {
            Logger.getLogger( LicencaServicoImpl.class.getName() ).log( Level.SEVERE, null, ex );
        }
        if ( obj == null ) {
            throw new IllegalStateException( "Operação inválida!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getMotivo() ) ) {
            throw new IllegalStateException( "O motivo deve ser informado!" );
        }
        if ( obj.getMotivo().length() > 255 ) {
            throw new IllegalStateException( "O motivo deve ter no máximo 255 caracteres!" );
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
    public Map<Date, List<Militar>> listarPorAnoMesAgrupadoPorData( Integer ano, Integer mes ) {
        List<Licenca> lista = dao.listarPorAnoMes( ano, mes );
        if ( lista != null && !lista.isEmpty() ) {
            SortedMap<Date, List<Militar>> map = new TreeMap<Date, List<Militar>>();
            for ( Licenca licenca : lista ) {
                map.put( licenca.getDataLicenca(), new ArrayList<Militar>() );
            }
            for ( Iterator<Date> it = map.keySet().iterator(); it.hasNext(); ) {
                Date d = it.next();
                for ( Licenca licenca : lista ) {
                    if ( d.equals( licenca.getDataLicenca() ) ) {
                        map.get( d ).add( licenca.getMilitar() );
                    }
                }
            }
            return map;
        }
        return Collections.EMPTY_MAP;
    }

    @Override
    public Object[][] listarPorAnoMesArray( Integer ano, Integer mes ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        Map<Date, List<Militar>> map = this.listarPorAnoMesAgrupadoPorData( ano, mes );
        String[][] result = new String[map.size()][2];
        int i = 0;
        for ( Iterator<Date> it = map.keySet().iterator(); it.hasNext(); ) {
            Date d = it.next();
            result[i][0] = sdf.format( d );
            StringBuilder sb = new StringBuilder();
            for ( Militar militar : map.get( d ) ) {
                sb.append( militar.getLoginNome().concat( "<br/>" ) );
            }
            result[i][1] = sb.substring( 0, sb.length() - "<br/>".length() );
            i++;
        }
        return result;
    }
}
