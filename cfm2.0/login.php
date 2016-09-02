<?php 

global  $lib_path;

$lib_path = '';

require_once 'config/load.php'; 

?>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <?php 
            include 'inc/cabecalho.php';
            
            executar_login();
        ?>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <?php include 'inc/menu-login.php'; ?>
        
        <div class="container">

            <?php exibir_mensagens(); ?>
           
            
            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1>CFM</h1>
                <p>Bem-vindo ao CFM (Controle Financeiro Mensal) um aplicativo WEB desenvolvido com objetivo de auxiliar o controle de seu orçamento doméstico através do lançamento de seus débitos e créditos.</p>
                <p><a class="btn btn-primary btn-large">Saiba mais &raquo;</a></p>
            </div>

            <hr>

            <footer>
                <p><?php include 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include 'inc/footer_bootstrap.php'; ?>

    </body>
</html>
