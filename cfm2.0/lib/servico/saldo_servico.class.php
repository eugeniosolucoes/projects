<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of saldo_dao
 *
 * @author eugenio
 */

class saldo_servico {

    function get_anos() {
        $usuario = unserialize($_SESSION['usuario']);
        $dao = new saldo_dao();
        if (is_object($usuario)) {
            return $dao->get_anos($usuario->get_id());
        } else {
            return array();
        }
    }

    function listar($params) {
        $saldo = new saldo();
        $dao = new saldo_dao();
        $list = $dao->retornar_todos_por_ordenado($saldo, $params, ' ano DESC, mes DESC');
        return $list;
    }

    function salvar($saldo) {
        try {
            $dao = new saldo_dao();
            if (is_null($saldo->get_id())) {
                $dao->incluir($saldo);
            } else {
                $dao->atualizar($saldo);
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function retornar($saldo) {
        $dao = new saldo_dao();
        return $dao->retornar($saldo);
    }

    function excluir($saldo) {
        try {
            $dao = new saldo_dao();
            $dao->excluir($saldo);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

}

?>
