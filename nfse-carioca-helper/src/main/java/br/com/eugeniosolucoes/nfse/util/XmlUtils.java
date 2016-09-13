/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.nfse.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author eugenio
 */
public class XmlUtils {
    
    private static final Logger LOG = Logger.getLogger( XmlUtils.class.getName() );
    
    /**
     * 
     * @param <E>
     * @param objeto
     * @return 
     */
    public static <E> String createXmlFromObject( E objeto ) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( objeto.getClass() );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            
            StringWriter writer = new StringWriter();
            
            jaxbMarshaller.marshal( objeto, writer );
            jaxbMarshaller.marshal( objeto, System.out );
            
            return writer.toString();
            
        } catch ( JAXBException e ) {
            LOG.error( e.getMessage(), e );
        }
        return null;
    }
    
    /**
     * 
     * @param <E>
     * @param xml
     * @param clazz
     * @return 
     */
    public static <E> E createObjectFromXml( String xml, Class<? extends E> clazz ) {
        try {
            
            JAXBContext jaxbContext = JAXBContext.newInstance( clazz );
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            E objeto = (E) jaxbUnmarshaller.unmarshal( new StringReader( xml ) );
            
            return objeto;
        } catch ( JAXBException e ) {
            LOG.error( e.getMessage(), e );
        }
        return null;
    }

    /**
     *
     * @param xml
     * @param xsds
     * @return
     * @throws Exception
     */
    public static void validateXml( String xml, InputStream... xsds ) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Source> sources = new ArrayList<>();
        for ( InputStream is : xsds ) {
            sources.add( new StreamSource( is ) );
        }
        Collections.reverse( sources );
        
        SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        Schema schema = schemaFactory.newSchema( sources.toArray( new Source[sources.size()] ) );
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware( true );
        factory.setValidating( false );
        XmlValidationErrorHandler errorHandlder = new XmlValidationErrorHandler();
        
        factory.setSchema( schema );
        factory.newSAXParser().parse( new ByteArrayInputStream( xml.getBytes( StandardCharsets.UTF_8 ) ), errorHandlder );
        
        if ( !errorHandlder.getMensagens().isEmpty() ) {
            for ( String erro : errorHandlder.getMensagens() ) {
                sb.append( erro ).append( "\n" );
            }
        }
        if ( !sb.toString().isEmpty() ) {
            throw new IllegalStateException( sb.toString() );
        }
    }
    
    /**
     * 
     * @param unformattedXml
     * @return 
     */
    public static String format( String unformattedXml ) {
        try {
            final Document document = parseXmlFile( unformattedXml );
            OutputFormat format = new OutputFormat( document );
            format.setLineWidth( 65 );
            format.setIndenting( true );
            format.setIndent( 2 );
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer( out, format );
            serializer.serialize( document );
            return out.toString();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }
    
    private static Document parseXmlFile( String in ) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource( new StringReader( in ) );
            return db.parse( is );
        } catch ( ParserConfigurationException | SAXException | IOException e ) {
            throw new RuntimeException( e );
        }
    }
    
    /**
     * 
     * @param caminho
     * @return
     * @throws Exception 
     */
    public static String lerArquivo( String caminho ) throws Exception {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner( new File( caminho ) );
        while (scanner.hasNext()) {
            sb.append( scanner.nextLine() );
        }
        return sb.toString();
    }
    
    /**
     * 
     * @param caminho
     * @return
     * @throws Exception 
     */
    public static String lerArquivo( InputStream caminho ) throws Exception {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner( caminho );
        while (scanner.hasNext()) {
            sb.append( scanner.nextLine() );
        }
        return sb.toString();
    }
    
    /**
     * 
     * @return 
     */
    public static XMLGregorianCalendar createDataXml() {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime( new Date() );
            return DatatypeFactory.newInstance().newXMLGregorianCalendar( c );
        } catch ( DatatypeConfigurationException ex ) {
            LOG.error( ex.getMessage(), ex );
        }
        return null;
    }

    /**
     * 
     * @param data
     * @return 
     */
    public static XMLGregorianCalendar createDataXml( Date data ) {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime( data );
            return DatatypeFactory.newInstance().newXMLGregorianCalendar( c );
        } catch ( DatatypeConfigurationException ex ) {
            LOG.error( ex.getMessage(), ex );
        }
        return null;
    }

    /**
     *
     * @param data
     * @param format
     * @return
     */
    public static XMLGregorianCalendar createDataXml( String data, String format ) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( format );
            GregorianCalendar c = new GregorianCalendar();
            c.setTime( sdf.parse( data ) );
            return DatatypeFactory.newInstance().newXMLGregorianCalendar( c );
        } catch ( ParseException | DatatypeConfigurationException ex ) {
            LOG.error( ex.getMessage(), ex );
        }
        return null;
    }
    
    
}
