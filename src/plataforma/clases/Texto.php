<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Texto
 *
 * @author SirLock
 */
class Texto {

    private $id_texto;
    private $texto_plano;
    private $texto_cifrado;
    private $clave;
    private $idioma;
    private $metodo;
    private $asignado;

    function __construct($id_texto) {
        $this->id_texto = $id_texto;
    }

    function cargarTexto() {
        require_once 'DB.php';
        require_once 'Idioma.php';
        require_once 'Metodo.php';

        $db = new DB();
        $db->conectar();

        $consulta = "SELECT * FROM textos WHERE id_texto=" . DB::limpiarSQL($this->id_texto);
        $textoSQL = $db->consulta($consulta);
        $texto = mysql_fetch_object($textoSQL);
        if ($texto->texto_plano) {
            $this->texto_plano = $texto->texto_plano;
            $this->texto_cifrado = $texto->texto_cifrado;
            $this->idioma = Idioma::devovlerIdioma($texto->id_idioma);
            $this->metodo = Metodo::devovlerMetodo($texto->id_metodo);
            $this->asignado = $texto->asgnado;
            $this->clave = $texto->clave;
        }
        mysql_free_result($textoSQL);
        $db->desconectar();
    }

    public function getTextoPlano() {
        if ($this->texto_plano == '') {
            return "Error: Texto Plano no encontrado, comun&iacute;quese con el administrador del sistema";
        }
        return html_entity_decode($this->texto_plano, ENT_COMPAT, 'UTF-8');
    }

    public function getTextoCifrado() {
        if ($this->texto_cifrado == '') {
            return "Error: Texto Cifrado no encontrado, comun&iacute;quese con el administrador del sistema";
        }
        return html_entity_decode($this->texto_cifrado, ENT_COMPAT, 'UTF-8');
    }

    public function getClave() {
        if ($this->clave == '') {
            return "Clave no encontrada, comun&iacute;quese con el administrador del sistema";
        }
        return $this->clave;
    }

    public function getIdioma() {
        if ($this->idioma == '') {
            return "Idioma no deifnido, comun&iacute;quese con el administrador del sistema";
        }
        return $this->idioma;
    }

    public function getMetodo() {
        if ($this->metodo == '') {
            return "M&eacute;todo no deifnido, comun&iacute;quese con el administrador del sistema";
        }
        return $this->metodo;
    }

}

?>
