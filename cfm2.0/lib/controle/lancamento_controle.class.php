<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of lancamento_controle
 *
 * @author cpo-202
 */
class lancamento_controle extends core_controle {

    function execute() {
        $value = $_REQUEST['comando'];
        switch ($value) {
            case 'incluir':
                return $this->incluir();
            case 'salvar':
                return $this->salvar();
            case 'excluir':
                $this->excluir();
                break;
            case 'listar_tabela':
                return $this->listar();
            case 'retornar':
                return $this->retornar();
            case 'novo':
                return $this->novo();
            case 'retornar_categorias_json':
                return $this->retornar_categorias_json();
            case 'copiar':
                $this->copiar();
                return $this->listar();
            case 'pesquisar':
                return $this->pesquisar();
            case 'get_categorias_json':
                return $this->get_categorias_json();
            case 'get_total_categorias_json':
                return $this->get_total_categorias_json();
            default:
                break;
        }
    }

    function listar() {
        try {
            $servico = new lancamento_servico();
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            return $servico->listar(array('usuario' => $usuario->get_id(),
                        'MONTH(inclusao)' => $mes,
                        'YEAR(inclusao)' => $ano));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function listar_por_dia() {
        $hoje = date("Y-m-d");
        $hoje_parts = explode('-', $hoje);
        try {
            $servico = new lancamento_servico();
            $usuario = new usuario();
            $usuario->set_id(1);
            $this->verificar_login($usuario);
            $dia = $hoje_parts[2];
            $mes = $hoje_parts[1];
            $ano = $hoje_parts[0];
            return $servico->listar(array('usuario' => $usuario->get_id(),
                        'DAY(inclusao)' => $dia,
                        'MONTH(inclusao)' => $mes,
                        'YEAR(inclusao)' => $ano));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function pesquisar() {
        try {
            $servico = new lancamento_servico();
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $criterio = @$_REQUEST['criterio'];
            $inicio = @$_REQUEST['inicio'];
            $fim = @$_REQUEST['fim'];
            if (empty($criterio)) {
                return array();
            } else {
                $params = preg_split("/[\s,]+/", $criterio, -1, PREG_SPLIT_NO_EMPTY);
                if($inicio){
                    $params[] = 'inicio:'.$inicio;
                }
                if($fim){
                    $params[] = 'fim:'.$fim;
                }
                return $servico->pesquisar($usuario->get_id(), $params);
            }
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function incluir() {
        $servico = new lancamento_servico();
        $categorias = @$_REQUEST['categoria'];
        $lancamento = new lancamento();
        try {
            $this->get_obj($lancamento);
            $lancamento->id = NULL;
            $lancamento->quantidade = @$_REQUEST['quantidade'] ? @$_REQUEST['quantidade'] : '0';
            $lancamento->tipo = @$_REQUEST['tipo'] ? @$_REQUEST['tipo'] : '0';
            $lancamento->parcelado = @$_REQUEST['parcelado'] ? 1 : 0;
            $lancamento->qtd_parcelas = @$_REQUEST['qtd_parcelas'] ? @$_REQUEST['qtd_parcelas'] : 0;
            $lancamento->set_link(@$_REQUEST['link']);
            $lancamento->usuario = unserialize($_SESSION['usuario'])->get_id();
            $servico->salvar($lancamento);
            $servico->salvar_categorias($lancamento, $categorias);
            $this->processar_parcelas( $servico, $lancamento, $categorias );
            $_SESSION['mensagem'] = 'Lançamento incluido com sucesso!';
            return $lancamento;
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
            return $lancamento;
        }
    }

    function salvar() {
        $lancamento = new lancamento();
        try {
            $this->get_obj($lancamento);
            if (!$lancamento->get_id()) {
                $lancamento->set_id(NULL);
            }
            $lancamento->quantidade = @$_REQUEST['quantidade'] ? @$_REQUEST['quantidade'] : '0';
            $lancamento->tipo = @$_REQUEST['tipo'] ? @$_REQUEST['tipo'] : '0';
            $lancamento->parcelado = NULL;
            $lancamento->qtd_parcelas = NULL;
            $lancamento->set_link(@$_REQUEST['link']);
            $lancamento->usuario = unserialize($_SESSION['usuario'])->get_id();
            $servico = new lancamento_servico();
            $servico->salvar($lancamento);
            $categorias = @$_REQUEST['categoria'];
            $servico->salvar_categorias($lancamento, $categorias);
            $lancamento->parcelado = @$_REQUEST['parcelado'] ? 1 : 0;
            $lancamento->qtd_parcelas = @$_REQUEST['qtd_parcelas'] ? @$_REQUEST['qtd_parcelas'] : 0;
            $_SESSION['mensagem'] = 'Lançamento salvo com sucesso!';
            return $lancamento;
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
            return $lancamento;
        }
    }

    function retornar() {
        try {
            $lancamento = new lancamento();
            $lancamento->set_id(@$_GET['id']);
            $servico = new lancamento_servico();
            return $servico->retornar($lancamento);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function novo() {
        $lancamento = new lancamento();
        $this->get_obj($lancamento);
        $lancamento->quantidade = 1;
        $lancamento->tipo = @$_REQUEST['tipo'] ? @$_REQUEST['tipo'] : '0';
        $lancamento->usuario = unserialize($_SESSION['usuario'])->get_id();
        return $lancamento;
    }

    function excluir() {
        try {
            $lancamento = new lancamento();
            $lancamentos_id = explode(',', @$_REQUEST['lancamentos']);
            foreach ($lancamentos_id as $value) {
                $lancamento->set_id($value);
                $servico = new lancamento_servico();
                $servico->excluir($lancamento);
            }
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_categorias($lancamento) {
        try {
            $servico = new lancamento_servico();
            return $servico->get_categorias($lancamento);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_categorias_descricao_por_lancamento($lancamento) {
        try {
            $servico = new lancamento_servico();
            return implode(' ', $servico->get_categorias_descricao_por_lancamento($lancamento));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_anos() {
        try {
            $servico = new lancamento_servico();
            return $servico->get_anos(unserialize($_SESSION['usuario'])->get_id());
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_meses() {
        try {
            return saldo::$meses;
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function retornar_categorias_json() {

        $lancamento = new lancamento();
        $lancamento->set_id($_REQUEST['id']);
        $servico = new lancamento_servico();

        try {
            if ($lancamento->get_id() != -1) {
                $servico->retornar($lancamento);
            }
            $lancamento->tipo = $_REQUEST['tipo'];
            $categorias_lancamento = $this->get_categorias($lancamento);
            if (is_null($categorias_lancamento)) {
                $categorias_lancamento = array();
            }
            $categoria_controle = new categoria_controle();
            $categorias_por_tipo = $categoria_controle->listar_por_tipo($lancamento);
            $categorias_json = array();
            foreach ($categorias_por_tipo as $categoria) {
                $categorias_json[$categoria->id]['id'] = $categoria->id;
                $categorias_json[$categoria->id]['descricao'] = $categoria->descricao;
                $categorias_json[$categoria->id]['selected'] = in_array($categoria->id, $categorias_lancamento) ? true : false;
            }
            Header('Content-Type: application/json');
            die(json_encode($categorias_json));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_creditos() {
        $servico = new lancamento_servico();
        try {
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            return $servico->get_creditos($mes, $ano, $usuario->get_id());
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_debitos() {
        $servico = new lancamento_servico();
        try {
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            return $servico->get_debitos($mes, $ano, $usuario->get_id());
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_creditos_nao_realizados() {
        $servico = new lancamento_servico();
        try {
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            return $servico->get_creditos_nao_realizados($mes, $ano, $usuario->get_id());
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_debitos_nao_realizados() {
        $servico = new lancamento_servico();
        try {
            $usuario = unserialize($_SESSION['usuario']);
            $this->verificar_login($usuario);
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            return $servico->get_debitos_nao_realizados($mes, $ano, $usuario->get_id());
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_frequencia($lancamento) {
        try {
            $frequencia_servico = new frequencia_servico();
            $frequencia = new frequencia();
            $frequencia->set_id($lancamento->get_frequencia());
            return $frequencia_servico->retornar($frequencia);
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function verificar_login($usuario) {
        if (!$usuario instanceof usuario) {
            header("Location: " . CONTEXT_PATH . "login.php");
        }
    }

    function copiar() {
        try {
            $lancamentos_id = explode(',', @$_REQUEST['lancamentos']);
            foreach ($lancamentos_id as $value) {
                $lancamento = new lancamento();
                $lancamento->set_id($value);
                $servico = new lancamento_servico();
                $servico->copiar($lancamento, @$_REQUEST['mes'], @$_REQUEST['ano']);
            }
            $_SESSION['mensagem'] = 'Lançamentos copiados com sucesso!';
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_categorias_json() {
        try {
            $categoria_controle = new categoria_controle();
            $categorias = $categoria_controle->listar();
            $categorias_json = array();
            foreach ($categorias as $categoria) {
                if (!in_array($categoria->descricao, $categorias_json)) {
                    $categorias_json[] = $categoria->descricao;
                }
            }
            array_unshift($categorias_json, '');
            Header('Content-Type: application/json');
            die(json_encode($categorias_json));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }

    function get_total_categorias_json() {
        try {
            $dao = new lancamento_dao();
            $link = $dao->get_conexao();
            $categorias_json = array();
            $total_creditos = 0;
            $usuario = unserialize($_SESSION['usuario']);
            $id = $usuario->id;
            $valor_creditos = 
            $mes = @$_REQUEST['mes'] ? $_REQUEST['mes'] : date('m');
            $ano = @$_REQUEST['ano'] ? $_REQUEST['ano'] : date('Y');
            $sql = "SELECT SUM(l.valor * l.quantidade) as 'total_creditos' 
                FROM lancamento l 
                WHERE YEAR(l.inclusao) = $ano AND MONTH(l.inclusao) = $mes AND l.usuario = $id AND l.tipo = 1 ";
            $result1 = mysql_query($sql, $link);
            if (!$result1) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result1)) {
                    $total_creditos = $row['total_creditos'];
                }
            }
            $sql = "SELECT SUM(l.valor * l.quantidade) as 'total', "
                    . "c.descricao AS 'categoria', "
                    . "YEAR(l.inclusao) AS 'ano',"
                    . "MONTH(l.inclusao) AS 'mes', "
                    . "c.tipo "
                    . "FROM lancamento l "
                    . "INNER JOIN lancamento_categoria lc "
                    . "ON l.id = lc.lancamentos_id "
                    . "INNER JOIN categoria c "
                    . "ON c.id = lc.categorias_id "
                    . "WHERE YEAR(l.inclusao) = $ano AND MONTH(l.inclusao) = $mes AND c.usuario = $id "
                    . "GROUP BY 2, 3, 4 "
                    . "ORDER BY 3, 4, 2 ";
            $result2 = mysql_query($sql, $link);
            if (!$result2) {
                throw new Exception('Invalid query: ' . mysql_error());
            } else {
                while ($row = mysql_fetch_assoc($result2)) {
                    $row['total_creditos'] = $total_creditos;
                    if($total_creditos) 
                        $row['percentual'] = $row['total']*100/$total_creditos;
                    else 
                        $row['percentual'] = 0;
                    $categorias_json[] = $row;
                }
            }
            mysql_close($link);
            //array_unshift($categorias_json, '');
            Header('Content-Type: application/json');
            die(json_encode($categorias_json));
        } catch (Exception $ex) {
            $_SESSION['mensagem'] = $ex->getMessage();
        }
    }    
    
    function processar_parcelas($servico, $lancamento, $categorias){
        if( $lancamento->parcelado ) {
            $parcelas = $servico->gerar_parcelas($lancamento);
            foreach($parcelas as $value){
                $servico->salvar($value);
                $servico->salvar_categorias($value, $categorias);
            }
            $servico->salvar($lancamento);
        } 
    }
}

?>
