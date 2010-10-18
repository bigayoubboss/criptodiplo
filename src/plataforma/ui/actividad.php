<?php
require_once '../clases/Login.php';

$usuarioActivo = Login::redireccionarUsuarios(Login::existeUsuarioActivo(), 0);
if ($usuarioActivo) {
    require_once '../uicontrolador/Actividad.php';
    $actividad->cargarRecursos();
    $recursos = $actividad->getRecursos();
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">

    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <title>Diplomado en Criptograf&iacute;a :: Universidad Nacional de Colombia</title>

        <link rel="stylesheet" type="text/css" href="../css/css.css" />

        <script type="text/javascript" src="../js/sha256.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../js/cerrarSesion.js"></script>
        <script type="text/javascript" src="../js/completarActividad.js"></script>
        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">

            <div id="cajaActividad">
                <h1 id="tituloLogin">Actividad: <?php echo $actividad->getNombre(); ?></h1>

                <div id="enlaces">
                    <a href="material.php" id="materialP">Material</a>
                    <a href="inicio.php" id="inicioPD">Inicio</a>
                    <a href="" id="logout">Cerrar sesi&oacute;n</a>
                </div>

                <?php if ($actividad->getMostrarTextoCifrado()) {
                ?>
                    <div id="actividadTextoCifrado" class="caja">
                        <h2 id="textoCifradoT">Texto Cifrado</h2>
                        <p>El siguiente texto esta cifrado con el m&eacute;todo de
                            <strong><?php echo $texto->getMetodo(); ?></strong> y el texto plano
                            esta en <strong><?php echo $texto->getIdioma(); ?></strong></p>
                        <span><?php echo $texto->getTextoCifrado(); ?></span>
                    </div><?php } ?>

                <?php if ($actividad->getEstado() == 3 || !$actividad->getMostrarTextoCifrado()) {
                ?>
                    <div id="actividadTextoPlano" class="caja">
                        <h2 id="textoPlanoT">Texto Plano</h2>
                        <span><?php echo $texto->getTextoPlano(); ?></span>
                    </div><?php } ?>

                <?php if ($actividad->getEstado() == 3 || $actividad->getMostrarClave()) {
                ?>
                    <div id="actividadClave" class="caja">
                        <h2 id="claveT">Clave</h2>
                        <p>La clave de cifrado es: <strong><?php echo $texto->getClave(); ?></strong></p>
                        <br />
                    </div><?php } ?>


                <?php if ($actividad->getEstado() == 2 && $actividad->getInformacionAdicional()) {
                ?>
                    <div id="actividadInformacionAdicional" class="caja">
                        <h2 id="informacionAdicionalT">Informaci&oacute;n adicional</h2>
                        <ul>
                        <?php imprimirInformacionAdicional($actividad, $texto); ?>
                    </ul>
                </div><?php } ?>

                <?php if ($actividad->getEstado() == 2) {
                ?>
                        <form action="#" method="post" id="actividadFormularioCompletar">
                            <h2 id="completarActividadT">Completar actividad</h2>


                            <p>
                                <label>Respuesta:</label>
                                <input type="text" name="clave" id="clave" />
                                <input type="hidden" name="idActividad" value="<?php echo $actividad->getIdActividad(); ?>" id="idActividad"/>
                            </p>
                            <p>
                                <label></label>
                                <input type="submit" value="Completar" />
                            </p>
                            <div id="respuestaCompletar"></div>
                            <span class="informacion"><?php if ($actividad->getFormaRespuesta() != '')
                            echo $actividad->getFormaRespuesta(); ?></span>
                    <?php } ?>
                </form>

                <div id="actividadRecursos" class="caja">
                    <h2 id="recursosT">Recursos</h2>
                    <p>Los siguientes recursos estan relacionados con esta actividad:</p>
                    <ul>
                        <?php imprimirRecursos($recursos, $actividad->getIdActividad()); ?>
                    </ul>
                    <br />
                </div>

                <div id="actividadLog" class="caja">
                    <h2 id="recursosT">Información de la actividad</h2>
                    <p>El sistema ha registrado los siguientes datos de esta actividad:</p>
                    <ul>
                        <li><strong>Fecha de inicio: </strong><?php echo $actividad->getFechaInicio(); ?></li>
                        <li><strong>Fecha de finalización: </strong>
                            <?php
                            if ($actividad->getFechaFin() == '--')
                                echo 'Esta actividad se encuentra aún sin terminar';
                            else
                                echo $actividad->getFechaFin();
                            ?>
                        </li>
                        <li><strong>Intentos realizados: </strong><?php echo $actividad->getIntentos(); ?></li>
                    </ul>
                    <br />
                </div>

                <noscript class="mensajeNoScript">
                    <div class="error">
                        <span class="error">La p&aacute;gina que est&aacute;s viendo requiere para su funcionamiento el uso de JavaScript.
                            Si lo has deshabilitado intencionadamente, por favor vuelve a activarlo.</span>
                    </div>
                </noscript>
            </div>
        </div>
    </body>
</html>

<?php

                            function imprimirRecursos($recursos, $id_actividad) {
                                if (sizeof($recursos) == 0) {
                                    echo "<li>No hay recursos asociados a esta actividad</li>";
                                } else {
                                    foreach ($recursos as $recurso) {
                                        echo "<li><a href='../ui/descarga.php?recurso=" . $recurso->id_recursos . "&actividad=" . $id_actividad . "'>" .
                                        html_entity_decode($recurso->nombres_mostrar, ENT_COMPAT, 'UTF-8') . "</a></li>";
                                    }
                                }
                            }

                            function imprimirInformacionAdicional($actividad, $texto) {
                                require_once '../uicontrolador/ActividadInformacionAdicional.php';
                            }
?>