<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of core_servico
 *
 * @author eugenio
 */
class core_servico {
    
    function fk_params($params, $prefix = 'id_'){
        $new_params = array();
        foreach ($params as $key => $value){
            $key_parts = explode('_', $key);
            $new_params[$prefix .''. $key_parts[count($key_parts)-1]] = $value;
        }
        return $new_params;
    }
}

?>
