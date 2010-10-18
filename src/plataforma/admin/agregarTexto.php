<?php

function formularioCargarTexto() {
    ?>
<div class="caja" id="agregarTexto">

    <h2>Agregar Texto</h2>
    <p>
        <label>Idioma:</label>
        <select id="idiomaTexto" name="idiomaTexto">
                <?php cargarIdiomas(); ?>
        </select>
    </p>
    <p>
        <label>M&eacute;todo de cifrado:</label>
        <select id="metodoCifrado" name="metodoCifrado">
                <?php cargarMetodos(); ?>
        </select>
    </p>
    <p>
        <label>Texto Plano:</label>
        <textarea id="textoPlano" cols="20" rows="3" name="textoPlano" ></textarea>
    </p>
    <p>
        <label>Texto Cifrado:</label>
        <textarea id="textoCifrado" cols="20" rows="3" name="textoCifrado"></textarea>
    </p>
    <p>
        <label>Clave:</label>
        <input type="text" id="clave" name="clave"/>
    </p>
    <p>
        <label></label>
        <input type="button" value="Agregar texto" id="agregarTextoBoton" />
    </p>

    <div id="respuestaAgregarTexto"></div>

</div>

<script type="text/javascript" src="../admin/agregarTexto.js"></script>
    <?php }

function cargarIdiomas() {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_idioma, idioma FROM idiomas";

    $idiomasSQL = $db->consulta($consulta);

    while ($idioma = mysql_fetch_object($idiomasSQL)) {
        if($idioma->id_idioma == 2) {
            $selected = "selected";
        } else {
            $selected = "";
        }
        echo '<option value = "'.$idioma->id_idioma.'" '.$selected.'>'.$idioma->idioma.'</option>';
    }
}

function cargarMetodos() {
    require_once '../clases/DB.php';
    $db = new DB();
    $db->conectar();

    $consulta = "SELECT id_metodo, metodo FROM metodos";

    $metodosSQL = $db->consulta($consulta);

    while ($metodo = mysql_fetch_object($metodosSQL)) {
        if($metodo->id_metodo == 1) {
            $selected = "selected";
        } else {
            $selected = "";
        }
        echo '<option value = "'.$metodo->id_metodo.'" '.$selected.'>'.$metodo->metodo.'</option>';
    }
}


if(isset ($_POST['idiomaTexto']) && isset ($_POST['metodoCifrado']) && isset ($_POST['textoPlano'])
        && isset ($_POST['textoCifrado']) && isset ($_POST['clave'])) {

    require_once '../clases/DB.php';

    $id_idioma = DB::limpiarSQL($_POST['idiomaTexto']);
    $id_metodo = DB::limpiarSQL($_POST['metodoCifrado']);
    $texto_plano = DB::limpiarSQL($_POST['textoPlano']);
    $texto_cifrado = DB::limpiarSQL($_POST['textoCifrado']);
    $clave = DB::limpiarSQL($_POST['clave']);

    $nuevoTexto = array(
            "id_idioma" => $id_idioma,
            "id_metodo" => $id_metodo,
            "asignado" => 0,
            "texto_plano" => $texto_plano,
            "texto_cifrado" => $texto_cifrado,
            "clave" => $clave,
    );

    $db = new DB();
    $db->conectar();

    if($db->insertarArreglo("textos", $nuevoTexto)) {
        echo "<span class='mensaje'>El texto se agrego correctamente.</span>";
    } else {
        echo "<span class='error'>Ocurrio un error al agregar un texto.</span>";
    }


}
?>