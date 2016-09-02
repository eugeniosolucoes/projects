<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/


date_default_timezone_set('America/Sao_Paulo');

function redirect_no_auth() {
    if ($_SERVER['REQUEST_URI'] == CONTEXT_PATH . 'lib/servico/send.php') {
        return;
    }
    if (empty($_SESSION['usuario'])) {
        $url = $_SERVER['REQUEST_URI'];
        $return_url = strpos($url, 'view') !== false ? urlencode($url) : '';
        if (strpos($_SERVER['REQUEST_URI'], 'login.php') === false) {
            $_SESSION['return_url'] = $return_url;
            header("Location: http://{$_SERVER["HTTP_HOST"]}" . CONTEXT_PATH . "login.php");
            die();
        }
    }


//    if ($_SERVER['REQUEST_URI'] !== CONTEXT_PATH . 'login.php') {
//        if (!isset($_SESSION['usuario'])) {
//            header("Location: http://{$_SERVER["HTTP_HOST"]}" . CONTEXT_PATH . "login.php");
//            die();
//        }
//    }
}

function executar_login() {
    if (@$_SERVER['REQUEST_URI'] == CONTEXT_PATH . 'login.php') {
        $login = @$_REQUEST['login'];
        $senha = @$_REQUEST['senha'];
        $url = @$_REQUEST['return_url'];
        if (isset($login) && isset($senha)) {
            $usuario = new usuario();
            $usuario->set_login($login);
            $usuario->set_senha($senha);
            $servico = new usuario_servico();
            if ($servico->login($login, $senha)) {
                $_SESSION['mensagem'] = "Bem-vindo, $usuario->get_login()}!";
                if(!empty($url)) {
                    header("Location: " . urldecode($url) );
                } else {
                    header("Location: " . CONTEXT_PATH . "index.php");
                }
            } else {
                $_SESSION['mensagem'] = 'Login ou senha inválidos!';
            }
        }
    }
}

function efetuar_logout() {
    if (isset($_GET['logout'])) {
        $_SESSION['usuario'] = NULL;
        limpar_mensagens();
        redirect_no_auth();
    }
}

function limpar_mensagens() {
    $_SESSION['mensagem'] = NULL;
}

function exibir_mensagens() {
    printf("%s", @$_SESSION['mensagem'] ? sprintf("<div class='mensagem clearfix'>%s</div>", @$_SESSION['mensagem']) : '');
}

function strip_desc($str, $init = 10) {
    $pos = 0;
    $new_str = "";
    $size = strlen($str);
    $cont = (int) $size / $init;
    for ($i = 0; $i < $cont; $i++) {
        $new_str .= substr($str, $pos, $init) . "\n";
        $pos += $init;
    }
    return $new_str;
}

function dropdownlist(
        $colecao, $name = '', $multiple = '', $id = 'id', $texto = 'descricao', $selected = NULL, $class = 'span2', $input = array(), $class_selecteds = 'sel1', $class_not_selecteds = 'sel2'
) {

    printf("<select class='$class' name='$name' id='$name' $multiple >\n");
    foreach ($colecao as $obj) {
        $api = new ReflectionClass($obj);
        foreach ($api->getProperties() as $propertie) {
            if ($propertie->getName() == $id) {
                $result = in_array($propertie->getValue($obj), $input);
                printf('<option value="%s" %s  class="%s">', $propertie->getValue($obj), $propertie->getValue($obj) == $selected ? 'selected' : '', $result ? $class_selecteds : $class_not_selecteds
                );
            }
            if ($propertie->getName() == $texto) {
                printf("%s</option>\n", $propertie->getValue($obj));
            }
        }
    }
    printf("</select>\n");
}

?>
