/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.model.dto;

import br.com.eugeniosolucoes.model.Conta;
import org.modelmapper.ModelMapper;

/**
 *
 * @author cpo-202
 */
public class ContaDTO extends BaseDTO<Conta> {

    private String banco;

    private String agencia;

    private String conta;

    private String saldo;

    public String getBanco() {
        return banco;
    }

    public void setBanco( String banco ) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia( String agencia ) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta( String conta ) {
        this.conta = conta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo( String saldo ) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContaDTO{" + "banco=" + banco + ", agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + '}';
    }

    @Override
    public Conta builder() {
        Conta entidadeConta = new Conta( this.banco, this.agencia, this.conta );
        if(!this.isNew()) {
            entidadeConta.setId( Long.valueOf( this.id ));
        }
        return entidadeConta;
    }

    public static ContaDTO builder( Conta entidade ) {
        ModelMapper mapper = new ModelMapper();
        ContaDTO contaDTO = mapper.map( entidade, ContaDTO.class );
        return contaDTO;
    }
}
