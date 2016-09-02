<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">CFM</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="<?php echo CONTEXT_PATH; ?>">Início</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lancamentos<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<?php echo CONTEXT_PATH; ?>view/lancamento/form.php?comando=novo">Novo</a></li>
							<li><a href="<?php echo CONTEXT_PATH; ?>view/lancamento/list.php?comando=listar">Por período</a></li>
                            <li><a href="<?php echo CONTEXT_PATH; ?>view/lancamento/search.php?comando=pesquisar">Pesquisar</a></li>
                        </ul>
                    </li>                    
                    <li><a href="<?php echo CONTEXT_PATH; ?>view/categoria/list.php?comando=listar">Categorias</a></li>
                    <li><a href="<?php echo CONTEXT_PATH; ?>view/frequencia/list.php?comando=listar">Frequências</a></li>
                    <li><a href="<?php echo CONTEXT_PATH; ?>index.php?logout=true">Sair</a></li>
                    <!--
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Sobre<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="list_saldo.php">Saldos</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="index.php?logout=true">Sair</a></li>
                        </ul>
                    </li>-->
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
