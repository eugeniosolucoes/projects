/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class TransporteLocalServiceTest {

    public TransporteLocalServiceTest() {
    }

    @Test
    public void showXML() {

        try {

            TransporteLocalService service = new TransporteLocalService();

            TransporteLocal local = service.retornar( 1L );

            JAXBContext context = JAXBContext.newInstance( TransporteLocal.class );
            Marshaller m = context.createMarshaller();

            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            m.marshal( local, System.out );

        } catch ( Exception ex ) {
            Logger.getLogger( TransporteLocalServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
        }

    }
}
