<?php

function formularioCambiarEstadoActividad() {
?>
    <div class="caja" id="cambiarEstadoActividad">

        <h2 class="tarea">Habilitar Actividad</h2>

        <p>
            <label>Actividad:</label>
            <select id="actividad" name="actividad">
            <?php cargarActividades(); ?>
        </select>
    </p>

    <p>
        <label>Opci&oacute;n:</label>
        <select id="estadoActividad" name="estadoActividad">
            <option value="0">Deshabilitar</option>
            <option value="1">Habilitar</option>
        </select>
    </p>

    <p>
        <label></label>
        <input type="button" id="cambiarEstadoActividadBoton" value="Cambiar estado" />
    </p>

    <div class="informacionerror">
        <span class="informacion">Posibles estados: H: Habilitada - D: Deshabilitada</span>
    </div>
    <div id="respuestaEstadoActividad"></div>

</div>
<script type="text/javascript" src="../admin/cambiarEstadoActividad.js"></script>

<?php
        }

        function cargarActividades() {
            require_once '../clases/DB.php';
            $db = new DB();
            $db->conectar();

            $consulta = "SELECT id_actividad,nombre,IF(habilitada = 1, '[H]','[D]') as habilitada
                FROM actividades
                ORDER BY id_actividad";

            $actividadSQL = $db->consulta($consulta);

            while ($actividad = mysql_fetch_object($actividadSQL)) {
                echo '<option value = "' . $actividad->id_actividad . '">' . $actividad->habilitada . ' ' . $actividad->nombre . '</option>';
            }
        }

        if (isset($_POST['actividad']) && isset($_POST['estadoActividad'])) {

            require_once '../clases/DB.php';

            $id_actividad = DB::limpiarSQL($_POST['actividad']);
            $habilitada = DB::limpiarSQL($_POST['estadoActividad']);

            $estadoActividad = array(
                "habilitada" => $habilitada
            );

            $db = new DB();
            $db->conectar();

            if ($db->actualizarArreglo($estadoActividad, "actividades", "id_actividad=" . $id_actividad)) {
                echo "<span class='mensaje'>El estado de la actividad se cambio exitosamente.</span>";
            } else {
                echo "<span class='error'>No se completo el cambio de estado de la actividad.</span>";
            }
        }
?>