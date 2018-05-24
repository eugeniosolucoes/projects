<?php
global $lib_path;
$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

$controle->execute();
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

    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <?php include $lib_path . 'inc/menu-main.php'; ?>

        <div class="container-fluid">

            <?php exibir_mensagens(); ?>

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit" style="padding: 10px;">
<!--                <p><arequire_once class="btn btn-primary" href="<?php echo CONTEXT_PATH; ?>view/lancamento/form.php?comando=novo">Novo Lançamento</a></p>-->
                <h3>Lançamentos</h3>
                <button type="button" class="btn-block" id="btn-balanco" >Balanço</button>
                <div id="div-balanco" class="ui-widget-content ui-corner-all">
                    <?php require_once './balanco.php'; ?>
                </div>
                <form id="menu-opcoes" class="form-inline" action="<?php echo CONTEXT_PATH; ?>view/lancamento/list.php" method="get" >
                    <select class='span2' name="mes" id="mes">
                        <?php
                        $meses = $controle->get_meses();
                        foreach ($meses as $key => $mes) {
                            printf("<option value='$key' %s>$mes</option>", @$_REQUEST['mes'] ? $_REQUEST['mes'] == $key ? 'selected' : '' : date('m') == $key ? 'selected' : '');
                        }
                        ?>
                    </select>
                    <select class='span2' name="ano" id="ano">
                        <?php
                        $anos = $controle->get_anos();
                        foreach ($anos as $ano) {
                            printf("<option value='$ano' %s>$ano</option>", @$_REQUEST['ano'] ? $_REQUEST['ano'] == $ano ? 'selected' : '' : date('Y') == $ano ? 'selected' : '');
                        }
                        ?>
                    </select>
                    <input type="hidden" name="comando" value="listar" />
                    <button type="submit" class="btn-block" >Selecionar período</button>
                    <button type="button" class="btn" id="btn-novo" >Novo</button>
                    <button type="button" class="btn" id="btn-excluir" disabled="disabled" >Excluir</button> 
                    <button type="button" class="btn" id="btn-copiar" disabled="disabled" 
                            title="Copiar lançamento(s) para o período selecionado" >Copiar</button>
                </form>
                <button type="button" class="btn-block" id="btn-exibir" >Exibir Lançamentos</button>
                <div class="form-inline" id="div-lancamento" >

                </div>
            </div>
            <footer>
                <p><?php include $lib_path . 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include $lib_path . 'inc/footer_bootstrap.php'; ?>

        <script>

            $(document).ready(function () {
                
                $('#div-balanco').toggle('up');

		var getUrlParameter = function getUrlParameter(sParam) {
		    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
			sURLVariables = sPageURL.split('&'),
			sParameterName,
			i;

		    for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
			    return sParameterName[1] === undefined ? true : sParameterName[1];
			}
		    }
		};

		if(getUrlParameter('acao')=='true') {
			show_lancamentos();
		}

                function show_lancamentos() {
                    $('#div-lancamento').empty();
                    $('#div-lancamento').html("<img src='<?php echo CONTEXT_PATH . "img/loading.gif' />Carregando Lançamentos..."; ?>");
                    $.ajax({
                        url: '<?php echo CONTEXT_PATH . "view/lancamento/table.php?comando=listar_tabela&mes=" . @$_REQUEST['mes'] . "&ano=" . @$_REQUEST['ano']; ?>',
                        async: true,
                        dataType: 'html',
                        success: function (data) {
                            $('#div-lancamento').html(data);
                            configurar_datatable();
                            carregar_categorias();
                            $('#tbl_lancamentos_filter label').
                                    append('<button type="button" id="btn_limpar" class="btn">Limpar</button>');

                            $('#btn_limpar').click(function () {
                                $('input:text').val('');
                                $('#tbl_lancamentos').dataTable().fnFilter('');
				$( "#lista_categorias" ).val('');
                            });

                            $('#lista_categorias').change(function () {
                                $('input:text').val($('#lista_categorias').val());
                                $('#tbl_lancamentos').dataTable().fnFilter($('#lista_categorias').val());
                            });
                            $("#btn-topo").click(function () {
                                $("html, body").animate({scrollTop: 0}, "slow");
                            });
                        }
                    });
                }

                function carregar_categorias() {
                    $.ajax({
                        url: '<?php echo CONTEXT_PATH; ?>view/lancamento/list.php?comando=get_categorias_json',
                        async: true,
                        dataType: 'json',
                        success: function (data) {
                            var sel = $("#lista_categorias");
                            sel.empty();
                            for (var i = 0; i < data.length; i++) {
                                sel.append('<option value="' + data[i] + '">' + data[i] + '</option>');
                            }
                        }
                    });
                }

                function configurar_datatable() {
                    $('#tbl_lancamentos').dataTable(
                            {
                                responsive: true,
                                "ajax": "table",
                                "autoWidth": false,
                                "bPaginate": false,
                                "bStateSave": true,
                                "aoColumnDefs": [
                                    {'bSortable': false, 'aTargets': [0]},
                                    {'bSortable': false, 'bVisible': false, 'aTargets': [1]},
                                    {'bSortable': false, 'bVisible': false, 'aTargets': [2]},
                                    {'bSortable': false, 'bVisible': false, 'aTargets': [3]},
                                    {'iDataSort': 6, 'aTargets': [5]},
                                    {'bVisible': false, 'aTargets': [6]},
                                    {'bSortable': false, 'aTargets': [7]}
                                ],
                                "iDisplayLength": 10,
                                "aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "todos"]],
                                "oLanguage": {
                                    "sInfo": "Resultado _START_ a _END_ de _TOTAL_ ",
                                    "sSearch": "Buscar:",
                                    "sLengthMenu": 'Registros: _MENU_ ',
                                    "sInfoFiltered": "(filtro de _MAX_ total registros)",
                                    "sInfoEmpty": "Nenhum resultado",
                                    "oPaginate": {
                                        "sPrevious": "Anterior",
                                        "sNext": "Próxima"
                                    }
                                },
                                "fnDrawCallback": function () {
                                    calcular();
                                }
                            });
                }

                $('#btn-exibir').click(function () {
                    show_lancamentos();
                });

                $('#btn-copiar').click(function () {
                    copiar();
                });

                $('#btn-excluir').click(function () {
                    excluir_lancamentos();
                });

                $('#btn-balanco').click(function () {
                    $('#div-balanco').toggle('blind');
                });

                $('#btn-novo').click(function () {
                    window.location.href = '<?php echo CONTEXT_PATH; ?>view/lancamento/form.php?comando=novo';
                });

            });
        </script>        

    </body>
</html>
