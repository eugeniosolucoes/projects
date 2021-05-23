<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of lancamento_dao
 *
 * @author eugenio
 */
class lancamento_dao extends core {

    public function retornar_todos_por_ordenado($obj, $params, $sort, $prefix = self::TABLE_PREFIX) {
        $this->atualizar_lancamentos_realizados();
        return parent::retornar_todos_por_ordenado($obj, $params, $sort, $prefix);
    }

    public function atualizar($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        $this->format_date_to_bd($obj);
        parent::atualizar($obj, $prefix, $param_id);
        $this->format_date_to_view($obj);
        return $obj;
    }

    public function incluir($obj, $prefix = self::TABLE_PREFIX) {
        $this->format_date_to_bd($obj);
        parent::incluir($obj, $prefix);
        $this->format_date_to_view($obj);
        return $obj;
    }

    public function retornar($obj, $prefix = self::TABLE_PREFIX, $param_id = 'id') {
        parent::retornar($obj, $prefix, $param_id);
        $this->format_date_to_view($obj);
        return $obj;
    }

    public static function format_date_to_bd($obj) {
        $tmp = explode('/', $obj->inclusao);
        $date = new DateTime("$tmp[2]-$tmp[1]-$tmp[0]");
        $obj->inclusao = $date->format('Y-m-d');
    }

    public static function format_date_to_view($obj) {
        $tmp = explode('-', $obj->inclusao);
        $date = new DateTime("$tmp[0]-$tmp[1]-$tmp[2]");
        $obj->inclusao = $date->format('d/m/Y');
    }

    function atualizar_lancamentos_realizados() {
        $hoje = date('Y-m-d');
        $sql1 = "UPDATE lancamento set realizado = 1 WHERE inclusao <= '$hoje'";
        $sql2 = "UPDATE lancamento set realizado = 0 WHERE inclusao > '$hoje'";
        try {
            $link = $this->get_conexao();
            $result1 = mysqli_query($link, $sql1);
            if (!$result1) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            }
            $result2 = mysqli_query($link, $sql2);
            if (!$result2) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
    }

    function get_categorias($id) {
        $colecao = array();
        $sql = 'SELECT categorias_id FROM lancamento_categoria WHERE lancamentos_id = ' . $id;
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['categorias_id'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao;
    }

    function get_categorias_descricao_por_lancamento($id) {
        $colecao = array();
        $sql = 'select c.descricao from categoria c inner join 
            lancamento_categoria lc on c.id = lc.categorias_id 
            inner join lancamento l on l.id = lc.lancamentos_id 
            where l.id = ' . $id;
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['descricao'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao;
    }

    function salvar_categorias($id_lancamento, $categorias) {
        $sql_delete = 'DELETE FROM lancamento_categoria WHERE lancamentos_id = ' . $id_lancamento;
        try {
            $link = $this->get_conexao();
            mysqli_query($link, $sql_delete);
            if ($categorias) {
                $sql_insert = 'INSERT INTO lancamento_categoria (lancamentos_id, categorias_id) VALUES';
                $valores = array();
                foreach ($categorias as $categoria) {
                    $valores[] = "($id_lancamento, $categoria)";
                }
                $sql_insert .= implode(',', $valores);
                mysqli_query($link, $sql_insert);
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
    }

    function get_anos($usuario) {
        $colecao = array();
        $sql = sprintf("SELECT YEAR(inclusao) AS ano FROM lancamento WHERE usuario = %d GROUP BY 1", $usuario);
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['ano'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao;
    }

    function get_creditos($mes, $ano, $usuario) {
        $colecao = array();
        $sql = sprintf("SELECT SUM(valor * quantidade) AS creditos FROM lancamento 
            WHERE MONTH(inclusao) = $mes 
            AND YEAR(inclusao) = $ano 
            AND usuario = $usuario 
            AND tipo = 1;");
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['creditos'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao[0];
    }

    function get_debitos($mes, $ano, $usuario) {
        $colecao = array();
        $sql = sprintf("SELECT SUM(valor * quantidade) AS debitos FROM lancamento 
            WHERE MONTH(inclusao) = $mes 
            AND YEAR(inclusao) = $ano 
            AND usuario = $usuario 
            AND tipo = 0;");
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['debitos'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao[0];
    }

    function get_creditos_nao_realizados($mes, $ano, $usuario) {
        $colecao = array();
        $sql = sprintf("SELECT SUM(valor * quantidade) AS creditos FROM lancamento 
            WHERE MONTH(inclusao) = $mes 
            AND YEAR(inclusao) = $ano 
            AND usuario = $usuario 
            AND tipo = 1 
            AND realizado = 1;");
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['creditos'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao[0];
    }

    function get_debitos_nao_realizados($mes, $ano, $usuario) {
        $colecao = array();
        $sql = sprintf("SELECT SUM(valor * quantidade) AS debitos FROM lancamento 
            WHERE MONTH(inclusao) = $mes 
            AND YEAR(inclusao) = $ano 
            AND usuario = $usuario 
            AND tipo = 0
            AND realizado = 1;");
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql);
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
                    $colecao[] = $row['debitos'];
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        mysqli_close($link);
        return $colecao[0];
    }

    function pesquisar($obj, $usuario, $params, $sort) {
        $colecao = array();
        $params_formatados = array();
        $api = new ReflectionClass($obj);
        $periodo = array();
        $inicio = '';
        $fim = '';
        foreach ($params as $value) {
            if (preg_match("/inicio:\d\d\/\d\d\/\d\d\d\d/", $value, $periodo)) {
                $date = DateTime::createFromFormat('d/m/Y', substr($periodo[0], 7));
                $inicio = $date->format('Y-m-d');
                continue;
            }
            if (preg_match("/fim:\d\d\/\d\d\/\d\d\d\d/", $value, $periodo)) {
                $date = DateTime::createFromFormat('d/m/Y', substr($periodo[0], 4));
                $fim = $date->format('Y-m-d');
                continue;
            }
            $params_formatados[] = " ( remove_acentos(l.descricao) LIKE remove_acentos('%$value%')
                OR remove_acentos(c.descricao) LIKE remove_acentos('%$value%')
                OR remove_acentos(f.descricao) LIKE remove_acentos('%$value%')
		OR l.link LIKE '%$value%' ) ";
        }
        $sql = "SELECT DISTINCT l.* from lancamento l 
LEFT OUTER JOIN frequencia f ON f.id = l.frequencia 
LEFT OUTER JOIN lancamento_categoria lc ON l.id = lc.lancamentos_id  
LEFT OUTER JOIN categoria c ON c.id = lc.categorias_id 
            WHERE l.usuario = $usuario AND " . implode(' AND ', $params_formatados);
        if( $inicio && $fim ){
            $sql .= " AND l.inclusao BETWEEN '$inicio' AND '$fim' ";
        } elseif ( $inicio ) {
            $sql .= " AND l.inclusao >= '$inicio'";
        } elseif ( $fim ) {
            $sql .= " AND l.inclusao <= '$fim'";
        }
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql . " ORDER BY $sort ");
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
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
        mysqli_close($link);
        return $colecao;
    }
    
    function pesquisar_old($obj, $usuario, $params, $sort) {
        $colecao = array();
        $params_formatados = array();
        $api = new ReflectionClass($obj);
        $periodo = array();
        $inicio = '';
        $fim = '';
        foreach ($params as $value) {
            if (preg_match("/inicio:\d\d\/\d\d\/\d\d\d\d/", $value, $periodo)) {
                $date = DateTime::createFromFormat('d/m/Y', substr($periodo[0], 7));
                $inicio = $date->format('Y-m-d');
                continue;
            }
            if (preg_match("/fim:\d\d\/\d\d\/\d\d\d\d/", $value, $periodo)) {
                $date = DateTime::createFromFormat('d/m/Y', substr($periodo[0], 4));
                $fim = $date->format('Y-m-d');
                continue;
            }
            $params_formatados[] = " (l.descricao LIKE '%$value%'
                OR c.descricao LIKE '%$value%'
                OR f.descricao LIKE '%$value%'
		OR l.link LIKE '%$value%' ) ";
        }
        $sql = "SELECT DISTINCT l.* from lancamento l 
LEFT OUTER JOIN frequencia f ON f.id = l.frequencia 
LEFT OUTER JOIN lancamento_categoria lc ON l.id = lc.lancamentos_id  
LEFT OUTER JOIN categoria c ON c.id = lc.categorias_id 
            WHERE l.usuario = $usuario AND " . implode(' AND ', $params_formatados);
        if( $inicio && $fim ){
            $sql .= " AND l.inclusao BETWEEN '$inicio' AND '$fim' ";
        } elseif ( $inicio ) {
            $sql .= " AND l.inclusao >= '$inicio'";
        } elseif ( $fim ) {
            $sql .= " AND l.inclusao <= '$fim'";
        }
        try {
            $link = $this->get_conexao();
            $result = mysqli_query($link, $sql . " ORDER BY $sort ");
            if (!$result) {
                throw new Exception('Invalid query: ' . mysqli_error($link));
            } else {
                while ($row = mysqli_fetch_assoc($result)) {
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
        mysqli_close($link);
        return $colecao;
    }

    function copiar($obj, $mes_novo, $ano_novo) {
        try {
            $lancamento = parent::retornar($obj);
            $lancamento->tipo = $lancamento->tipo ? '1' : '0';
            $lancamento_id = $lancamento->get_id();
            $lancamento->set_id(NULL);
            $data = explode('-', $lancamento->inclusao);
            $mes_atual = $data[1];
            $ano_atual = $data[0];
            $diff_ano = $ano_novo - $ano_atual;
            $diff = $mes_novo - $mes_atual;
            $diff += ($diff_ano * 12);

            $api = new ReflectionClass($lancamento);
            $params = $this->get_params($lancamento);
            $params['inclusao'] = '[COPY_DATE]';
            $sql = 'INSERT INTO ' . strtolower(self::TABLE_PREFIX . $api->getName()) . ' (';
            try {
                $sql .= implode(',', array_keys($params)) . ') VALUES(\'';
                $sql .= implode("','", array_values($params)) . '\')';
                $sql = str_replace("'[COPY_DATE]'", sprintf("DATE_ADD('%s', INTERVAL %s MONTH)", $lancamento->inclusao, $diff), $sql);
                $link = $this->get_conexao();
                $result = mysqli_query($link, $sql);
                $id = mysqli_query($link, "SELECT LAST_INSERT_ID();");
                while ($row = mysqli_fetch_row($id)) {
                    $lancamento->set_id($row[0]);
                }
                if (!$result) {
                    throw new Exception('Invalid query: ' . mysqli_error($link));
                }
            } catch (Exception $e) {
                throw new Exception($e->getMessage());
            }
            mysqli_close($link);


            $categorias = $this->get_categorias($lancamento_id);
            $this->salvar_categorias($lancamento->get_id(), $categorias);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

}

?>
