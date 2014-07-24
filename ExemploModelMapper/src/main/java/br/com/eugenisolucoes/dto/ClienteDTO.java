/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eugenio
 */
@XmlRootElement
public class ClienteDTO {

    private String cpf;

    private String nome;

    private String enderecoRua;

    private String enderecoBairro;

    private List<TelefoneDTO> telefones;

    public String getCpf() {
        return cpf;
    }

    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public void setEnderecoRua( String enderecoRua ) {
        this.enderecoRua = enderecoRua;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro( String enderecoBairro ) {
        this.enderecoBairro = enderecoBairro;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones( List<TelefoneDTO> telefones ) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "cpf=" + cpf + ", nome=" + nome + ", enderecoRua=" + enderecoRua + ", enderecoBairro=" + enderecoBairro + ", telefones=" + telefones + '}';
    }

}
