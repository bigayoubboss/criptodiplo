<?php
require_once '../clases/Login.php';
Login::redireccionarUsuarios(Login::existeUsuarioActivo(), 1);
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
        <script type="text/javascript" src="../js/herramientas.js"></script>


        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">
            <div id="cajaAdmin">
                <h1 id="tituloAdmin">Herramientas de administrador</h1>

                <div id="enlaces">
                    <a href="material.php" id="materialP">Material</a>
                    <a href="" id="logout">Cerrar sesi&oacute;n</a>
                </div>
            </div>

            <div class="caja" id="herramientasDisponibles">
                <h2>Herramientas disponibles</h2>
                <p>
                    <label>Herramienta:</label>
                    <select id="herramientasOpciones">
                        <option value="verEstadoUsuarios">Ver estado usuarios</option>
                        <option value="verEstadoBDTextos">Ver n&uacute;mero de textos disponibles</option>
                        <option value="verTexto">Ver texto</option>
                        <option value="--" disabled>---------------------------------------</option>
                        <option value="cambiarEstadoActividad">Habilitar Actividad</option>
                        <option value="cambiarEstadoSeccion">Habilitar Login</option>                        
                        <option value="--" disabled>---------------------------------------</option>
                        <option value="cambiarTexto">Cambiar Texto</option>
                        <option value="agregarTexto">Agregar Texto</option>
                        <option value="--" disabled>---------------------------------------</option>
                        <option value="agregarRecurso">Agregar Recurso</option>
                    </select>
                </p>
                <p>
                    <label></label>
                    <input type="button" value="Cargar" id="cargarHerramienta" />
                </p>
            </div>

            <div id="herramientas"></div>
        </div>



    </body>
</html>
