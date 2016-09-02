/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.servico;

import br.com.eugenisolucoes.datatable.InputDataTable;
import br.com.eugenisolucoes.datatable.OutputDataTable;
import br.com.eugenisolucoes.modelo.Dado;
import br.com.eugenisolucoes.repositorio.DadoRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cpo-202
 */
public class DadoServico {

    public OutputDataTable listar( InputDataTable inputDataTable ) {

        DadoRepository dadoRepository = new DadoRepository();

        List<Dado> dados = dadoRepository.listar( inputDataTable );

        ArrayList lista = new ArrayList();
        
        for ( Dado dado : dados ) {
            ArrayList<String> linha = new ArrayList<String>();
            linha.add( dado.getEngine() );
            linha.add( dado.getBrowser() );
            linha.add( dado.getPlatform() );
            linha.add( dado.getVersion() );
            linha.add( dado.getGrade() );
            lista.add( linha );
        }
        
        OutputDataTable outputDataTable = new OutputDataTable();

        outputDataTable.setsEcho( inputDataTable.getsEcho() );

        outputDataTable.setiTotalDisplayRecords( inputDataTable.getiTotalRecords() );
        
        outputDataTable.setiTotalRecords( inputDataTable.getiTotalRecords() );

        outputDataTable.setAaData( lista );

        return outputDataTable;
    }
}
