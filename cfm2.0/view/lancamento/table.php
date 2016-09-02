<?php

global $lib_path;

$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

$lancamentos = $controle->execute();

if (is_array($lancamentos) && count($lancamentos)) {
    ?>
    <label for="lista_categorias" >Categorias: </label>
    <select class='span10' id="lista_categorias"></select>
    <table id="tbl_lancamentos">
        <thead>
            <tr>
                <th style="text-align: left; width: 1px;"><input type="checkbox" onclick="chk_lancamentos(this);
                        chk_excluir();" id="chk_all"  /></th>
                <th style="text-align: left; width: 1px;">Tipo</th>
                <th>Categorias</th>
                <th>Frequência</th>
                <th>Descrição</th>
                <th style="text-align: right; width: 80px;">Data</th>
                <th style="text-align: right; width: 80px;">Data Sort</th>
                <th style="text-align: right; width: 100px;">Valor Total</th>
            </tr>
        </thead>
        <tbody>
            <?php
            foreach ($lancamentos as $lancamento) {
                ?>
                <tr class="item">
                    <td style="text-align: left; width: 1px;">
                        <input type="checkbox" class="chk_item" name="chk_item" onclick="chk_excluir();" value="<?php echo $lancamento->get_id(); ?>" />
                    </td>
                    <td style="text-align: left; width: 1px;"><?php printf("%s", $lancamento->get_tipo() ? 'credito' : 'debito'); ?></td>
                    <td><?php $controle->get_categorias_descricao_por_lancamento($lancamento); ?></td>
                    <td><?php echo $controle->get_frequencia($lancamento)->get_descricao(); ?></td>
                    <td><a class="link_descricao" href="<?php echo CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento->get_id()}"; ?>"><?php echo $lancamento->get_descricao(); ?></a></td>
                    <td style="text-align: right; width: 1px; white-space: nowrap"><?php
                        lancamento_dao::format_date_to_view($lancamento);
                        echo $lancamento->get_inclusao();
                        ?></td>
                    <td style="text-align: right; width: 1px; white-space: nowrap"><?php
                        lancamento_dao::format_date_to_bd($lancamento);
                        echo $lancamento->get_inclusao();
                        ?></td>
                    <td style="text-align: right; width: 1px;"><span class="<?php echo $lancamento->get_tipo() ? 'lancamento_credito' : 'lancamento_debito'; ?>"><?php printf("%.2f", $lancamento->get_valor() * $lancamento->get_quantidade()); ?></span></td>
                </tr>
                <?php
            }
            ?>
        </tbody>
        <tfoot>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td style="text-align: center;"><button type="button" class="btn" id="btn-topo" >&uArr;</button></td>
                <td></td>
                <td></td>
                <td style="text-align: right; width: 1px; white-space: nowrap"><span id="total"></span></td>
            </tr>
        </tfoot>
    </table>
    <?php
} else {
    echo 'Não existem lançamentos cadastrados!';
}
?>