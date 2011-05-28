<?php

function formularioCambiarEstadoRecurso() {
    ?>
    <div class="caja" id="cambiarEstadoRecurso">

        <h2 class="tarea">Cambiar estado Recurso</h2>

        <p>
            <label>Recurso:</label>
            <select id="recurso" name="recurso">
                <?php cargarRecursos(); ?>
            </select>
        </p>

        <p>
            <label>Opci&oacute;n:</label>
            <select id="estadoRecurso" name="estadoRecurso">
                <option value="0">Deshabilitar</option>
                <option value="1">Habilitar</option>
            </select>
        </p>

        <p>
            <label></label>
            <input type="button" id="cambiarEstadoRecursoBoton" value="Cambiar estado" />
        </p>

        <div class="informacionerror">
            <span class="informacion">Posibles estados: H: Habilitado - D: Deshabilitado</span>
        </div>
        <div id="respuestaEstadoRecurso"></div>

    </div>
    <script type="text/javascript" src="../admin/cambiarEstadoRecurso.js"></script>

    <?php
}

function cargarRecursos() {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_recursos,substring(nombres_mostrar,1,50) as nombres_mostrar,IF(habilitado = 1, '[H]','[D]') as habilitado
                FROM recursos
                ORDER BY id_metodo";

    $recursosSQL = $db->consulta($consulta);

    while ($recurso = mysql_fetch_object($recursosSQL)) {
        echo '<option value = "' . $recurso->id_recursos . '">' . $recurso->habilitado . ' ' . $recurso->nombres_mostrar . '</option>';
    }
}

if (isset($_POST['recurso']) && isset($_POST['estadoRecurso'])) {

    require_once '../clases/DB.php';

    $id_recurso = DB::limpiarSQL($_POST['recurso']);
    $habilitado = DB::limpiarSQL($_POST['estadoRecurso']);

    $estadoRecurso = array(
        "habilitado" => $habilitado
    );

    $db = new DB();
    $db->conectar();

    if ($db->actualizarArreglo($estadoRecurso, "recursos", "id_recursos=" . $id_recurso)) {
        echo "<span class='mensaje'>El estado del recurso se cambio exitosamente.</span>";
    } else {
        echo "<span class='error'>No se completo el cambio de estado del recurso.</span>";
    }
}
?>