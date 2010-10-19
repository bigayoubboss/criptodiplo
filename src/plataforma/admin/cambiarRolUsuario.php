<?php

function formularioCambiarRolUsuario() {
?>
    <div class="caja" id="cambiarRolUsuario">

        <h2 class="tarea">Cambiar Rol Usuario</h2>

        <p>
            <label>Usuario:</label>
            <select id="usuario" name="usuario">
            <?php cargarusuarios(); ?>
        </select>
    </p>

    <p>
        <label>Rol:</label>
        <select id="rolUsuario" name="rolUsuario">
            <option value="0">Usuario</option>
            <option value="1">Administrador</option>
        </select>
    </p>

    <p>
        <label></label>
        <input type="button" id="cambiarRolUsuarioBoton" value="Cambiar rol" />
    </p>

    <div class="informacionerror">
        <span class="informacion">Posibles roles: A: Administrador - U: Usuario</span>
    </div>
    <div id="respuestaRolUsuario"></div>


</div>
<script type="text/javascript" src="../admin/cambiarRolUsuario.js"></script>

<?php
        }

        function cargarusuarios() {
            require_once '../clases/DB.php';
            $db = new DB();
            $db->conectar();

            $consulta = "SELECT id_usuario,nombres, apellidos, IF(administrador=1,'[A]','[U]') as administrador
                FROM usuarios
                ORDER BY nombres";

            $usuarioSQL = $db->consulta($consulta);

            while ($usuario = mysql_fetch_object($usuarioSQL)) {
                $option = '<option value = "' . $usuario->id_usuario . '">' . $usuario->administrador . ' ' . $usuario->nombres . ' ' . $usuario->apellidos . '</option>';
                echo $option;
            }
        }

        if (isset($_POST['usuario']) && isset($_POST['rolUsuario'])) {

            require_once '../clases/DB.php';

            $id_usuario = DB::limpiarSQL($_POST['usuario']);
            $administrador = DB::limpiarSQL($_POST['rolUsuario']);

            $rolUsuario = array(
                "administrador" => $administrador
            );

            $db = new DB();
            $db->conectar();

            if ($db->actualizarArreglo($rolUsuario, "usuarios", "id_usuario=" . $id_usuario)) {
                echo "<span class='mensaje'>El rol de la usuario se cambio exitosamente.</span>";
            } else {
                echo "<span class='error'>No se completo el cambio de rol de la usuario.</span>";
            }
        }
?>