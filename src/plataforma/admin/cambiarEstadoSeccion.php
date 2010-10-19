<?php

function formularioCambiarEstadoSeccion() {
?>
    <div class="caja" id="cambiarEstadoSeccion">

        <h2 class="opciones">Cambiar Estado Secci&oacute;n</h2>
        <p>
            <label>Secci&oacute;n:</label>
            <select id="seccion">
            <?php cargarSecciones(); ?>
        </select>
    </p>
    <p>
        <label>Opci&oacute;n:</label>
        <select id="estadoSeccion">
            <option value="0">Deshabilitar</option>
            <option value="1">Habilitar</option>
        </select>
    </p>
    <p>
        <label></label>
        <input type="button" value="Cambiar estado" id="cambiarEstadoSeccionBoton"/>
    </p>

    <div class="informacionerror">
        <span class="informacion">Posibles estados: H: Habilitado - D: Deshabilitado</span>
    </div>
    <div id="respuestaEstadoSeccion"></div>

</div>
<script type="text/javascript" src="../admin/cambiarEstadoSeccion.js"></script>

<?php
        }

        function cargarSecciones() {
            require_once '../clases/DB.php';
            $db = new DB();
            $db->conectar();

            $consulta = "SELECT id_seccion_habilitadas,seccion,IF(habilitada = 1, '[H]','[D]') as habilitada
                FROM secciones_habilitadas";

            $seccionesSQL = $db->consulta($consulta);

            while ($seccion = mysql_fetch_object($seccionesSQL)) {
                echo '<option value = "' . $seccion->id_seccion_habilitadas . '">' . $seccion->habilitada . ' ' . $seccion->seccion . '</option>';
            }
        }

        if (isset($_POST['seccion']) && isset($_POST['estadoSeccion'])) {

            require_once '../clases/DB.php';

            $id_seccion = DB::limpiarSQL($_POST['seccion']);
            $habilitada = DB::limpiarSQL($_POST['estadoSeccion']);

            $estadoSeccion = array(
                "habilitada" => $habilitada
            );

            $db = new DB();
            $db->conectar();

            if ($db->actualizarArreglo($estadoSeccion, "secciones_habilitadas", "id_seccion_habilitadas=" . $id_seccion)) {
                echo "<span class='mensaje'>El estado de la secci&oacute;n se cambio exitosamente.</span>";
            } else {
                echo "<span class='error'>No se completo el cambio de estado de la secci&oacute;n.</span>";
            }
        }
?>