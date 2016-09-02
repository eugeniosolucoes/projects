<?php
global $lib_path;
$lib_path = '../../';

require_once $lib_path . 'config/load.php';

$controle = new frequencia_controle();

$frequencia = $controle->execute();

?> 

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>

        <?php include $lib_path . 'inc/cabecalho.php'; ?>

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
            <div class="hero-unit">
                <form class="form-inline" method="post" >
                    <fieldset>
                        <caption>Frequência</caption>
                        <div class="control-group">
                            <input type="hidden" name="id" id="id" value="<?php echo $frequencia->get_id(); ?>" />
                            <input class="input-block-level" type="text" name="descricao" placeholder="Descrição" value="<?php echo $frequencia->get_descricao(); ?>" />
                        </div>
                        <div class="control-group">
                            <button type="submit" class="btn" name="comando" value="salvar">Salvar</button>
                            <button type="button" class="btn" onclick="javascript:window.location='<?php echo CONTEXT_PATH; ?>view/frequencia/list.php?comando=listar'" >Frequências</button>
                        </div>
                    </fieldset>
                </form>
            </div>
            <hr>
            <footer>
                <p><?php include $lib_path . 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include $lib_path . 'inc/footer_bootstrap.php'; ?>

        <script type="text/javascript">
            $(function() {
                $("#inclusao").datepicker({
                    buttonText: "data do lançamento",
                    showButtonPanel: true
                });
            });
        </script>  

    </body>
</html>
