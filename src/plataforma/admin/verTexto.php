<?php

function formularioVerTexto() {
?>
    <div class="caja" id="verTexto">

        <h2>Ver Texto</h2>
        <p>
            <label>Id Texto:</label>
            <input type="text" id="idTexto"/>
        </p>
        <p>
            <label></label>
            <input type="button" value="Cargar texto" id="verTextoBoton" />
        </p>

        <div id="respuestaVerTexto"></div>

    </div>

    <script type="text/javascript" src="../admin/verTexto.js"></script>
<?php
}

if (isset($_POST['idTexto'])) {

    require_once '../clases/DB.php';

    $id_texto = DB::limpiarSQL($_POST['idTexto']);

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT t.texto_cifrado, t.texto_plano, t.clave, IF(t.asignado=0,'Sin asignar',(
            SELECT CONCAT(u.nombres, ' ' , u.apellidos)
            FROM actividades_por_usuario a, usuarios u
            WHERE a.id_usuario = u.id_usuario
            AND a.id_texto = t.id_texto)) as asignado, m.metodo, i.idioma
        FROM textos t, metodos m, idiomas i, actividades_por_usuario a, usuarios u
        WHERE m.id_metodo = t.id_metodo
        AND a.id_usuario = u.id_usuario
        AND i.id_idioma = t.id_idioma
        AND t.id_texto = " . $id_texto;

    $textoSQL = $db->consulta($consulta);
    $texto = mysql_fetch_object($textoSQL);

    $db->desconectar();

    if ($texto->texto_plano != '') {
        imprimirTexto($texto);
    } else {
        echo "<span class='error'>EL texto solicitado no se encuentra disponible.</span>";
    }
}

function imprimirTexto($texto) {
    $textoPlano = html_entity_decode($texto->texto_plano, ENT_COMPAT, 'UTF-8');
    $textoPlano = '<div id="actividadTextoPlano"><span><strong>Texto Plano</strong><br />' . $textoPlano . '</span></div>';

    $textoCifrado = html_entity_decode($texto->texto_cifrado, ENT_COMPAT, 'UTF-8');
    $textoCifrado = '<div id="actividadTextoCifrado" ><span><strong>Texto Cifrado</strong><br />' . $textoCifrado . '</span></div>';

    $clave = html_entity_decode($texto->clave, ENT_COMPAT, 'UTF-8');
    $clave = '<div id="actividadClave"><p><strong>Clave: </strong>' . $clave . '</p></div>';

    $detalles = '<div id="actividadDetalles"><p><strong>Asignado a: </strong>' . $texto->asignado;
    $detalles .= '</br><strong>M&eacute;todo: </strong>' . $texto->metodo;
    $detalles .= '</br><strong>Idioma: </strong>' . $texto->idioma . '</p></div>';

    echo $textoPlano . $textoCifrado . $clave . $detalles;
}
?>