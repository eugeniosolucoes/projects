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

}

?>
