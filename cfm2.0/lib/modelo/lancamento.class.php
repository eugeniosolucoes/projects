<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of lancamento
 *
 * @author eugenio
 */
class lancamento {

    var $id;
    var $tipo;
    var $descricao;
    var $inclusao;
    var $quantidade;
    var $realizado;
    var $valor;
    var $frequencia;
    var $categorias;
    var $link;
    var $usuario;

    function __construct() {
        $date = new DateTime();
        $this->inclusao = $date->format('d/m/Y');
    }

    public function get_inclusao() {
        return $this->inclusao;
    }

    public function set_inclusao($inclusao) {
        $this->inclusao = $inclusao;
    }

    public function get_realizado() {
        return $this->realizado;
    }

    public function set_realizado($realizado) {
        $this->realizado = $realizado;
    }

    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }

    public function get_tipo() {
        return $this->tipo;
    }

    public function set_tipo($tipo) {
        $this->tipo = $tipo;
    }

    public function get_descricao() {
        return $this->descricao;
    }

    public function set_descricao($descricao) {
        $this->descricao = $descricao;
    }

    public function get_quantidade() {
        return $this->quantidade;
    }

    public function set_quantidade($quantidade) {
        $this->quantidade = $quantidade;
    }

    public function get_valor() {
        return $this->valor;
    }

    public function set_valor($valor) {
        $this->valor = $valor;
    }

    public function get_frequencia() {
        return $this->frequencia;
    }

    public function set_frequencia($frequencia) {
        $this->frequencia = $frequencia;
    }
    
    public function get_usuario() {
        return $this->usuario;
    }

    public function set_usuario($usuario) {
        $this->usuario = $usuario;
    }

    public function get_categorias() {
        return $this->categorias;
    }

    public function set_categorias($categorias) {
        $this->categorias = $categorias;
    }

    function get_link() {
        return urldecode( $this->link );
    }

    function set_link($link) {
        $this->link = urlencode( $link );
    }

}

?>
