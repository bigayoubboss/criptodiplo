<?php
require_once '../clases/Login.php';
Login::redireccionarUsuarios(Login::existeUsuarioActivo(),0);
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
        <script type="text/javascript" src="../js/cerrarSesion.js"></script>
        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">

            <div id="cajaDescarga">

                <h1>Descarga de recursos</h1>

                <div id="enlaces">
                    <a href="actividad.php?act=<?php echo $_GET['actividad']?>" id="actividadVolver">Volver a la actividad</a>
                    <a href="material.php" id="materialP">Material</a>
                    <a href="inicio.php" id="inicioPD">Inicio</a>
                    <a href="" id="logout">Cerrar sesi&oacute;n</a>
                </div>

                <div class="caja">

                    <?php
                    require_once '../uicontrolador/Descarga.php';
                    $recurso = descargarArchivo();
                    if($recurso->id_recursos) {
                        redireccionarDescarga($recurso);
                    } else {
                        echo '<h2 id="descargaNo">Recurso no disponible</h2>';
                        echo '<p>Informaci&oacute;n del recurso: no disponible</p><br />';
                        echo $recurso;
                    }
                    ?>
                </div>
            </div>
        </div>
    </body>
</html>


