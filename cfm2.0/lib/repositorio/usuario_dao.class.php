<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuario_dao
 *
 * @author eugenio
 */

class usuario_dao extends core {

    function autenticar($usuario) {
        try {
            $link = $this->get_conexao();
            $sql = sprintf("SELECT * FROM usuario WHERE login = '%s'
                and senha = '%s'", mysql_real_escape_string($usuario->get_login()), $usuario->get_senha());
            $result = mysql_query($sql, $link);

            if (mysql_num_rows($result)) {
                while ($row = mysql_fetch_assoc($result)) {
                    $usuario->set_id($row["id"]);
                    $usuario->set_login($row["login"]);
                    $usuario->set_nome($row["nome"]);
                }
            }
            else {
                $usuario = NULL;
            }
            mysql_free_result($result);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $usuario;
    }

    public function retornar_todos($obj, $prefix = self::TABLE_PREFIX) {
        return parent::retornar_todos($obj, $prefix);
    }

}

?>
