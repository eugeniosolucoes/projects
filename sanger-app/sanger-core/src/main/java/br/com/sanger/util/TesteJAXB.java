/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.util;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.apoio.Estado;
import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Transporte;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author eugenio
 */
public class TesteJAXB {

    public static void main( String[] args ) throws Exception {

        Motorista motorista = new Motorista( "eugenio", "123" );

        motorista.setEndereco( new Endereco() );

        motorista.getEndereco().setLogradouro( "rua a" );
        motorista.getEndereco().setNumero( "123" );
        motorista.getEndereco().setCep( "21.433-980" );
        motorista.getEndereco().setComplemento( "ap 898" );
        motorista.getEndereco().setCidade( "Rio de Janeiro" );
        motorista.getEndereco().setBairro( "XB" );
        motorista.getEndereco().setEstado( Estado.RJ );

        motorista.setHabilitacao( "545290" );
        motorista.setPronturario( "XAYUFAA" );
        motorista.setServicos( new ArrayList<Transporte>() );

        motorista.setTelefoneResidencial( "(21)7958-7029" );
        motorista.setTelefoneMovel( "(21)8958-7029" );

        TransporteLocal local = new TransporteLocal();
        
        local.setCliente( new ClientePessoaFisica( "cliente1", "09809" ) );
        
        local.getCliente().setTelefoneResidencial( "TELEFONE");

        local.setInventariante( new Autonomo( "autonomo1", "324243" ) );

        List<Autonomo> autonomos = new ArrayList<Autonomo>();

        autonomos.add( new Autonomo( "autonomo2", "432900" ) );

        autonomos.add( new Autonomo( "autonomo3", "432901" ) );

        autonomos.add( new Autonomo( "autonomo4", "432902" ) );

        local.setAjudantes( new ArrayList<Autonomo>( autonomos ) );

        motorista.getServicos().add( local );

        object2Xml( motorista );

    }

    public static void object2Xml( Object object ) throws Exception {

        JAXBContext context = JAXBContext.newInstance( object.getClass() );

        Marshaller m = context.createMarshaller();

        m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        m.marshal( object, System.out );

        //File f = new File( "result.xml" );

        //Marshaller m2 = context.createMarshaller();

        //m2.marshal( object, new FileOutputStream( f ) );

    }
}
