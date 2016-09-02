<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of saldo_controle
 *
 * @author cpo-202
 */

class saldo_controle extends core_controle {

    function listar_meses($current_month = TRUE) {
        foreach (saldo::$meses as $key => $value) {
            if ($current_month) {
                if (isset($_REQUEST['mes'])) {
                    printf("<option value='$key' %s >$value</option>", $_REQUEST['mes'] == $key ? 'selected' : '');
                } else {
                    printf("<option value='$key' %s >$value</option>", date('m') + 1 == $key ? 'selected' : '');
                }
            } else {
                printf("<option value='$key' >$value</option>");
            }
        }
    }

    function listar_anos($current_year = TRUE) {

        $servico = new saldo_servico();
        $anos = $servico->get_anos();
        foreach ($anos as $value) {
            if ($current_year) {
                if (isset($_REQUEST['ano'])) {
                    printf("<option value='$value' %s >$value</option>", $_REQUEST['ano'] == $value ? 'selected' : '');
                } else {
                    printf("<option value='$value' %s >$value</option>", date('Y') == $value ? 'selected' : '');
                }
            } else {
                printf("<option value='$value' >$value</option>");
            }
        }
    }

    function listar() {
        $servico = new saldo_servico();
        $usuario = unserialize($_SESSION['usuario']);
        if (is_object($usuario))
            return $servico->listar(array('usuario' => $usuario->get_id()));
        else
            return array();
    }

    function salvar() {
        if (@$_POST['cmd_salvar']) {
            try {
                $saldo = new saldo();
                $saldo->set_id(@$_POST['id']);
                $saldo->set_mes($_POST['mes']);
                $saldo->set_ano($_POST['ano']);
                $saldo->set_valor($_POST['valor']);
                $saldo->set_usuario(unserialize($_SESSION['usuario'])->get_id());
                $servico = new saldo_servico();
                if (@$_POST['id'])
                    $servico->atualizar($saldo);
                else
                    $servico->incluir($saldo);
                $_SESSION['mensagem'] = 'Valor salvo com sucesso!';
            } catch (Exception $ex) {
                $_SESSION['mensagem'] = $ex->getMessage();
            }
        }
    }

    function retornar() {
        if (@$_GET['id']) {
            try {
                $saldo = new saldo();
                $saldo->set_id(@$_GET['id']);
                $servico = new saldo_servico();
                return $servico->retornar($saldo);
            } catch (Exception $ex) {
                $_SESSION['mensagem'] = $ex->getMessage();
            }
        }
    }

    function excluir() {
        if (@$_POST['cmd_excluir']) {
            try {
                $saldo = new saldo();
                $saldo->set_id(@$_POST['id']);
                $servico = new saldo_servico();
                $servico->excluir($saldo);
            } catch (Exception $ex) {
                $_SESSION['mensagem'] = $ex->getMessage();
            }
        }
    }
    
}

?>
