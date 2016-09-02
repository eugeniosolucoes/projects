<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of categoria
 *
 * @author eugenio
 */


class categoria {

    var $id;
    var $descricao;
    var $tipo;
    var $usuario;
    var $categoria;

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

    public function get_tipo() {
        return $this->tipo;
    }

    public function set_tipo($tipo) {
        $this->tipo = $tipo;
    }

    public function get_usuario() {
        return $this->usuario;
    }

    public function set_usuario($usuario) {
        $this->usuario = $usuario;
    }


    public function get_categoria() {
        return $this->categoria;
    }

    public function set_categoria($categoria) {
        $this->categoria = $categoria;
    }
    
}

?>
