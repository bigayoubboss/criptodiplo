<?php
require_once '../clases/Login.php';
$rol = Login::existeUsuarioActivo();
if ($rol == - 1) {
	Login::redireccionarUsuarios($rol, 0);
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">

    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <title>Diplomado en Criptograf&iacute;a :: Universidad Nacional de Colombia</title>

        <link rel="stylesheet" type="text/css" href="../css/css.css" />

        <script type="text/javascript" src="../js/sha256.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/cerrarSesion.js"></script>
        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">

            <div id="cajaDescarga">

                <h1>Material</h1>

                <div id="enlaces">
                    <a href="inicio.php" id="inicioPD">Inicio</a>
                    <a href="" id="logout">Cerrar sesi&oacute;n</a>
                </div>

                <div class="caja">
                    <h2 id="diapositivasClase">Presentaciones</h2>
                    <ul>
                        <?php imprimirRecursos(1000); ?>
                    </ul>
                </div>

                <div class="caja">
                    <h2 id="otrosRecursos">Otros recursos</h2>
                    <ul>
                        <?php imprimirRecursos(2000); ?>
                    </ul>
                </div>

                <div class="caja">
                    <h2 id="bibliografia">Bibliograf&iacute;a</h2>
                    <ul>
                        <?php imprimirRecursos(3000); ?>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>

<?php

function imprimirRecursos($id_metodo) {
	require_once '../clases/DB.php';
	$db = new DB();
	$db->conectar();

	$consulta = "SELECT * FROM recursos WHERE id_metodo = ".DB::limpiarSQL($id_metodo);
	$recursos = $db->consulta($consulta);

	if (!$recursos) {
		echo "<li>No hay recursos asociados a esta actividad</li>";
	} else {
		while ($recurso = mysql_fetch_object($recursos)) {
			echo "<li><a href='".$recurso->enlace."'>".html_entity_decode($recurso->nombres_mostrar, ENT_COMPAT, 'UTF-8')."</a></li>";
		}
	}

	mysql_free_result($recursos);
	$db->desconectar();
}
?>