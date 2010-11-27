
<?php

function descargarArchivo() {

	$mensajeError = '<span class="error">Lo sentimos. El recurso solicitado no existe o no se encuentra disponible.</span>';
	$mensajeRequisitos = '<span class="error">Lo sentimos. Para acceder a este recurso debe haber finalizado correctamente las actividades anteriores.</span>';

	if (isset($_GET['actividad']) && isset($_GET['recurso'])) {

		if (!isset($_SESSION)) {
			session_start();
		}
		$id_actividad = $_GET['actividad'];
		$id_recurso = $_GET['recurso'];
		$id_usuario = $_SESSION['idUsuario'];

		if ($id_usuario) {

			$recurso = comprobarExistencia($id_recurso);

			if ($recurso && comprobarConsistencia($id_actividad, $id_recurso)) {
				if ($recurso->requisitos) {
					if (!cumpleRequisitos($id_actividad, $id_usuario)) {
						$recurso = $mensajeRequisitos;
					}
				}
			} else {
				$recurso = $mensajeError;
			}
		}
	} else {
		$recurso = $mensajeError;
	}

	return $recurso;
}

function comprobarConsistencia($id_actividad, $id_recurso) {
	require_once '../clases/DB.php';
	$db = new DB();
	$db->conectar();

	$consulta = "SELECT COUNT(*) FROM recursos
        WHERE id_recursos=".DB::limpiarSQL($id_recurso)."
        AND id_metodo = (SELECT id_metodo FROM actividades WHERE id_actividad=".DB::limpiarSQL($id_actividad).")";

	$recursoSQL = $db->consulta($consulta);
	$recurso = mysql_fetch_array($recursoSQL);

	mysql_free_result($recursoSQL);
	$db->desconectar();

	if ($recurso['COUNT(*)'])
		return true;
	return false;
}

function comprobarExistencia($id_recurso) {
	require_once '../clases/DB.php';
	$db = new DB();
	$db->conectar();

	$consulta = "SELECT * FROM recursos WHERE id_recursos = ".DB::limpiarSQL($id_recurso);
	$recursoSQL = $db->consulta($consulta);
	$recurso = mysql_fetch_object($recursoSQL);

	mysql_free_result($recursoSQL);
	$db->desconectar();

	if ($recurso)
		return $recurso;
	return false;
}

function cumpleRequisitos($id_actividad, $id_usuario) {
	require_once '../clases/DB.php';
	$db = new DB();
	$db->conectar();

	$consulta = "SELECT COUNT(*) FROM actividades_por_usuario
        WHERE id_usuario=".DB::limpiarSQL($id_usuario)."
        AND fecha_fin!='--'
        AND id_actividad<".DB::limpiarSQL($id_actividad);

	$numeroactividadesTermiandasSQL = $db->consulta($consulta);
	$numeroactividadesTermiandas = mysql_fetch_array($numeroactividadesTermiandasSQL);

	mysql_free_result($numeroactividadesTermiandasSQL);
	$db->desconectar();

	if ($numeroactividadesTermiandas['COUNT(*)'] == ($id_actividad - 1))
		return true;
	return false;
}

function redireccionarDescarga($recurso) {

	if (stripos($recurso->enlace, 'ttp:')) {
		Header("Location: ".$recurso->enlace);
	} else {
		header('Content-Description: File Transfer');
		header('Content-Type: application/octet-stream');
		header('Content-Disposition: attachment; filename='.basename($recurso->enlace));
		header('Content-Transfer-Encoding: binary');
		header('Expires: 0');
		header('Cache-Control: must-revalidate, post-check=0, pre-check=0');
		header('Pragma: public');
		header('Content-Length: '.filesize($recurso->enlace));
		ob_clean();
		flush();
		readfile($recurso->enlace);
		exit;
	}
}
?>
