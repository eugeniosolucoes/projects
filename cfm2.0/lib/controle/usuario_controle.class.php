<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuario_controle
 *
 * @author cpo-202
 */
class usuario_controle extends core_controle {

    function execute() {
        parent::execute();
        switch (@$_REQUEST['comando']) {
            case 'periodos':
                return $this->get_periodos();
                break;
            default:
                break;
        }
    }

    function exibir_usuario() {
        try {
            $usuario = @unserialize($_SESSION['usuario']);
            if (is_object($usuario)) {
                return $usuario->get_login();
            }
        } catch (Exception $e) {
            return NULL;
        }
    }
    
    function get_periodos() {
        try {
            $usuario = @unserialize($_SESSION['usuario']);
            $periodos = array();
            if ($usuario !== null) {
                $ano = @$_REQUEST['ano'] ? @$_REQUEST['ano'] : date('Y');
                $dao = new usuario_dao();
                $link = $dao->get_conexao();
                $sql_anos = "SELECT DISTINCT ano FROM vw_periodos_json "
                        . "where usuario = {$usuario->get_id()}  order by ano;";
                $result = mysql_query($sql_anos, $link);
                if (!$result) {
                    throw new Exception('Invalid query: ' . mysql_error());
                } else {
                    while ($row = mysql_fetch_assoc($result)) {
                        $anos[] = $row['ano'];
                    }
                }        
                $sql_periodos = "SELECT mes, ano, creditos, debitos, balanco FROM "
                        . "vw_periodos_json where ano = {$ano} "
                        . "and usuario = {$usuario->get_id()} order by mes;";
                $result = mysql_query($sql_periodos, $link);
                if (!$result) {
                    throw new Exception('Invalid query: ' . mysql_error());
                } else {
                    while ($row = mysql_fetch_assoc($result)) {
                        $periodos[] = $row;
                    }
                }
                $colecao['anos'] = $anos;
                $colecao['periodos'] = $periodos;
                Header('Content-Type: application/json');
                die(json_encode($colecao));
            }
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

}

?>
