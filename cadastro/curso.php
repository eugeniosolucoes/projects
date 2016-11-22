<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of curso
 *
 * @author eugenio
 */
class curso {

    var $id;
    var $descricao;
    var $selecionado;

    public function __construct( $id, $descricao ) {
        $this->id = $id;
        $this->descricao = $descricao;
    }

    public function get_id() {
        return $this->id;
    }

    public function get_descricao() {
        return $this->descricao;
    }

    public function get_selecionado() {
        return $this->selecionado;
    }

    public function set_id( $id ) {
        $this->id = $id;
        return $this;
    }

    public function set_descricao( $descricao ) {
        $this->descricao = $descricao;
        return $this;
    }

    public function set_selecionado( $selecionado ) {
        $this->selecionado = $selecionado;
        return $this;
    }

}
