/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.repositorio.transporte.impl.TransporteLocalRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.servico.funcionarios.impl.AutonomoService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author eugenio
 */
public class TransporteLocalService extends GenericService<TransporteLocal> {

    public TransporteLocalService() {
        super( new TransporteLocalRepository() );
    }

    @Override
    public void salvar( TransporteLocal obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( TransporteLocal obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( TransporteLocal obj ) {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }

    public List<Autonomo> autonomosNaoAdicionados( TransporteLocal obj ) throws Exception {
        if ( obj != null && obj.getId() == null ) {
            return new AutonomoService().listar();
        }
        return ( (TransporteLocalRepository) dao ).autonomosNaoAdicionados( obj );
    }

    public byte[] imprimir( Object id ) throws Exception {
        TransporteLocal transporteLocal = dao.retornar( id );
        List<TransporteLocal> colecao = new ArrayList<TransporteLocal>();
        colecao.add( transporteLocal );
        InputStream is = this.getClass().getResourceAsStream( "/transportelocal.jasper" );
        InputStream logo = this.getClass().getResourceAsStream( "/logo.jpg" );
        Map parametros = new HashMap();
        parametros.put( "IMAGE_LOGO", logo );
        JasperPrint impressao = JasperFillManager.fillReport( is, parametros, new JRBeanCollectionDataSource( colecao ) );
        return JasperExportManager.exportReportToPdf( impressao );
    }
    
}
