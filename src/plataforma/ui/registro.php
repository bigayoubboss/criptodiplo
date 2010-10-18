<?php
require_once '../clases/Login.php';
Login::redireccionarUsuarios(Login::existeUsuarioActivo(),-1);
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
        <script type="text/javascript" src="../js/registrarUsuario.js"></script>
        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">
            <div id="ccajaRegistro">

                <h1>Diplomado en Criptograf&iacute;a</h1>

                <form id="formNuevoUsuario" method="get" action="#">

                    <h2 id="nuevoUsuario">Registro de usuario</h2>

                    <p>
                        <label>Usuario:</label>
                        <input type="text" tabindex="1"  name="usuario" id="usuario" />
                    </p>

                    <p>
                        <label>Contrase&ntilde;a:</label>
                        <input type="password" tabindex="2" name="contrasena" id="contrasena"  />
                    </p>
                    <p>
                        <label>Verifique la contrase&ntilde;a:</label>
                        <input type="password" tabindex="2" name="contrasena2" id="contrasena2"  />
                    </p>
                    <p>
                        <label>Nombres:</label>
                        <input type="text" tabindex="3"   name="nombres" id="nombres"/>
                    </p>
                    <p>
                        <label>Apellidos:</label>
                        <input type="text" tabindex="4"  name="apellidos" id="apellidos"/>
                    </p>
                    <p>
                        <label>Correo electr&oacute;nico:</label>
                        <input type="text" tabindex="5"  name="correo_electronico" id="correo_electronico" />
                    </p>
                    <p>
                        <label></label>
                        <input type="submit" id="registrarUsuario" value="Registrar"/>
                    </p>


                    <div id="respuesta"></div>
                    <noscript class="mensajeNoScript">
                        <div>
                            <span class="error">La p&aacute;gina que est&aacute;s viendo requiere para su funcionamiento el uso de JavaScript.
                                Si lo has deshabilitado intencionadamente, por favor vuelve a activarlo.</span>
                        </div>
                    </noscript>
                </form>

                <p class="centrado">Ya tienes una cuenta? <a href="login.php" id="inicioP">Inicia sesi&oacute;n ahora.</a></p>

            </div>
        </div>
    </body>
</html>
