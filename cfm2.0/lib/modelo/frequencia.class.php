<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of frequencia
 *
 * @author eugenio
 */


class frequencia {

    var $id;
    var $descricao;
    var $usuario;
    var $frequencia;
    
    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }

    public function get_descricao() {
        return $this->descricao;
    }

    public function set_descricao($descricao) {
        $this->descricao = $descricao;
    }

    public function get_usuario() {
        return $this->usuario;
    }

    public function set_usuario($usuario) {
        $this->usuario = $usuario;
    }

    public function get_frequencia() {
        return $this->frequencia;
    }

    public function set_frequencia($frequencia) {
        $this->frequencia = $frequencia;
    }
    
}

?>
