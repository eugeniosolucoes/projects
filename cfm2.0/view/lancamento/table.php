<?php

global $lib_path;

$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

$lancamentos = $controle->execute();

if (is_array($lancamentos) && count($lancamentos)) {
    ?>
    <label for="lista_categorias" >Categorias: </label>
    <input type="text" id="lista_categorias"  />
    <table id="tbl_lancamentos" style="width: 100%;">
        <thead>
            <tr>
                <th style="text-align: left; width: 1%;"><input type="checkbox" onclick="chk_lancamentos(this);
                        chk_excluir();" id="chk_all"  /></th>
                <th style="text-align: left;">Tipo</th>
                <th>Categorias</th>
                <th>Frequência</th>
                <th style="width: 90%">Descrição</th>
                <th style="text-align: right; width: 1%">Dia</th>
                <th style="text-align: right;">Data Sort</th>
                <th style="text-align: right; width: 1%;">Total</th>
            </tr>
        </thead>
        <tbody>
            <?php
            foreach ($lancamentos as $lancamento) {
                $list_cat = $controle->get_categorias_descricao_por_lancamento($lancamento); 
                ?>
                <tr class="item">
                    <td style="text-align: left;">
                        <input type="checkbox" class="chk_item" name="chk_item" onclick="chk_excluir();" value="<?php echo $lancamento->get_id(); ?>" />
                    </td>
                    <td style="text-align: left; width: 1px;"><?php printf("%s", $lancamento->get_tipo() ? 'credito' : 'debito'); ?></td>
                    <td><?php echo $controle->get_categorias_descricao_por_lancamento($lancamento); ?></td>
                    <td><?php echo $controle->get_frequencia($lancamento)->get_descricao(); ?></td>
                    <td>
                        <a class="link_descricao" href="<?php echo CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento->get_id()}&mes={$lancamento->get_mes()}&ano={$lancamento->get_ano()}"; ?>"><?php echo $lancamento->get_descricao(); ?></a>
                        <?php if($list_cat) { ?>
                        <span class="ui-icon ui-icon-info info_cat" style="float: right; vertical-align: middle; cursor: pointer;" title="<?php echo $list_cat; ?>" ></span>
                        <?php } ?>
                        <?php  if($lancamento->get_link()) { ?>
                            <a href="<?php echo $lancamento->get_link(); ?>" target="_blank"><img src="../../img/external-link2.png" /></a>
                        <?php  } ?>
                    </td>
                    <td style="text-align: right; white-space: nowrap"><?php
                        lancamento_dao::format_date_to_view($lancamento);
                        echo substr($lancamento->get_inclusao(), 0, 2);
                        ?></td>
                    <td style="text-align: right; white-space: nowrap"><?php
                        lancamento_dao::format_date_to_bd($lancamento);
                        echo $lancamento->get_inclusao();
                        ?></td>
                    <td style="text-align: right;"><span class="<?php echo $lancamento->get_tipo() ? 'lancamento_credito' : 'lancamento_debito'; ?>"><?php printf("%.2f", $lancamento->get_valor() * $lancamento->get_quantidade()); ?></span></td>
                </tr>
                <?php
            }
            ?>
        </tbody>
        <tfoot>
            <tr>
<!--                <td></td>
                <td></td>
                <td></td>
                <td></td>-->
                <td colspan="7" style="text-align: center;"><button type="button" class="btn btn-top" >&uArr;</button></td>
<!--                <td></td>
                <td></td>-->
                <td style="text-align: right; white-space: nowrap"><span id="total"></span></td>
            </tr>
        </tfoot>
    </table>
    <?php
} else {
    echo 'Não existem lançamentos cadastrados!';
}
?>