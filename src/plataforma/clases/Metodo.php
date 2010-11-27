<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Metodo
 *
 * @author SirLock
 */
class Metodo {

	/**
	 * Devuelve el id de un metodo de cifrado con base a su nombre
	 * @param String $metodo Nombre del metodo de cifrado
	 * @return int El id del metodo de cifrado o FALSE si no existe
	 */
	public static function devovlerIdMetodo($metodo) {
		require_once 'DB.php';
		$db = new DB();
		$db->conectar();

		$consulta = "SELECT id_metodo FROM metodos WHERE metodo ='".DB::limpiarSQL($metodo)."'";
		$id_metodoSQL = $db->consulta($consulta);
		$id_metodo = mysql_fetch_object($id_metodoSQL);

		mysql_free_result($id_metodoSQL);
		$db->desconectar();

		if ($id_metodo) {
			return $id_metodo->id_metodo;
		}
		return false;
	}

	/**
	 * Devuelve el nombre de un metodo de cifrado con base a su id
	 * @param int $id_metodo Id del metodo de cifrado
	 * @return String El nombre del metodo de cifrado o FALSE si no existe
	 */
	public static function devovlerMetodo($id_metodo) {
		require_once 'DB.php';
		$db = new DB();
		$db->conectar();

		$consulta = "SELECT metodo FROM metodos WHERE id_metodo ='".DB::limpiarSQL($id_metodo)."'";
		$metodoSQL = $db->consulta($consulta);
		$metodo = mysql_fetch_object($metodoSQL);

		mysql_free_result($metodoSQL);
		$db->desconectar();

		if ($metodo) {
			return $metodo->metodo;
		}
		return false;
	}

}
?>
