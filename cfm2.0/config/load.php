<?php

global $lib_path;

session_start();

$_SESSION['mensagem'] = NULL;

define("CONTEXT_PATH", "/cfm2.0/");

require_once $lib_path.'lib/modelo/usuario.class.php';
require_once $lib_path.'lib/modelo/saldo.class.php';
require_once $lib_path.'lib/modelo/lancamento.class.php';
require_once $lib_path.'lib/modelo/frequencia.class.php';
require_once $lib_path.'lib/modelo/categoria.class.php';
require_once $lib_path.'lib/modelo/tipo_categoria.class.php';

require_once $lib_path.'lib/seguranca/seguranca.class.php';

require_once $lib_path.'lib/repositorio/core_dao.class.php';
require_once $lib_path.'lib/repositorio/usuario_dao.class.php';
require_once $lib_path.'lib/repositorio/saldo_dao.class.php';
require_once $lib_path.'lib/repositorio/frequencia_dao.class.php';
require_once $lib_path.'lib/repositorio/lancamento_dao.class.php';
require_once $lib_path.'lib/repositorio/categoria_dao.class.php';

require_once $lib_path.'lib/servico/core_servico.class.php';
require_once $lib_path.'lib/servico/usuario_servico.class.php';
require_once $lib_path.'lib/servico/saldo_servico.class.php';
require_once $lib_path.'lib/servico/lancamento_servico.class.php';
require_once $lib_path.'lib/servico/categoria_servico.class.php';
require_once $lib_path.'lib/servico/frequencia_servico.class.php';

require_once $lib_path.'lib/controle/core_controle.class.php';
require_once $lib_path.'lib/controle/usuario_controle.class.php';
require_once $lib_path.'lib/controle/saldo_controle.class.php';
require_once $lib_path.'lib/controle/lancamento_controle.class.php';
require_once $lib_path.'lib/controle/categoria_controle.class.php';
require_once $lib_path.'lib/controle/frequencia_controle.class.php';
require_once $lib_path.'lib/controle/dropdownlist.class.php';

require_once 'functions.php';

redirect_no_auth();

efetuar_logout();

?>
