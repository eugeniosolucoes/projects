<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of categoria_dao
 *
 * @author eugenio
 */

class categoria_servico extends core_servico {


    function listar($params) {
        $categoria = new categoria();
        $dao = new categoria_dao();
        $list = $dao->retornar_todos_por_ordenado($categoria, $params, ' descricao ');
        return $list;
    }

    function salvar($categoria) {
        try {
            if (!$categoria->descricao) {
                throw new Exception('A descrição é requerida!');
            }
            $dao = new categoria_dao();
            if (is_null($categoria->get_id())) {
                $dao->incluir($categoria);
            } else {
                $dao->atualizar($categoria);
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function retornar($categoria) {
        $dao = new categoria_dao();
        return $dao->retornar($categoria);
    }

    function excluir($categoria) {
        try {
            $dao = new categoria_dao();
            $dao->excluir($categoria);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

}

?>
