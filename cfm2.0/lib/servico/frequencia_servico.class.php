<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of frequencia_dao
 *
 * @author eugenio
 */

class frequencia_servico {

    function listar($params) {
        $frequencia = new frequencia();
        $dao = new frequencia_dao();
        $list = $dao->retornar_todos_por_ordenado($frequencia, $params, ' descricao ');
        return $list;
    }

    function salvar($frequencia) {
        try {
            if (!$frequencia->descricao) {
                throw new Exception('A descrição é requerida!');
            }
            $dao = new frequencia_dao();
            if (is_null($frequencia->get_id())) {
                $dao->incluir($frequencia);
            } else {
                $dao->atualizar($frequencia);
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function retornar($frequencia) {
        $dao = new frequencia_dao();
        return $dao->retornar($frequencia);
    }

    function excluir($frequencia) {
        try {
            $dao = new frequencia_dao();
            $dao->excluir($frequencia);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

}

?>
