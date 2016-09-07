/**
 *
 */
package br.com.eugeniosolucoes.nfse.util;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author eugenio
 *
 */
public class XmlValidationErrorHandler extends DefaultHandler {

    private final List<String> mensagens;

    public XmlValidationErrorHandler() {
        this.mensagens = new LinkedList<>();
    }

    @Override
    public void warning( SAXParseException e ) throws SAXException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "Warning: " );
        printInfo( e, stringBuilder );
    }

    @Override
    public void error( SAXParseException e ) throws SAXException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "Error: " );
        printInfo( e, stringBuilder );
    }

    @Override
    public void fatalError( SAXParseException e ) throws SAXException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "Fatal error: " );
        printInfo( e, stringBuilder );
    }

    private void printInfo( SAXParseException e, StringBuilder stringBuilder ) throws SAXParseException {
//        stringBuilder.append( "   Public ID: " ).append( e.getPublicId() );
//        stringBuilder.append( "\n   System ID: " ).append( e.getSystemId() );
        stringBuilder.append( "\n   Line number: " ).append( e.getLineNumber() );
        stringBuilder.append( "\n   Column number: " ).append( e.getColumnNumber() );
        stringBuilder.append( "\n   Message: " ).append( e.getLocalizedMessage() );

        this.mensagens.add( stringBuilder.toString() );
    }

    /**
     * @return the mensagens
     */
    public List<String> getMensagens() {
        return mensagens;
    }
}
