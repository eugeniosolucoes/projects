<?php

global $lib_path;
$lib_path = '../../';

require_once '../../config/load.php';

// O remetente deve ser um e-mail do seu domínio conforme determina a RFC 822.
// O return-path deve ser ser o mesmo e-mail do remetente.
$headers = "MIME-Version: 1.1\r\n";
$headers .= "Content-type: text/html; charset=iso-utf-8\r\n";
//$headers .= "Content-type: text/plain; charset=iso-8859-1\r\n";
$headers .= "From: webmaster@eugeniosolucoes.com.br\r\n"; // remetente
$headers .= "Bcc: webmaster@eugeniosolucoes.com.br, ageniws@gmail.com\r\n";
$headers .= "Return-Path: webmaster@eugeniosolucoes.com.br\r\n"; // return-path

$servico_lancamento = new lancamento_servico();
$servico_usuario = new usuario_servico();

$usuarios = $servico_usuario->listar();

foreach ($usuarios as $usuario) {

    $lancamentos = $servico_lancamento->listar_por_dia($usuario);

    $texto = 'CFM 2.0 - Lançamentos do dia:<br/>';

    $texto .= '<br/>';

    if (is_array($lancamentos) && count($lancamentos) > 0) {

        foreach ($lancamentos as $lancamento) {
            $texto .= "<a href='http://{$_SERVER["HTTP_HOST"]}" . CONTEXT_PATH . "view/lancamento/form.php?comando=retornar&id={$lancamento->get_id()}'>{$lancamento->get_descricao()}</a><br/>";
        }
        $envio = mail($usuario->get_email(), "CFM 2.0 - Lançamentos do dia", $texto, $headers);

        if ($envio)
            echo "Mensagem enviada com sucesso {$usuario->get_email()}<br>";
        else
            echo "A mensagem não pode ser enviada";
    }
}
?>
