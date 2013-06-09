/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.apoio.Estado;
import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import br.com.sanger.modelo.clientes.ClientePessoaJuridica;
import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Proprietario;
import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import br.com.sanger.servico.funcionarios.impl.AutonomoService;
import br.com.sanger.servico.funcionarios.impl.MotoristaService;
import br.com.sanger.servico.transporte.impl.VeiculoDeTransporteService;
import br.com.sanger.util.MyStrings;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author eugenio
 */
public class ClienteServiceTest {
    
    public ClienteServiceTest() {
    }
    
    @Test
    public void criarClientesPF() {
        System.out.println( "criando clientes pf" );
        ClienteService service = new ClienteService();
        int num = 20;
        Random random = new Random();
        for ( int i = 1; i <= num; i++ ) {
            String cpf = MyStrings.padLeft( 11, random.nextInt( (int) ( i + 1e11 ) ) );
            ClientePessoaFisica pf = new ClientePessoaFisica( "cliente pf " + i, cpf );
            pf.setEndereco( new Endereco() );
            pf.getEndereco().setLogradouro( "rua " + i );
            pf.getEndereco().setNumero( "10" );
            pf.getEndereco().setBairro( "teste" );
            pf.getEndereco().setCep( "00.000-000" );
            pf.getEndereco().setComplemento( "ap. 101" );
            pf.getEndereco().setCidade( "Rio de Janeiro" );
            pf.setTelefoneResidencial( "(00)0000-0000" );
            pf.setTelefoneComercial( "(11)1111-1111" );
            pf.setTelefoneMovel( "(11)1111-1111" );
            try {
                service.salvar( pf );
            } catch ( Exception ex ) {
                Logger.getLogger( ClienteServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }
    
    @Test
    public void criarClientesPJ() {
        System.out.println( "criando clientes pj" );
        ClienteService service = new ClienteService();
        int num = 20;
        Random random = new Random();
        for ( int i = 1; i <= num; i++ ) {
            String cnpj = MyStrings.padLeft( 14, random.nextInt( (int) ( i + 1e14 ) ) );
            ClientePessoaJuridica pj = new ClientePessoaJuridica( "cliente pj " + i, cnpj );
            pj.setInscricaoMunicipal( "1111111-1" );
            pj.setInscricaoEstadual( "2222222-2" );
            pj.setEndereco( new Endereco() );
            pj.getEndereco().setLogradouro( "rua " + i );
            pj.getEndereco().setNumero( "10" );
            pj.getEndereco().setBairro( "teste" );
            pj.getEndereco().setCep( "00.000-000" );
            pj.getEndereco().setComplemento( "ap. 101" );
            pj.getEndereco().setCidade( "Rio de Janeiro" );
            pj.setTelefoneResidencial( "(00)0000-0000" );
            pj.setTelefoneComercial( "(11)1111-1111" );
            pj.setTelefoneMovel( "(11)1111-1111" );
            try {
                service.salvar( pj );
            } catch ( Exception ex ) {
                Logger.getLogger( ClienteServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }
    
    @Test
    public void criarAutonomos() {
        System.out.println( "criando autonomos" );
        AutonomoService service = new AutonomoService();
        int num = 20;
        Random random = new Random();
        for ( int i = 1; i <= num; i++ ) {
            String cpf = MyStrings.padLeft( 11, random.nextInt( (int) ( i + 1e11 ) ) );
            Autonomo autonomo = new Autonomo( "autonomo " + i, cpf );
            autonomo.setEndereco( new Endereco() );
            autonomo.getEndereco().setLogradouro( "rua " + i );
            autonomo.getEndereco().setNumero( "10" );
            autonomo.getEndereco().setBairro( "teste" );
            autonomo.getEndereco().setCep( "00.000-000" );
            autonomo.getEndereco().setComplemento( "ap. 101" );
            autonomo.getEndereco().setCidade( "Rio de Janeiro" );
            autonomo.setTelefoneResidencial( "(00)0000-0000" );
            autonomo.setTelefoneComercial( "(11)1111-1111" );
            autonomo.setTelefoneMovel( "(11)1111-1111" );
            try {
                service.salvar( autonomo );
            } catch ( Exception ex ) {
                Logger.getLogger( ClienteServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }

    @Test
    public void criarMotoristas() {
        System.out.println( "criando motoristas" );
        MotoristaService service = new MotoristaService();
        int num = 20;
        Random random = new Random();
        for ( int i = 1; i <= num; i++ ) {
            String cpf = MyStrings.padLeft( 11, random.nextInt( (int) ( i + 1e11 ) ) );
            Motorista motorista = new Motorista( "motorista " + i, cpf );
            motorista.setEndereco( new Endereco() );
            motorista.getEndereco().setLogradouro( "rua " + i );
            motorista.getEndereco().setNumero( "10" );
            motorista.getEndereco().setBairro( "teste" );
            motorista.getEndereco().setCep( "00.000-000" );
            motorista.getEndereco().setComplemento( "ap. 101" );
            motorista.getEndereco().setCidade( "Rio de Janeiro" );
            motorista.setTelefoneResidencial( "(00)0000-0000" );
            motorista.setTelefoneComercial( "(11)1111-1111" );
            motorista.setTelefoneMovel( "(11)1111-1111" );
            try {
                service.salvar( motorista );
            } catch ( Exception ex ) {
                Logger.getLogger( ClienteServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }
    
    @Test
    public void criarVeiculos() {
        System.out.println( "criando veiculos de transporte" );
        VeiculoDeTransporteService service = new VeiculoDeTransporteService();
        int num = 20;
        Random random = new Random();
        for ( int i = 1; i <= num; i++ ) {
            VeiculoDeTransporte veiculo = new VeiculoDeTransporte();
            veiculo.setPlaca( "XYZ-" + MyStrings.padLeft( 4, random.nextInt( i ) ) );
            veiculo.setCidade( "Rio de Janeiro" );
            veiculo.setEstado( Estado.RJ );
            veiculo.setMarca( "volks" );
            veiculo.setModelo( "caminhão baú" );
            veiculo.setProprietario( new Proprietario() );
            veiculo.getProprietario().setNome( "sanger-rio" );
            veiculo.getProprietario().setEndereco( new Endereco() );
            veiculo.getProprietario().getEndereco().setLogradouro( "rua " + i );
            veiculo.getProprietario().getEndereco().setNumero( "10" );
            veiculo.getProprietario().getEndereco().setBairro( "teste" );
            veiculo.getProprietario().getEndereco().setCep( "00.000-000" );
            veiculo.getProprietario().getEndereco().setComplemento( "ap. 101" );
            veiculo.getProprietario().getEndereco().setCidade( "Rio de Janeiro" );
            veiculo.getProprietario().setTelefoneResidencial( "(00)0000-0000" );
            veiculo.getProprietario().setTelefoneComercial( "(11)1111-1111" );
            veiculo.getProprietario().setTelefoneMovel( "(11)1111-1111" );
            try {
                service.salvar( veiculo );
            } catch ( Exception ex ) {
                Logger.getLogger( ClienteServiceTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }

    //@Test
    public void testListar() throws Exception {
        System.out.println( "listar" );
        ClienteService instance = new ClienteService();
        List<Cliente> lista = instance.listar();
        
        for ( Cliente obj : lista ) {
            
            System.out.println( obj.getNome() );
            System.out.println( obj.getEndereco().getLogradouro() );
        }
    }

    //@Test
    public void testGetObject() throws Exception {
        System.out.println( "getobject" );
        ClienteService instance = new ClienteService();
        List<Cliente> lista = instance.listar();
        
        ClientePessoaFisica pf = new ClientePessoaFisica();
        pf.setId( 3L );
        Cliente obj = (Cliente) getEntidade( pf, lista );
        
        System.out.println( obj.getNome() );
    }
    
    private <E extends IEntidade> IEntidade getEntidade( Object id, List<E> lista ) throws Exception {
        E objeto = lista.get( lista.indexOf( id ) );
        return objeto;
    }
}
