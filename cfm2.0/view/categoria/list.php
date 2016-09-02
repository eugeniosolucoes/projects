<?php
global $lib_path;
$lib_path = '../../';

require_once '../../config/load.php';

$controle = new categoria_controle();

$categorias = $controle->execute();

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
            <div class="hero-unit" style="padding: 10px;">
                <h3>Categorias</h3>
                <div class="btn-group">
                    <button class="btn dropdown-toggle" data-toggle="dropdown">Ações <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="<?php echo CONTEXT_PATH; ?>view/categoria/form.php?comando=novo">Novo</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Excluir selecionados</a></li>
                    </ul>
                </div>                
                <div>
                    <?php
                    if (is_array($categorias) && count($categorias)) {
                        ?>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th style="text-align: left; width: 50px;">Tipo</th>
                                    <th>Descrição</th>
                                    <th style="text-align: center; width: 100px;">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                foreach ($categorias as $categoria) {
                                    ?>
                                    <tr>
                                        <td>
                                            <?php echo $categoria->get_tipo() ? 'Crédito' : 'Débito'; ?>
                                        </td>
                                        <td>
                                            <?php echo $categoria->get_descricao(); ?>
                                        </td>
                                        <td style="text-align: right;">
                                            <a class="btn" href="<?php echo CONTEXT_PATH; ?>view/categoria/form.php?comando=retornar&id=<?php echo $categoria->get_id(); ?>"><i class="icon-pencil"></i></a>
                                            <a class="btn" href="#" onclick="excluir({comando: 'excluir', id : <?php echo $categoria->get_id(); ?>})" ><i class="icon-trash"></i></a>
                                        </td>
                                    </tr>
                                    <?php
                                }
                                ?>
                            </tbody>
                        </table>
                        <?php
                    } else {
                        echo 'Não existem categorias cadastradas!';
                    }
                    ?>
                </div>
            </div>
            <footer>
                <p><?php include $lib_path . 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include $lib_path . 'inc/footer_bootstrap.php'; ?>

    </body>
</html>
