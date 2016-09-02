<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of saldo
 *
 * @author cpo-202
 */
class saldo {

    var $id;
    var $ano;
    var $mes;
    var $valor_inicial;
    var $usuario;
    var $saldo;
    static $meses = array(
        1 => 'janeiro',
        'fevereiro',
        'março',
        'abril',
        'maio',
        'junho',
        'julho',
        'agosto',
        'setembro',
        'outubro',
        'novembro',
        'dezembro'
    );

    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }

    public function get_ano() {
        return $this->ano;
    }

    public function set_ano($ano) {
        $this->ano = $ano;
    }

    public function get_mes() {
        return $this->mes;
    }

    public function set_mes($mes) {
        $this->mes = $mes;
    }

    public function get_valor_inicial() {
        return $this->valor_inicial;
    }

    public function set_valor_inicial($valor_inicial) {
        $this->valor_inicial = $valor_inicial;
    }

    public function get_usuario() {
        return $this->usuario;
    }

    public function set_usuario($usuario) {
        $this->usuario = $usuario;
    }

    public function get_saldo() {
        return $this->saldo;
    }

    public function set_saldo($saldo) {
        $this->saldo = $saldo;
    }

    public function get_nome_mes() {
        return self::$meses[$this->get_mes()];
    }

}

?>
