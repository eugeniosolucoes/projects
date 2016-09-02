<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of perfil
 *
 * @author cpo-202
 */
class perfil {
    
    var $id;
    var $propriedade;
    var $valor;
    
    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }

    public function get_propriedade() {
        return $this->propriedade;
    }

    public function set_propriedade($propriedade) {
        $this->propriedade = $propriedade;
    }

    public function get_valor() {
        return $this->valor;
    }

    public function set_valor($valor) {
        $this->valor = $valor;
    }


}

?>
