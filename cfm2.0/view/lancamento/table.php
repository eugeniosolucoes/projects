<?php

global $lib_path;

$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

$lancamentos = $controle->execute();

$months = array('01' => 'Jan', '02' => 'Fev', '03' => 'Mar', '04' => 'Abr', '05' => 'Mai', '06' => 'Jun', '07' => 'Jul', '08' => 'Ago', '09' => 'Set', '10' => 'Out', '11' => 'Nov', '12' => 'Dez');

class LancamentoDTO {

    var $id;
    var $tipo;
    var $link_descricao;
    var $descricao;
    var $link;
    var $frequencias;
    var $categorias;
    var $info_cat_title;
    var $info_cat_value;
    var $inclusao;
    var $valor;
    var $class_valor;

}

$lista_json = array();

if (is_array($lancamentos) && count($lancamentos)) {
    foreach ($lancamentos as $lancamento) {
        $obj = new LancamentoDTO();
        $obj->id = $lancamento->get_id();
        $obj->tipo = $lancamento->get_tipo() ? 'credito' : 'debito';
        $obj->frequencias = $controle->get_frequencia($lancamento)->get_descricao();
        $obj->categorias = $controle->get_categorias_descricao_por_lancamento($lancamento);
        $obj->link_descricao = CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento->get_id()}&mes={$lancamento->get_mes()}&ano={$lancamento->get_ano()}";
        $obj->descricao = "{$lancamento->get_descricao()} {$lancamento->get_parcela_fmt()} ";
        $obj->link = $lancamento->get_link();
        $obj->info_cat_title = "{$months[$lancamento->get_mes()]}{$lancamento->get_ano()}";
        lancamento_dao::format_date_to_view($lancamento);
        $obj->info_cat_value = substr($lancamento->get_inclusao(), 0, 2);
        lancamento_dao::format_date_to_bd($lancamento);
        $obj->inclusao = $lancamento->get_inclusao();
        $obj->class_valor = $lancamento->get_tipo() ? 'lancamento_credito' : 'lancamento_debito';
        $obj->valor = sprintf("%.2f", $lancamento->get_valor() * $lancamento->get_quantidade());
        $lista_json[] = $obj;
    }
}
Header('Content-Type: application/json');
die(json_encode($lista_json));

?>

