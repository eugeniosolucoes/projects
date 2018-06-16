<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of categoria_dao
 *
 * @author eugenio
 */

class categoria_dao extends core {
    public function excluir($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        $sql = "DELETE FROM lancamento_categoria WHERE categorias_id = {$obj->id}";
        try {
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        parent::excluir($obj, $prefix, $param_id);
    }
}

?>
