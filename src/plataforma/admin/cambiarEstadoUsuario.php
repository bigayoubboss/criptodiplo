<?php

function formularioCambiarEstadoUsuario() {
?>
    <div class="caja" id="cambiarEstadoUsuario">

        <h2 class="tarea">Habilitar Usuario</h2>

        <p>
            <label>Usuario:</label>
            <select id="usuario" name="usuario">
            <?php cargarusuarios(); ?>
        </select>
    </p>

    <p>
        <label>Opci&oacute;n:</label>
        <select id="estadoUsuario" name="estadoUsuario">
            <option value="0">Deshabilitar</option>
            <option value="1">Habilitar</option>
        </select>
    </p>

    <p>
        <label></label>
        <input type="button" id="cambiarEstadoUsuarioBoton" value="Cambiar estado" />
    </p>
    <div class="informacionerror">
        <span class="informacion">Posibles estados: H: Habilitado - D: Deshabilitado</span>
    </div>
    <div id="respuestaEstadoUsuario"></div>


</div>
<script type="text/javascript" src="../admin/cambiarEstadoUsuario.js"></script>

<?php
        }

        function cargarusuarios() {
            require_once '../clases/DB.php';
            $db = new DB();
            $db->conectar();

            $consulta = "SELECT id_usuario,nombres, apellidos, habilitado FROM usuarios ORDER BY nombres";

            $usuarioSQL = $db->consulta($consulta);

            while ($usuario = mysql_fetch_object($usuarioSQL)) {
                $option = '<option value = "' . $usuario->id_usuario . '">[';
                if ($usuario->habilitado) {
                    $option .= 'H';
                } else {
                    $option .= 'D';
                }
                $option .= '] ' . $usuario->nombres . ' ' . $usuario->apellidos . '</option>';
                echo $option;
            }
        }

        if (isset($_POST['usuario']) && isset($_POST['estadoUsuario'])) {

            require_once '../clases/DB.php';

            $id_usuario = DB::limpiarSQL($_POST['usuario']);
            $habilitado = DB::limpiarSQL($_POST['estadoUsuario']);

            $estadoUsuario = array(
                "habilitado" => $habilitado
            );

            $db = new DB();
            $db->conectar();

            if ($db->actualizarArreglo($estadoUsuario, "usuarios", "id_usuario=" . $id_usuario)) {
                echo "<span class='mensaje'>El estado de la usuario se cambio exitosamente.</span>";
            } else {
                echo "<span class='error'>No se completo el cambio de estado de la usuario.</span>";
            }
        }
?>