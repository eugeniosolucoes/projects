<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of frequencia_controle
 *
 * @author cpo-202
 */
class frequencia_controle extends core_controle {

    function listar() {
        $servico = new frequencia_servico();
        $usuario = unserialize($_SESSION['usuario']);
        return $servico->listar(array('usuario' => $usuario->get_id()));
    }

    function salvar() {
        $frequencia = new frequencia();
        try {
            $this->get_obj($frequencia);
            if(!$frequencia->get_id()){
                $frequencia->set_id(NULL);
            }
            $frequencia->descricao = mb_strtolower(trim($frequencia->descricao), mb_detect_encoding($frequencia->descricao));
            $frequencia->usuario = unserialize($_SESSION['usuario'])->get_id();
            $servico = new frequencia_servico();
            $servico->salvar($frequencia);
            $_SESSION['mensagem'] = 'Frequência salva com sucesso!';
            return $frequencia;
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
            return $frequencia;
        }
    }

    function retornar() {
        try {
            $frequencia = new frequencia();
            $frequencia->set_id(@$_GET['id']);
            $servico = new frequencia_servico();
            return $servico->retornar($frequencia);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function novo() {
        $frequencia = new frequencia();
        $this->get_obj($frequencia);
        $frequencia->usuario = unserialize($_SESSION['usuario'])->get_id();
        return $frequencia;
    }

    function excluir() {
        try {
            $frequencia = new frequencia();
            $frequencia->set_id(@$_REQUEST['id']);
            $servico = new frequencia_servico();
            $servico->excluir($frequencia);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

}

?>
