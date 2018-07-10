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
class lancamento_servico extends core_servico {

    function listar($params) {
        try {
            $lancamento = new lancamento();
            $dao = new lancamento_dao();
            $list = $dao->retornar_todos_por_ordenado($lancamento, $params, ' inclusao ASC ');
            return $list;
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function listar_por_dia($usuario) {
        $hoje = date("Y-m-d");
        $hoje_parts = explode('-', $hoje);
        try {
            $servico = new lancamento_servico();
            $dia = $hoje_parts[2];
            $mes = $hoje_parts[1];
            $ano = $hoje_parts[0];
            return $servico->listar(array('usuario' => $usuario->get_id(),
                        'DAY(inclusao)' => $dia,
                        'MONTH(inclusao)' => $mes,
                        'YEAR(inclusao)' => $ano));
        } catch (Exception $ex) {
            throw new Exception($ex->getMessage());
        }
    }    
    
    function pesquisar($usuario, $params) {
        try {
            $lancamento = new lancamento();
            $dao = new lancamento_dao();
            $list = $dao->pesquisar($lancamento, $usuario, $params, ' inclusao ASC ');
            return $list;
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function salvar($lancamento) {
        try {
            $dao = new lancamento_dao();
            if (is_null($lancamento->get_id())) {
                $dao->incluir($lancamento);
            } else {
                $dao->atualizar($lancamento);
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function verificar_saldo_lancamento($lancamento) {
        try {
            $usuario = unserialize($_SESSION['usuario']);
            $saldo = new saldo();
            $tmp = explode('/', $lancamento->inclusao);
            $date = new DateTime("$tmp[2]-$tmp[1]-$tmp[0]");
            $saldo->set_ano(date_format($date, 'Y'));
            $saldo->set_mes(date_format($date, 'm'));
            $saldo->set_usuario($usuario->get_id());
            $servico = new saldo_servico();
            try {
                $servico->salvar($saldo);
            } catch (Exception $exc) {
                //saldo já existe
            }
            $s = $servico->listar(array('ano' => $saldo->get_ano(),
                'mes' => $saldo->get_mes(), 'usuario' => $usuario->get_id()));
            $lancamento->set_saldo($s[0]->get_id());
        } catch (Exception $exc) {
            throw new Exception($exc->getMessage());
        }
    }

    function retornar($lancamento) {
        try {
            $dao = new lancamento_dao();
            return $dao->retornar($lancamento);
        } catch (Exception $exc) {
            throw new Exception($exc->getMessage());
        }
    }

    function excluir($lancamento) {
        try {
            $dao = new lancamento_dao();
            $dao->excluir($lancamento);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_categorias($lancamento) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_categorias($lancamento->get_id());
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_categorias_descricao_por_lancamento($lancamento) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_categorias_descricao_por_lancamento($lancamento->get_id());
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_anos($usuario) {
        try {
            $dao = new lancamento_dao();
            $colecao = $dao->get_anos($usuario);
            if(is_array($colecao) && count($colecao) > 0){
                $last = $colecao[count($colecao)-1];
                $last++;
                $colecao[] = $last;
            }
            return $colecao;
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function salvar_categorias($lancamento, $categorias) {
        try {
            $dao = new lancamento_dao();
            return $dao->salvar_categorias($lancamento->get_id(), $categorias);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_creditos($mes, $ano, $usuario) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_creditos($mes, $ano, $usuario);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_debitos($mes, $ano, $usuario) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_debitos($mes, $ano, $usuario);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_creditos_nao_realizados($mes, $ano, $usuario) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_creditos_nao_realizados($mes, $ano, $usuario);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function get_debitos_nao_realizados($mes, $ano, $usuario) {
        try {
            $dao = new lancamento_dao();
            return $dao->get_debitos_nao_realizados($mes, $ano, $usuario);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    function copiar($lancamento, $mes_novo, $ano_novo) {
        try {
            $dao = new lancamento_dao();
            $dao->copiar($lancamento, $mes_novo, $ano_novo);

//            $lancamento = $this->retornar($lancamento);
//            $lancamento->tipo = $lancamento->tipo ? '1' : '0';
//            $lancamento_id = $lancamento->get_id();
//            $lancamento->set_id(NULL);
//            $data = explode('/', $lancamento->inclusao);
//            $mes_atual = $data[1];
//            $numberOfMonths = $mes_novo - $mes_atual;
//            $date = new DateTime("$data[2]-$data[1]-$data[0]");
//            $add = $numberOfMonths >= 0 ? '+' : '';
//            $date->modify( $add. $numberOfMonths.' month');
//            $lancamento->inclusao = $date->format('d/m/Y');
//            $this->salvar($lancamento);
//            $copiado = new lancamento();
//            $copiado->set_id($lancamento_id);
//            $categorias = $this->get_categorias($copiado);
//            $this->salvar_categorias($lancamento, $categorias);
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
    }

    
    function gerar_parcelas($obj) {
        $lista = array();
        if ($obj->parcelado) {
            $qtd = $obj->qtd_parcelas;
            $tmp = explode('/', $obj->inclusao);
            //$lista[] = $obj;
            for ($i = 1; $i < $qtd; $i++) {
                $strInterval = 'P' . $i . 'M';
                $parcela = clone $obj;
                $parcela->id = NULL;
                $date = new DateTime("$tmp[2]-$tmp[1]-$tmp[0]");
                $date->add(new DateInterval($strInterval));
                $parcela->inclusao = $date->format('d/m/Y');
                $parcela->valor /= $qtd;
                $parcela->num_parcela = $i + 1;
                $parcela->lancamento_id = $obj->id;
                $lista[] = $parcela;
            }
            $obj->valor /= $qtd;
        }
        return $lista;
    }

}

?>
