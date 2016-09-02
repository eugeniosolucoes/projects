/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.controller;

import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.service.ClienteService;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eugenio
 */
@Controller
@RequestMapping( "/clientes" )
public class ClienteController {

    private static final String URL_PATH = "clientes/list";

    private final ClienteService service;

    private final ApplicationContext context;

    @Autowired
    public ClienteController( ClienteService service, ApplicationContext context ) {
        this.service = service;
        this.context = context;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView listar() {
        ModelAndView view = new ModelAndView( URL_PATH );
        listar( view );
        return view;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    public ModelAndView buscar( @PathVariable( "id" ) final String id ) {
        ModelAndView view = new ModelAndView( URL_PATH );
        ClienteDTO clienteDTO = service.buscar( id );
        view.addObject( "cliente", clienteDTO );
        listar( view );
        return view;
    }

    @RequestMapping( method = RequestMethod.POST, value = "/{id}" )
    public ModelAndView excluir( @PathVariable( "id" ) final String id ) {
        ModelAndView view = new ModelAndView( URL_PATH );
        service.excluir( id );
        listar( view );
        return view;
    }

    @RequestMapping( method = RequestMethod.POST )
    public ModelAndView salvar( @Valid ClienteDTO clienteDTO, BindingResult binding ) {
        ModelAndView view = new ModelAndView( URL_PATH );
        String operacao = clienteDTO.isNew() ? "cadastrado" : "atualizado";
        try {
            Mensagem.processarMensagemDeErro( binding );
            service.salvar( clienteDTO );
            view.addObject( "tipoMensagem", Mensagem.Type.SUCESS.getShortDescription() );
            view.addObject( "mensagem", context.getMessage( "cliente.salvar", new Object[]{ clienteDTO, operacao }, Locale.getDefault() ) );
        } catch ( Exception e ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.WARNING, e.getMessage() );
            view.addObject( "tipoMensagem", Mensagem.Type.ERROR.getShortDescription() );
            view.addObject( "mensagem", e.getMessage() );
            view.addObject( "cliente", clienteDTO );
        }
        listar( view );
        return view;
    }

    private void listar( ModelAndView view ) {
        List<ClienteDTO> clientes = service.listar();
        view.addObject( "clientes", clientes );
    }

}
