<?php
global $lib_path;
$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

$lancamentos = $controle->execute();
?>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>

        <?php include $lib_path . 'inc/cabecalho.php'; ?>

        <link rel="stylesheet" href="<?php echo CONTEXT_PATH; ?>js/datatable/css/demo_page.css" >
        <link rel="stylesheet" href="<?php echo CONTEXT_PATH; ?>js/datatable/css/jquery.dataTables.css" >
        <!--<script type="text/javascript" language="javascript" src="<?php echo CONTEXT_PATH; ?>js/datatable/js/jquery.js"></script>-->
        <script type="text/javascript" language="javascript" src="<?php echo CONTEXT_PATH; ?>js/datatable/js/jquery.dataTables.min.js"></script>

        <link rel="stylesheet" href="<?php echo CONTEXT_PATH; ?>css/main.css">
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <?php include $lib_path . 'inc/menu-main.php'; ?>

        <div class="container">

            <?php exibir_mensagens(); ?>

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit" style="padding: 10px;">
                <h3>Pesquisar Lançamentos</h3>
                <div class="control-group">
                    <label class="control-label" style="width: 20px;">Início: <input id="data_inicio" type="text"></label><label class="control-label" style="width: 20px;">Fim:<input id="data_fim" type="text"></label>
                </div>
                <?php
                if (empty($_REQUEST['criterio'])) {
                    ?>
                    <div id="tbl_lancamentos_filter" class="dataTables_filter">
                        <label>Critérios: <input id='criterio_lancamento' aria-controls="tbl_lancamentos" type="text"></label>
                            </div>
                            <div class="clearfix"></div>
                        <?php } else { ?>
                            <div>
                                <table id="tbl_lancamentos">
                                    <thead>
                                        <tr>
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
                                            <tr>
                                                <td style="text-align: left; width: 1px;"><?php printf("%s", $lancamento->get_tipo() ? 'credito' : 'debito'); ?></td>
                                                <td><?php $controle->get_categorias_descricao_por_lancamento($lancamento); ?></td>
                                                <td><?php echo $controle->get_frequencia($lancamento)->get_descricao(); ?></td>
                                                <td><a class="link_descricao"  href="<?php echo CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento->get_id()}&mes={$lancamento->get_mes()}&ano={$lancamento->get_ano()}"; ?>"><?php echo $lancamento->get_descricao(); ?></a>
                        <?php  if($lancamento->get_link()) { ?>
                            <a href="<?php echo $lancamento->get_link(); ?>" target="_blank"><img src="../../img/external-link2.png" /></a>
                        <?php  } ?>
</td>
                                                <td style="text-align: right; width: 80px; white-space: nowrap"><?php
                                    lancamento_dao::format_date_to_view($lancamento);
                                    echo $lancamento->get_inclusao();
                                            ?></td>
                                                <td style="text-align: right; width: 80px; white-space: nowrap"><?php
                                            lancamento_dao::format_date_to_bd($lancamento);
                                            echo $lancamento->get_inclusao();
                                            ?></td>
                                                <td style="text-align: right;"><span class="<?php echo $lancamento->get_tipo() ? 'lancamento_credito' : 'lancamento_debito'; ?>"><?php printf("%.2f", $lancamento->get_valor() * $lancamento->get_quantidade()); ?></span></td>
                                            </tr>
                                            <?php
                                        }
                                        ?>
                                    </tbody>
                                </table>
                            </div>
                        <?php } ?>
                </div>
                <footer>
                    <p><?php include $lib_path . 'inc/rodape.php'; ?></p>
                </footer>

            </div> <!-- /container -->

            <?php include $lib_path . 'inc/footer_bootstrap.php'; ?>

            <script>
                $(document).ready(function() {
                    $('#tbl_lancamentos').dataTable(
                    {
                        "bPaginate": true,
                        "bStateSave": true,
                        "aoColumnDefs": [
                            { 'bSortable': false, 'bVisible': false, 'aTargets': [ 0 ] },
                            { 'bSortable': false, 'bVisible': false, 'aTargets': [ 1 ] },
                            { 'bSortable': false, 'bVisible': false, 'aTargets': [ 2 ] },
                            { 'iDataSort': 5, 'aTargets': [ 4 ]},
                            { 'bVisible': false, 'aTargets': [ 5 ]},
                            { 'bSortable': false, 'aTargets': [ 6 ] }
                        ],
                        "iDisplayLength": 10,
                        "aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "TODOS"]],
                        "oLanguage": {
                            "sInfo": "Resultado _START_ a _END_ de _TOTAL_ ",
                            "sSearch": "Critérios:",
                            "sLengthMenu": 'Registros: _MENU_ ',
                            "sInfoFiltered": "(filtro de _MAX_ total registros)",
                            "sInfoEmpty": "",
                            "sZeroRecords": "Nenhum resultado para pesquisa",
                            "oPaginate": {
                                "sPrevious": "Anterior",
                                "sNext": "Próxima"
                            }
                        }
                    } );

                    $('#tbl_lancamentos_filter label').
                        append('<button type="button" id="btn_pesquisar" class="btn">Pesquisar</button>');

                    $('#tbl_lancamentos_filter label').
                        append('<button type="button" id="btn_limpar" class="btn">Limpar</button>');

                    $('#btn_pesquisar').click(function() {
                        post_to_url('search.php', {criterio: $('input[aria-controls="tbl_lancamentos"]').val(), comando: 'pesquisar', inicio: $('#data_inicio').val(), fim: $('#data_fim').val()}, 'get');
                    }); 
                    
                    $('#criterio_lancamento').val('<?php echo @$_REQUEST['criterio']; ?>');
                
                    $('#btn_limpar').click(function() {
                        $('input:text').val('');
                        $('#tbl_lancamentos').dataTable().fnFilter('');
                    });
		    $('#criterio_lancamento').bind("enterKey",function(e){
   			$('#btn_pesquisar').click();
		    });
 		    $('#criterio_lancamento').keyup(function(e){
		    	if(e.keyCode == 13){
				$(this).trigger("enterKey");
		    	}
		    });  
                    
                    $('#data_inicio').datepicker({
                        buttonText: "data do início",
                        showButtonPanel: true,
					changeMonth: true,
					changeYear: true
                    });
                    
                    $('#data_fim').datepicker({
                        buttonText: "data do fim",
                        showButtonPanel: true,
					changeMonth: true,
					changeYear: true
                    });
                });
            </script>        
    </body>
</html>

