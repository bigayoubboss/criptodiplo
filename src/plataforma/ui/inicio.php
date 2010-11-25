<?php
require_once '../clases/Login.php';
require_once '../uicontrolador/Inicio.php';

$usuarioActivo = Login::redireccionarUsuarios(Login::existeUsuarioActivo(), 0);
if ($usuarioActivo) {
    require_once '../clases/Perfil.php';
    if (!isset($_SESSION)) {
        session_start();
    }

    $perfil = new Perfil($_SESSION['usuario']);
    $perfil->cargarPerfil();

    $actividades = $perfil->getActividades();
    $usuario = $perfil->getUsuario();
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
        <script type="text/javascript" src="../js/inicio.js"></script>
        <!--[if lt IE 7.]>
           <script defer type="text/javascript" src="../js/pngfix.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="contenido">

            <div id="cajaPerfil">
                <h1 id="tituloLogin">Perfil de usuario</h1>

                <div id="enlaces">
                    <a href="material.php" id="materialP">Material</a>
                    <a href="" id="logout">Cerrar sesi&oacute;n</a>
                </div>

                <div id="listaActividades"  class="caja">

                    <h2 id="actividades">Lista de actividades</h2>

                    <table id="tablaActividades">
                        <thead>
                            <th>#</th>
                            <th>Actividad</th>
                            <th>Estado</th>
                            <th>Fecha inicio</th>
                            <th>Fecha fin</th>
                            <th>Entrar</th>
                        </thead>
                        <tbody>
<?php
if ($usuarioActivo) {
    imprimirListaActividades($actividades);
}
?>
                        </tbody>
                        <tfoot>
                            <td colspan="3"><strong>Terminadas: </strong><?php echo $perfil->getActividadesTerminadas() . " de " . $perfil->getNumeroActividades(); ?>  </td>
                            <td colspan="3"><strong>Tiempo: </strong><?php echo $perfil->getTiempoTotal(); ?></td>
                        </tfoot>
                    </table>
                    <noscript class="mensajeNoScript">
                        <div>
                            <span class="error">La p&aacute;gina que est&aacute;s viendo requiere para su funcionamiento el uso de JavaScript.
                                Si lo has deshabilitado intencionadamente, por favor vuelve a activarlo.</span>
                        </div>
                    </noscript>
                </div>

                <div id="informacionDeUsuario"  class="caja">

                    <h2 id="usuarioPerfil">Informaci&oacute;n de usuario</h2>
                    <p>
                        <img src="../css/imagenes/usuarioActivo.png" alt="usuario" /><strong>Nombres y apellidos: </strong><?php echo $usuario->getNombres() . " " . $usuario->getApellidos(); ?>
                    </p>
                    <p>
                        <img src="../css/imagenes/usuario.png" alt="usuario" /><strong>Nombre de usuario: </strong><?php echo $usuario->getUsuario(); ?>
                    </p>
                    <p>
                        <img src="../css/imagenes/correoElectronico.png" alt="correo electronico" /><strong>Correo electr&oacute;nico: </strong><?php echo $usuario->getCorreoElectronico(); ?><br />
                    </p>
                    <p>
                        <img src="../css/imagenes/calenadrio.png" alt="ultimo acceso" /><strong>Ultimo acceso: </strong><?php echo $usuario->getUltimoAcceso(); ?><br />
                    </p>
                </div>

                <div id="contactoDudas"  class="caja">
                    <h2 id="ayuda">Ayuda</h2>
                    <ul>
<?php imprimirListaAdministradores(); ?>
                    </ul>
                    <br />

                </div>
            </div>
        </div>
    </body>
</html>

