<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of base_controle
 *
 * @author eugenio
 */
class core_controle {

    function get_obj($obj) {
        $api = new ReflectionClass($obj);
        try {
            foreach ($api->getProperties() as $prop) {
                if (isset($_REQUEST[$prop->getName()])) {
                    $prop->setValue($obj, $_REQUEST[$prop->getName()]);
                }
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());
        }
        return $obj;
    }
    
    function execute() {
        switch (@$_REQUEST['comando']) {
            case 'novo':
                return $this->novo();
                break;
            case 'listar':
                return $this->listar();
                break;
            case 'retornar':
                return $this->retornar();
                break;
            case 'salvar':
                return $this->salvar();
                break;
            case 'excluir':
                $this->excluir();
                return $this->listar();
                break;
            default:
                break;
        }
    }

    

}

?>
