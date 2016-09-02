<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of dropdownlist
 *
 * @author eugenio
 */
class dropdownlist {

    var $collection = array();
    var $name = '';
    var $multiple = '';
    var $id = '';
    var $value = 'id';
    var $text = 'descricao';
    var $selected = NULL;
    var $select_class = 'span2';
    var $selected_values = array();
    var $attributes = array();

    function render() {

        printf("<select 
            class='$this->select_class' 
            name='$this->name' 
            id='$this->id' 
            $this->multiple %s >\n", $this->get_attributes());
        foreach ($this->collection as $obj) {
            $api = new ReflectionClass($obj);
            foreach ($api->getProperties() as $propertie) {
                if ($propertie->getName() == $this->value) {
                    $result = false;
                    if ($this->selected_values)
                        $result = in_array($propertie->getValue($obj), $this->selected_values);
                    printf('<option value="%s" %s >', $propertie->getValue($obj), $result ? 'selected' : ''
                    );
                }
                if ($propertie->getName() == $this->text) {
                    printf("%s</option>\n", $propertie->getValue($obj));
                }
            }
        }
        printf("</select>\n");
    }

    public function get_collection() {
        return $this->collection;
    }

    public function set_collection($collection) {
        $this->collection = $collection;
    }

    public function get_name() {
        return $this->name;
    }

    public function set_name($name) {
        $this->name = $name;
    }

    public function get_multiple() {
        return $this->multiple;
    }

    public function set_multiple($multiple) {
        $this->multiple = $multiple;
    }

    public function get_value() {
        return $this->value;
    }

    public function set_value($value) {
        $this->value = $value;
    }

    public function get_text() {
        return $this->text;
    }

    public function set_text($text) {
        $this->text = $text;
    }

    public function get_selected() {
        return $this->selected;
    }

    public function set_selected($selected) {
        $this->selected = $selected;
    }

    public function get_select_class() {
        return $this->select_class;
    }

    public function set_select_class($select_class) {
        $this->select_class = $select_class;
    }

    public function get_selected_values() {
        return $this->selected_values;
    }

    public function set_selected_values($selected_values) {
        $this->selected_values = $selected_values;
    }

    private function get_attributes() {
        if (is_array($this->attributes)) {
            $attributes = array();
            foreach ($this->attributes as $key => $value) {
                $attributes[] = "$key='$value'";
            }
            return implode(' ', $attributes);
        }
        return '';
    }

    public function set_attributes($attributes) {
        $this->attributes = $attributes;
    }
    
    public function get_id() {
        return $this->id;
    }

    public function set_id($id) {
        $this->id = $id;
    }



}

?>
