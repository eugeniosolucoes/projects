<?php
global $lib_path;
$lib_path = '../../';

require_once '../../config/load.php';

$controle = new lancamento_controle();

?>
<ul class="group-balanco">
    <li>Previsão</li>
    <li>Créditos:  <span class="lancamento_credito"><?php printf("%.2f", $controle->get_creditos()); ?></span></li>
    <li>Débitos:  <span class="lancamento_debito"><?php printf("%.2f", $controle->get_debitos()); ?></span></li>
    <li>Saldo:  <span class="<?php printf("%s", $controle->get_creditos() - $controle->get_debitos() > 0 ? 'lancamento_credito' : 'lancamento_debito' ) ?>">
<?php printf("%.2f", abs($controle->get_creditos() - $controle->get_debitos())); ?></span></li>
</ul>
<ul class="group-balanco">
    <li>Realizado</li>
    <li>Créditos:  <span class="lancamento_credito"><?php printf("%.2f", $controle->get_creditos_nao_realizados()); ?></span></li>
    <li>Débitos:  <span class="lancamento_debito"><?php printf("%.2f", $controle->get_debitos_nao_realizados()); ?></span></li>
    <li>Saldo:  <span class="<?php printf("%s", $controle->get_creditos() - $controle->get_debitos_nao_realizados() > 0 ? 'lancamento_credito' : 'lancamento_debito' ) ?>">
<?php printf("%.2f", abs($controle->get_creditos_nao_realizados() - $controle->get_debitos_nao_realizados())); ?></span></li>
</ul>
