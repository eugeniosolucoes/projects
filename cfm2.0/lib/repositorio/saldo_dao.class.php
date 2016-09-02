<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of saldo_dao
 *
 * @author eugenio
 */

class saldo_dao extends core {

    function get_anos($usuario) {
        $anos = array(date('Y'));
        try {
            $link = $this->get_conexao();
            $sql = sprintf("SELECT ano FROM saldo WHERE usuario = %d GROUP BY ano", $usuario);
            $result = mysql_query($sql, $link);

            if (mysql_num_rows($result)) {
                unset($anos);
                while ($row = mysql_fetch_assoc($result)) {
                    $anos[] = $row["ano"];
                }
            }
            mysql_free_result($result);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $anos;
    }

}

?>
