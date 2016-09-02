<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuario
 *
 * @author eugenio
 */
class usuario {
    
    var $id;
    var $login;
    var $email;
    var $nome;
    var $senha;
    
    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }

    public function get_login() {
        return $this->login;
    }

    public function set_login($login) {
        $this->login = $login;
    }

    public function get_nome() {
        return $this->nome;
    }

    public function set_nome($nome) {
        $this->nome = $nome;
    }

    public function get_senha() {
        return $this->senha;
    }

    public function set_senha($senha) {
        $this->senha = base64_encode(sha1($senha, TRUE));
    }

    public function get_email() {
        return $this->email;
    }

    public function set_email($email) {
        $this->email = $email;
    }
}

?>
