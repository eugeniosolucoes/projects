<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of categoria_controle
 *
 * @author cpo-202
 */

class categoria_controle extends core_controle {

    function listar() {
        $servico = new categoria_servico();
        $usuario = unserialize($_SESSION['usuario']);
        return $servico->listar(array('usuario' => $usuario->get_id()));
    }

    function salvar() {
        $categoria = new categoria();
        try {
            $this->get_obj($categoria);
            if(!$categoria->get_id()) {
                $categoria->set_id(NULL);
            }
            $categoria->descricao = mb_strtolower(trim($categoria->descricao), mb_detect_encoding($categoria->descricao));
            $categoria->tipo = @$_REQUEST['tipo'] ? @$_REQUEST['tipo'] : '0';
            $categoria->usuario = unserialize($_SESSION['usuario'])->get_id();
            $servico = new categoria_servico();
            $servico->salvar($categoria);
            $_SESSION['mensagem'] = 'Categoria salva com sucesso!';
            return $categoria;
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
            return $categoria;
        }
    }

    function retornar() {
        try {
            $categoria = new categoria();
            $categoria->set_id(@$_GET['id']);
            $servico = new categoria_servico();
            return $servico->retornar($categoria);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function novo() {
        $categoria = new categoria();
        $this->get_obj($categoria);
        $categoria->tipo = @$_REQUEST['tipo'] ? @$_REQUEST['tipo'] : '0';
        $categoria->usuario = unserialize($_SESSION['usuario'])->get_id();
        return $categoria;
    }

    function excluir() {
        try {
            $categoria = new categoria();
            $categoria->set_id(@$_REQUEST['id']);
            $servico = new categoria_servico();
            $servico->excluir($categoria);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_categorias($categoria) {
        try {
            $servico = new categoria_servico();
            return $servico->get_categorias($categoria);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }


    function listar_por_tipo($categoria) {
        $servico = new categoria_servico();
        $usuario = unserialize($_SESSION['usuario']);
        if (is_object($usuario)) {
            $result = $servico->listar(
                    array('usuario'=>$usuario->get_id(),
                        'tipo' => $categoria->get_tipo()));
            return $result;
        } else {
            return array();
        }        
    }
    
}

?>
