<?php

function formularioCambiarTexto() { ?>
    <div class="caja" id="cambiarTexto">

        <h2>Cambio de texto</h2>
        <p>
            <label>Usuario:</label>
<?php imprimirUsuarios(); ?>
    </p>
    <p>
        <label>Actividad:</label>
<?php imprimirActividades(); ?>
    </p>
    <p>
        <label></label>
        <input type="button" value="Cambiar texto" id="cambiarTextoBoton" />
    </p>

    <div id="respuestaCambiarTexto"></div>

</div>

<script type="text/javascript" src="../admin/cambiarTexto.js"></script>
<?php
}

function imprimirActividades() {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT nombre,id_actividad FROM actividades ORDER BY id_actividad";
    $actividadesSQL = $db->consulta($consulta);

    echo '<select id="idActividad">';
    while ($actividad = mysql_fetch_object($actividadesSQL)) {
        echo '<option value="' . $actividad->id_actividad . '">' . $actividad->nombre . '</option>';
    }
    echo '</select>';

    $db->desconectar();
}

function imprimirUsuarios() {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_usuario,nombres,apellidos FROM usuarios WHERE administrador=0 ORDER BY nombres";
    $usuariosSQL = $db->consulta($consulta);

    echo '<select id="idUsuario">';
    while ($usuario = mysql_fetch_object($usuariosSQL)) {
        echo '<option value="' . $usuario->id_usuario . '">' . $usuario->nombres . ' ' . $usuario->apellidos . '</option>';
    }
    echo '</select>';

    $db->desconectar();
}

if (isset($_POST['idUsuario']) && isset($_POST['idActividad'])) {
    require_once '../clases/DB.php';
    $id_usuario = DB::limpiarSQL($_POST['idUsuario']);
    $id_actividad = DB::limpiarSQL($_POST['idActividad']);

    $id_textoViejo = inicioActividad($id_usuario, $id_actividad);

    if ($id_textoViejo) {
        $id_textoNuevo = generarNuevoTexto($id_usuario, $id_actividad);
        if ($id_textoNuevo) {
            if (cambiarEstadoTexto($id_textoViejo, 0) && cambiarEstadoTexto($id_textoNuevo, 1)) {
                if (cambiarTexto($id_usuario, $id_actividad, $id_textoNuevo)) {
                    echo "<span class='mensaje'>Cambio de texto realizado correctamente.</span>";
                } else {
                    echo "<span class='error'>No fue posible realizar el cambio de texto.</span>";
                }
            } else {
                echo "<span class='error'>No fue posible realizar el cambio de texto.</span>";
            }
        } else {
            echo "<span class='error'>No existen textos disponibles para hacer el cambio.</span>";
        }
    } else {
        echo "<span class='error'>El usuario no ha iniciado la actividad seleccionada.</span>";
    }
}

function inicioActividad($id_usuario, $id_actividad) {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_texto FROM actividades_por_usuario WHERE id_usuario=" . $id_usuario . " AND id_actividad=" . $id_actividad;

    $id_textoSQL = $db->consulta($consulta);
    $id_texto = mysql_fetch_object($id_textoSQL);

    $db->desconectar();
    if ($id_texto->id_texto != '')
        return $id_texto->id_texto;
    return false;
}

function generarNuevoTexto($id_usuario, $id_actividad) {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_texto FROM textos
        WHERE asignado=0
        AND id_metodo=(SELECT id_metodo FROM actividades WHERE id_actividad=" . $id_actividad . ")
            ORDER BY RAND() LIMIT 1";

    $id_textoSQL = $db->consulta($consulta);
    $id_texto = mysql_fetch_object($id_textoSQL);

    $db->desconectar();

    if ($id_texto->id_texto != '')
        return $id_texto->id_texto;
    return false;
}

function cambiarEstadoTexto($id_texto, $asignado) {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $estadoTexto = array(
        'asignado' => $asignado
    );

    if ($db->actualizarArreglo($estadoTexto, 'textos', 'id_texto=' . $id_texto))
        return true;
    return false;
}

function cambiarTexto($id_usuario, $id_actividad, $id_texto) {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $cambioTexto = array(
        'id_texto' => $id_texto
    );
    $condicion = 'id_usuario=' . $id_usuario . ' AND id_actividad=' . $id_actividad;

    if ($db->actualizarArreglo($cambioTexto, 'actividades_por_usuario', $condicion))
        return true;
    return false;
}
?>
