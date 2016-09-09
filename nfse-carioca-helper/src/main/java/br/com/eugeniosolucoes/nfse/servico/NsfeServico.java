/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.nfse.servico;

import br.com.eugeniosolucoes.nfse.model.CancelarNfseEnvio;
import br.com.eugeniosolucoes.nfse.model.CancelarNfseResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarNfseRpsResposta;
import br.com.eugeniosolucoes.nfse.model.ConsultarSituacaoLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.ConsultarSituacaoLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.model.EnviarLoteRpsEnvio;
import br.com.eugeniosolucoes.nfse.model.EnviarLoteRpsResposta;
import br.com.eugeniosolucoes.nfse.ws.NfseSoap;

/**
 *
 * @author eugenio
 */
public interface NsfeServico {

    EnviarLoteRpsResposta enviarLoteRps( EnviarLoteRpsEnvio envio );

    ConsultarSituacaoLoteRpsResposta consultarSituacaoLoteRps( ConsultarSituacaoLoteRpsEnvio envio );

    ConsultarNfseRpsResposta consultarNfseRps( ConsultarNfseRpsEnvio envio );

    ConsultarNfseResposta consultarNfse( ConsultarNfseEnvio envio );

    ConsultarLoteRpsResposta consultarLoteRps( ConsultarLoteRpsEnvio envio );

    CancelarNfseResposta cancelarNfse( CancelarNfseEnvio envio );

    NfseSoap conectar() throws Exception;
    
}
