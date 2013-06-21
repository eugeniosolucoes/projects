/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.Date;
import java.util.List;
import java.util.Map;
import modelo.jpa.Licenca;
import modelo.jpa.Militar;
import org.json.JSONArray;

/**
 *
 * @author eugenio
 */
public interface LicencaServico {

    void salvar( Licenca obj );

    void excluir( Licenca obj );

    Licenca retornar( Object id );

    List<Licenca> listarPorAnoMes( Integer ano, Integer mes );

    List<Licenca> listarPorMilitar( Militar obj );

    JSONArray listarPorAnoMesJSON( Integer ano, Integer mes );

    Map<Date, List<Militar>> listarPorAnoMesAgrupadoPorData( Integer ano, Integer mes );
    
    Object[][] listarPorAnoMesArray( Integer ano, Integer mes );
}
