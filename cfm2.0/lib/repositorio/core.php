<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of persistencia
 *
 * @author eugenio
 */
function get_conexao() {
    $link = mysql_connect('localhost', 'root', 'redelocal');
    mysql_select_db('cfm', $link);
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

function incluir($obj, $prefix = '') {
    $api = new ReflectionClass($obj);
    $params = get_params($obj);
    $sql = 'INSERT INTO ' . strtolower($prefix . $api->getName()) . ' (';
    try {
        $sql .= implode(',', array_keys($params)) . ') VALUES(\'';
        $sql .= implode("','", array_values($params)) . '\')';
        $link = get_conexao();
        $result = mysql_query($sql, $link);
        if (!$result) {
            die('Invalid query: ' . mysql_error());
        }
    } catch (Exception $e) {
        throw new Exception($e->getMessage());
    }
    mysql_close($link);
}

function atualizar($obj,  $prefix = '', $param_id = 'id') {
    $api = new ReflectionClass($obj);
    $params = get_params($obj);
    $sql = 'UPDATE ' . strtolower($prefix . $api->getName()) . ' SET ';
    try {
        foreach ($params as $k => $v) {
            if ($k != $param_id) {
                $sql .= "$k = '$v', ";
            }
        }
        $sql = substr($sql, 0, -2);
        $sql .= " WHERE $param_id = {$params[$param_id]}";
        $link = get_conexao();
        $result = mysql_query($sql, $link);
        if (!$result) {
            die('Invalid query: ' . mysql_error());
        }
    } catch (Exception $e) {
        throw new Exception($e->getMessage());
    }
    mysql_close($link);
}

function retornar($obj, $prefix = '', $param_id = 'id') {
    $api = new ReflectionClass($obj);
    $params = get_params($obj);
    $sql = 'SELECT * ';
    try {
        $sql .= 'FROM ' . strtolower($prefix . $api->getName()) . " WHERE $param_id =
{$params[$param_id]}";
        $link = get_conexao();
        $result = mysql_query($sql, $link);
        if (!$result) {
            die('Invalid query: ' . mysql_error());
        } else {
            while ($row = mysql_fetch_assoc($result)) {
                foreach ($api->getProperties() as $prop) {
                    if ($row[$prop->getName()]) {
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

function retornar_todos($obj, $prefix = '') {
    $colecao = array();
    $api = new ReflectionClass($obj);
    $sql = 'SELECT * ';
    try {
        $sql .= 'FROM ' . strtolower($prefix . $api->getName());
        $link = get_conexao();
        $result = mysql_query($sql, $link);
        if (!$result) {
            die('Invalid query: ' . mysql_error());
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

?>
