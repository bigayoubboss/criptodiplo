<?php

function formularioVerNumeroTextos() {
?>
    <div id="estadoTextos" class="caja">
        <h2>Estado de textos por m&eacute;todo de cifrado</h2>
        <table id="tablaEstadoTextos">
            <thead>
                <tr>
                    <th>M&eacute;todo de cifrado</th>
                    <th>Textos asignados</th>
                    <th>Textos disponibles</th>
                </tr>
            </thead>
            <tbody>
<?php imprimirNumeroTextos(); ?>
        </tbody>
        <tfoot>
<?php imprimirTotalTextos(); ?>
        </tfoot>
    </table>
</div>

<?php
}

function imprimirNumeroTextos() {
	require_once '../clases/DB.php';

	$db = new DB();
	$db->conectar();

	$consulta = "SELECT metodo,id_metodo FROM metodos ORDER BY id_metodo";
	$metodosSQL = $db->consulta($consulta);

	while ($metodo = mysql_fetch_object($metodosSQL)) {
		$disponibles = obtenerNumeroTextos($metodo->id_metodo, 0);
		$asignados = obtenerNumeroTextos($metodo->id_metodo, 1);

		echo "<tr>";
		echo '<td>'.$metodo->metodo.'</td>';
		echo '<td>'.$asignados.'</td>';
		if ($disponibles == 0) {
			echo '<td class="estado0">'.$disponibles.'</td>';
		} else {
			echo '<td>'.$disponibles.'</td>';
		}

		echo "</tr>";
	}

	$db->desconectar();
}

function imprimirTotalTextos() {
	require_once '../clases/DB.php';

	$db = new DB();
	$db->conectar();

	echo "<tr>";
	echo '<td><strong>Total</strong></td>';
	echo '<td>'.obtenerNumeroTextos(0, 1).'</td>';
	echo '<td>'.obtenerNumeroTextos(0, 0).'</td>';
	echo "</tr>";

	$db->desconectar();
}

function obtenerNumeroTextos($id_metodo, $asignado) {
	require_once '../clases/DB.php';

	$db = new DB();
	$db->conectar();

	if ($id_metodo == 0) {
		$consulta = "SELECT COUNT(*) FROM textos WHERE asignado=".$asignado;
	} else {
		$consulta = "SELECT COUNT(*) FROM textos WHERE asignado=".$asignado." AND id_metodo=".$id_metodo;
	}

	$numeroDisponiblesSQL = $db->consulta($consulta);

	$numeroDisponibles = mysql_fetch_array($numeroDisponiblesSQL);

	$db->desconectar();

	if ($numeroDisponibles['COUNT(*)'])
		return $numeroDisponibles['COUNT(*)'];
	return 0;
}
?>