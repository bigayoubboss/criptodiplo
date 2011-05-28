<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Idioma
 *
 * @author SirLock
 */
class Idioma {

    /**
     * Devuelve el id de un idioma con base a su nombre
     * @param String $idioma Nombre del idioma
     * @return int El id del idioma  o FALSE si no existe
     */
    public static function devovlerIdIdioma($idioma) {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT id_idioma FROM idiomas WHERE idioma ='" . DB::limpiarSQL($idioma) . "'";
        $id_idiomaSQL = $db->consulta($consulta);
        $id_idioma = mysql_fetch_object($id_idiomaSQL);

        mysql_free_result($id_idiomaSQL);
        $db->desconectar();

        if ($id_idioma) {
            return $id_idioma->id_idioma;
        }
        return false;
    }

    /**
     * Devuelve el nombre de un idioma con base a su id
     * @param int $id_idioma Id del idioma
     * @return String El nombre del idioma o FALSE si no existe
     */
    public static function devovlerIdioma($id_idioma) {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT idioma FROM idiomas WHERE id_idioma ='" . DB::limpiarSQL($id_idioma) . "'";
        $idiomaSQL = $db->consulta($consulta);
        $idioma = mysql_fetch_object($idiomaSQL);

        mysql_free_result($idiomaSQL);
        $db->desconectar();

        if ($idioma) {
            return $idioma->idioma;
        }
        return false;
    }

}

?>
