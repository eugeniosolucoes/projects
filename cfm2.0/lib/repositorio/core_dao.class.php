<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of core
 *
 * @author eugenio
 */
class core {

    const TABLE_PREFIX = '';

    function get_conexao() {
        $sec = new seguranca();
        $link = mysql_connect(
                //         $sec->decifrar(seguranca::$HOST),
//                                $sec->decifrar(seguranca::$USER), 
//                $sec->decifrar(seguranca::$PASSWD));

	'localhost', 'root', 'redelocal');
        //mysql_select_db($sec->decifrar(seguranca::$DATABASE), $link);
        mysql_select_db('eugeniosolucoes3', $link);
        return $link;
    }

    function get_params($obj) {
        $api = new ReflectionClass($obj);
        $params = array();
        foreach ($api->getProperties() as $propertie) {
            if (is_scalar($propertie->getValue($obj))) {
                $params[$propertie->getName()] = $propertie->getValue($obj);
            }
        }
        return $params;
    }

    function incluir($obj, $prefix = self::TABLE_PREFIX) {
        $api = new ReflectionClass($obj);
        $params = $this->get_params($obj);
        $sql = 'INSERT INTO ' . strtolower($prefix . $api->getName()) . ' (';
        try {
            $sql .= implode(',', array_keys($params)) . ') VALUES(\'';
            $sql .= implode("','", array_values($params)) . '\')';
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            $id = mysql_query("SELECT LAST_INSERT_ID();", $link);
            while ($row = mysql_fetch_row($id)) {
                $obj->set_id($row[0]);
            }
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
    }

    function atualizar($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        $api = new ReflectionClass($obj);
        $params = $this->get_params($obj);
        $sql = 'UPDATE ' . strtolower($prefix . $api->getName()) . ' SET ';
        try {
            foreach ($params as $k => $v) {
                if ($k != $param_id) {
                    $sql .= "$k = '$v', ";
                }
            }
            $sql = substr($sql, 0, -2);
            if (is_array($param_id)) {
                $sql .= " WHERE ";
                foreach ($param_id as $key => $value) {
                    $sql .= (is_numeric($value)) ? " $key = $value " :
                            " $key = '$value' ";
                    $sql .= ' AND ';
                }
                $sql = substr($sql, 0, -strlen(' AND '));
            } else {
                $sql .= " WHERE $param_id = {$params[$param_id]}";
            }
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
    }

    function retornar($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        $api = new ReflectionClass($obj);
        $params = $this->get_params($obj);
        $sql = 'SELECT * ';
        try {
            $sql .= 'FROM ' . strtolower($prefix . $api->getName()) . " WHERE $param_id =
{$params[$param_id]}";
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result)) {
                    foreach ($api->getProperties() as $prop) {
                        if (@$row[$prop->getName()]) {
                            $prop->setValue($obj, $row[$prop->getName()]);
                        }
                    }
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $obj;
    }

    function excluir($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        $api = new ReflectionClass($obj);
        $params = $this->get_params($obj);
        $sql = 'DELETE ';
        try {
            $sql .= 'FROM ' . strtolower($prefix . $api->getName()) . '';
            if (is_array($param_id)) {
                $sql .= " WHERE ";
                foreach ($param_id as $key => $value) {
                    $sql .= (is_numeric($value)) ? " $key = $value " :
                            " $key = '$value' ";
                    $sql .= ' AND ';
                }
                $sql = substr($sql, 0, -strlen(' AND '));
            } else {
                $sql .= " WHERE $param_id = {$params[$param_id]}";
            }
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
    }

    function retornar_todos($obj, $prefix = self::TABLE_PREFIX) {
        $colecao = array();
        $api = new ReflectionClass($obj);
        $sql = 'SELECT * ';
        try {
            $sql .= 'FROM ' . strtolower($prefix . $api->getName());
            $link = $this->get_conexao();
            $result = mysql_query($sql, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result)) {
                    $novo = $api->newInstance();
                    $api_novo = new ReflectionClass($novo);
                    foreach ($api_novo->getProperties() as $prop) {
                        if (@$row[$prop->getName()]) {
                            $prop->setValue($novo, $row[$prop->getName()]);
                        }
                    }
                    $colecao[] = $novo;
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $colecao;
    }

    function retornar_todos_por($obj, $params, $prefix = self::TABLE_PREFIX) {
        $colecao = array();
        $AND = ' AND ';
        $api = new ReflectionClass($obj);
        $sql = 'SELECT * ';
        try {
            $sql .= 'FROM ' . strtolower($prefix . $api->getName());
            if (is_array($params) && count($params)) {
                $sql .= " WHERE ";
                foreach ($params as $key => $value) {
                    $sql .= (is_numeric($value)) ? " $key = $value " :
                            " $key = '$value' ";
                    $sql .= $AND;
                }
            }
            $sql1 = substr($sql, 0, -strlen($AND));
            $link = $this->get_conexao();
            $result = mysql_query($sql1, $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result)) {
                    $novo = $api->newInstance();
                    $api_novo = new ReflectionClass($novo);
                    foreach ($api_novo->getProperties() as $prop) {
                        if (@$row[$prop->getName()]) {
                            $prop->setValue($novo, $row[$prop->getName()]);
                        }
                    }
                    $colecao[] = $novo;
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $colecao;
    }

    function retornar_todos_por_ordenado($obj, $params, $sort, $prefix = self::TABLE_PREFIX) {
        $colecao = array();
        $AND = ' AND ';
        $api = new ReflectionClass($obj);
        $sql = 'SELECT * ';
        try {
            $sql .= 'FROM ' . strtolower($prefix . $api->getName());
            if (is_array($params) && count($params)) {
                $sql .= " WHERE ";
                foreach ($params as $key => $value) {
                    $sql .= (is_numeric($value)) ? " $key = $value " :
                            " $key = '$value' ";
                    $sql .= $AND;
                }
            }
            $sql1 = substr($sql, 0, -strlen($AND));
            $link = $this->get_conexao();
            $result = mysql_query($sql1 . " ORDER BY $sort ", $link);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result)) {
                    $novo = $api->newInstance();
                    $api_novo = new ReflectionClass($novo);
                    foreach ($api_novo->getProperties() as $prop) {
                        if (@$row[$prop->getName()]) {
                            $prop->setValue($novo, $row[$prop->getName()]);
                        }
                    }
                    $colecao[] = $novo;
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysql_close($link);
        return $colecao;
    }

}

?>
