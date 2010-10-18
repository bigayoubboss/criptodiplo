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
    <?php }
if(isset ($_POST['idTexto'])) {

    require_once '../clases/DB.php';

    $id_texto = DB::limpiarSQL($_POST['idTexto']);

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT texto_cifrado, texto_plano, clave, asignado FROM textos WHERE id_texto=".$id_texto;

    $textoSQL = $db->consulta($consulta);
    $texto = mysql_fetch_object($textoSQL);

    $db->desconectar();

    if($texto->texto_plano!='') {
        imprimirTexto($texto);
    } else {
        echo "<span class='error'>EL texto solicitado no se encuentra disponible.</span>";
    }
}

function imprimirTexto($texto) {
    $textoPlano = html_entity_decode($texto->texto_plano,ENT_COMPAT,'UTF-8');
    $textoPlano = '<div id="actividadTextoPlano"><span><strong>Texto Plano</strong><br />'.$textoPlano.'</span></div>';

    $textoCifrado = html_entity_decode($texto->texto_cifrado,ENT_COMPAT,'UTF-8');
    $textoCifrado = '<div id="actividadTextoCifrado" ><span><strong>Texto Cifrado</strong><br />'.$textoCifrado.'</span></div>';

    $clave = html_entity_decode($texto->clave,ENT_COMPAT,'UTF-8');
    $clave = '<div id="actividadClave"><p><strong>Clave: </strong>'.$clave.'</p></div>';

    echo $textoPlano.$textoCifrado.$clave;
}
?>