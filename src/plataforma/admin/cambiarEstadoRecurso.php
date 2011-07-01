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

    $consulta = "SELECT r.id_recursos as id_recurso,substring(r.nombres_mostrar,1,50) as nombres_mostrar,IF(r.habilitado = 1, '[H]','[D]') as habilitado,m.metodo as metodo
                FROM recursos r,metodos m
                WHERE m.id_metodo = r.id_metodo
                ORDER BY m.metodo";

    $recursosSQL = $db->consulta($consulta);

    while ($recurso = mysql_fetch_object($recursosSQL)) {
        echo '<option value = "' . $recurso->id_recursos . '">' .  ' ' .$recurso->habilitado . ' ' . $recurso->metodo . ' - ' . $recurso->nombres_mostrar . '</option>';
    }
}

if (isset($_POST['recurso']) && isset($_POST['estadoRecurso'])) {

    require_once '../clases/DB.php';

    $id_recurso = $_POST['recurso'];
    $habilitado = $_POST['estadoRecurso'];

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
