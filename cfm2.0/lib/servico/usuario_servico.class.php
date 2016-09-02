<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuario_servico
 *
 * @author eugenio
 */
class usuario_servico extends core_servico {

    private function autenticar($login, $senha) {
        $user = new usuario();
        $user->set_login($login);
        $user->set_senha($senha);
        $dao = new usuario_dao();
        return $dao->autenticar($user);
    }

    function login($login, $senha) {
        $usuario = $this->autenticar($login, $senha);
        if ($usuario) {
            $_SESSION['usuario'] = serialize($usuario);
            return TRUE;
        } else {
            return FALSE;
        }
    }

    function listar() {
        try {
            $dao = new usuario_dao();
            return $dao->retornar_todos(new usuario());
        } catch (Exception $ex) {
            throw new Exception($ex->getMessage());
        }
    }

}

?>
