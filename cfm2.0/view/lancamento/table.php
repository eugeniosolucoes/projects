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
        $obj->id = $lancamento['id'];
        $obj->tipo = $lancamento['tipo'] ? 'credito' : 'debito';
        $obj->frequencias = $lancamento['frequencia'];
        $obj->categorias = $lancamento['categorias'];
        $obj->link_descricao = CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento['id']}&mes={$lancamento['mes']}&ano={$lancamento['ano']}";
        $parcela_fmt = '';
        if($lancamento['parcelado'] && $lancamento['num_parcela']) {
            $parcela_fmt = sprintf("(%s/%s)", $lancamento['num_parcela'], $lancamento['qtd_parcelas']);
        }
        $obj->descricao = "{$lancamento['descricao']} {$parcela_fmt} ";
        $obj->link = urldecode( $lancamento['link'] );
        $obj->info_cat_title = "{$months[$lancamento['mes']]}{$lancamento['ano']}";
        $obj->info_cat_value = $lancamento['dia'];
        $obj->inclusao = $lancamento['inclusao'];
        $obj->class_valor = $lancamento['tipo'] ? 'lancamento_credito' : 'lancamento_debito';
        $obj->valor = sprintf("%.2f", $lancamento['valor'] * $lancamento['quantidade']);
        $lista_json[] = $obj;
    }
}
Header('Content-Type: application/json');
die(json_encode($lista_json));

?>

