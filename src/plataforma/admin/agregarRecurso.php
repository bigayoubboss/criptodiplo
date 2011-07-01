<?php

function formularioAgregarRecurso() {
    ?>
    <div class="caja" id="agregarRecurso">

        <h2>Agregar Recurso</h2>
        <p>
            <label>Nombre para mostrar:</label>
            <input type="text" id="nombre"/>
        </p>
        <p>
            <label>Enlace</label>
            <input type="text" id="enlace"/>
        </p>
        <p>
            <label>M&eacute;todo de cifrado</label>
            <select id="idMetodo" >
                <option value="1000">Presentaciones</option>
                <option value="2000">Varios</option>
                <option value="3000">Bibliograf&iacute;a</option>
                <?php cargarMetodos(); ?>
            </select>
        </p>
        <p>
            <label>Pre-requisitos</label>
            <select id="prerequisitos" >
                <option value="0">No</option>
                <option value="1">Si</option>
            </select>
        </p>
        <p>
            <label></label>
            <input type="button" value="Agregar recurso" id="agregarRecursoBoton" />
        </p>


        <div id="respuestaAgregarRecurso"></div>

    </div>

    <script type="text/javascript" src="../admin/agregarRecurso.js"></script>
    <?php
}

function cargarMetodos() {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_metodo, metodo FROM metodos";

    $metodosSQL = $db->consulta($consulta);

    while ($metodo = mysql_fetch_object($metodosSQL)) {
        echo '<option value = "' . $metodo->id_metodo . '" ' . $selected . '>' . $metodo->metodo . '</option>';
    }
}

if (isset($_POST['nombre']) && isset($_POST['enlace']) && isset($_POST['prerequisitos']) && isset($_POST['idMetodo'])) {

    require_once '../clases/DB.php';

    $nombre = $_POST['nombre'];
    $enlace = $_POST['enlace'];
    $prerequisitos = $_POST['prerequisitos'];
    $idMetodo = $_POST['idMetodo'];

    $nuevoRecurso = array(
        "nombres_mostrar" => $nombre,
        "enlace" => $enlace,
        "id_metodo" => $idMetodo,
        "requisitos" => $prerequisitos
    );

    $db = new DB();
    $db->conectar();

    if ($db->insertarArreglo("recursos", $nuevoRecurso)) {
        echo "<span class='mensaje'>El recurso se agrego correctamente.</span>";
    } else {
        echo "<span class='error'>Ocurrio un error al agregar el recurso.</span>";
    }
    $db->desconectar();
}
?>
