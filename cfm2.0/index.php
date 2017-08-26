<?php
global $lib_path;

$lib_path = '';

require_once 'config/load.php';

$controle = new usuario_controle();

$controle->execute();
?>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <?php
        include 'inc/cabecalho.php';
        ?>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: right;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
            .row-periodo {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <?php include 'inc/menu-main.php'; ?>

        <div class="container">

            <div class="mensagem clearfix"><?php printf("Bem-vindo, %s", $controle->exibir_usuario()); ?></div>
            <?php exibir_mensagens(); ?>

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1>CFM</h1>
                <p>Bem-vindo ao CFM (Controle Financeiro Mensal) um aplicativo WEB desenvolvido com objetivo de auxiliar o controle de seu orçamento doméstico através do lançamento de seus débitos e créditos.</p>
                <p>
                    <a id="btn-periodos" class="btn btn-primary btn-large" href="#">Períodos &raquo;</a>
                    <a class="btn btn-primary btn-large" href="<?php echo CONTEXT_PATH; ?>view/lancamento/form.php?comando=novo">Novo lançamento &raquo;</a>
                    <a class="btn btn-primary btn-large" href="<?php echo CONTEXT_PATH; ?>view/lancamento/list.php?comando=listar">Lançamentos do Mês &raquo;</a>
                    <a class="btn btn-primary btn-large" href="<?php echo CONTEXT_PATH; ?>view/lancamento/list.php?mes=<?php echo date('m', strtotime("+1 months", strtotime(date('Y-m-d')))); ?>&ano=<?php echo date('Y', strtotime("+1 months", strtotime(date('Y-m-d')))); ?>&comando=listar">Lançamentos pr&oacute;ximo Mês &raquo;</a>
                </p>
                <div id="div-periodos">
                    <select id="list-anos" class="btn-block">

                    </select>
                    <table id="tbl-periodo">
                        <row-periodothead>
                            <tr>
                                <th>Mês</th>
                                <th>Créditos</th>
                                <th>Débitos</th>
                                <th>Balanço</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>

            <hr>

            <footer>
                <p><?php include 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include 'inc/footer_bootstrap.php'; ?>
        <script type="text/javascript" >
            $(document).ready(function () {
                $('#div-periodos').hide();
                processar_anos();
                $('#list-anos').change(function () {
                    var ano = $("#list-anos option:selected").val();
                    processar_periodo(ano);
                });
                $('#btn-periodos').click(function () {
                    processar_periodo();
                });

            });
        </script>
    </body>
</html>
