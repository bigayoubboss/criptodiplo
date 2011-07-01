<?php

function formularioVerEstadoUsuarios() {
    ?>
    <div id="estadoUsuarios" class="caja">
        <h2>Estado de usuarios por actividad</h2>
        <table id="tablaEstadoUsuarios">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Estado</th>
                    <th class="nombreUsuario">Usuario</th>
                    <?php imprimirActividades(); ?>
                </tr>
            </thead>
            <tbody>
                <?php $usuarios = imprimirUsuarios(); ?>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="18">N&uacute;mero de usuarios registrados: <?php echo $usuarios - 1; ?></td>
                </tr>
            </tfoot>
        </table>
        <div class="informacionerror">
            <span class="informacion">Posibles estados: H: Habilitado - D: Deshabilitado</span>
        </div>
    </div>
    <?php
}

function imprimirUsuarios() {
    require_once '../clases/Perfil.php';

    $listaUsuarios = obtenerIDsUsuarios();
    $usuarios = 1;

    while ($idUsuario = mysql_fetch_object($listaUsuarios)) {

        $id_usuario = $idUsuario->id_usuario;

        $perfil = new Perfil($id_usuario);
        $perfil->cargarPerfil();

        $actividades = $perfil->getActividades();

        echo "<tr>";
        echo "<td>" . $usuarios . "</td>";
        echo "<td >" . obtenerEstadoUsuario($id_usuario) . "</td>";
        echo "<td title='Id: " . $id_usuario . "'>" . obtenerNmobreUsuario($id_usuario) . "</td>";
        foreach ($actividades as $actividad) {
            $title = obtenerNombreEstado($actividad->getEstado());
            if ($actividad->getIdTexto() != '') {
                $title .= ' - Texto: ' . $actividad->getIdTexto();
                $title .= ' - Iniciada: ' . $actividad->getFechaInicio();
                if ($actividad->getFechaFin() != '--') {
                    $title .= ' - Finalizada: ' . $actividad->getFechaFin();
                }
                $title .= ' - Intentos: ' . $actividad->getIntentos();
            }
            echo '<td class="estado' . $actividad->getEstado() . '" title="' . $title . '"></td>';
        }
        echo "</tr>";

        $usuarios++;
    }
    return $usuarios;
}

function imprimirActividades() {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT nombre,id_actividad FROM actividades WHERE visible=1 ORDER BY id_actividad";
    $actividadesSQL = $db->consulta($consulta);

    while ($actividad = mysql_fetch_object($actividadesSQL)) {
        echo '<th title="' . $actividad->nombre . '">' . $actividad->id_actividad . '</th>';
    }

    $db->desconectar();
}

function obtenerNmobreUsuario($id_usuario) {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT nombres,apellidos FROM usuarios WHERE id_usuario=" . $id_usuario;

    $usuarioSQL = $db->consulta($consulta);
    $usuario = mysql_fetch_object($usuarioSQL);

    $db->desconectar();

    return ucwords(strtolower($usuario->nombres . " " . $usuario->apellidos));
}

function obtenerEstadoUsuario($id_usuario) {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT habilitado FROM usuarios WHERE id_usuario=" . $id_usuario;

    $usuarioSQL = $db->consulta($consulta);
    $usuario = mysql_fetch_object($usuarioSQL);

    $db->desconectar();
    if ($usuario->habilitado) {
        $estado = "H";
    } else {
        $estado = "D";
    }
    return $estado;
}

function obtenerIDsUsuarios() {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_usuario FROM usuarios WHERE administrador=0 ORDER BY nombres";

    $listaUsuarios = $db->consulta($consulta);
    $db->desconectar();

    return $listaUsuarios;
}

function obtenerNombreEstado($estado) {
    switch ($estado) {
        case 0:
            return "Actividad deshabilitada";
        case 1:
            return "Actividad habilitada";
        case 2:
            return "Actividad iniciada";
        case 3:
            return "Actividad terminada";
    }
}
?>
