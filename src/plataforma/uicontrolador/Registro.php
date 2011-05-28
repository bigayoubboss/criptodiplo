<?php

if (isset($_POST['op'])) {
    switch ($_POST['op']) {
        case "registrarUsuario": {
                if (isset($_POST['usuario']) && isset($_POST['contrasena']) && isset($_POST['nombres']) && isset($_POST['apellidos']) && isset($_POST['correo_electronico'])) {
                    require_once '../clases/Usuario.php';
                    if (Usuario::registroHabilitado()) {
                        if (registrarUsuario($_POST['usuario'], $_POST['contrasena'], $_POST['nombres'], $_POST['apellidos'], $_POST['correo_electronico'])) {
                            echo "true";
                        } else {
                            echo "<span class='error'>Ocurrio un error al crear el usuario</span>";
                        }
                    } else {
                        echo "<span class='error'>El registro de usuarios se ha deshabilitado temporalmente</span>";
                    }
                }
            }
            break;
    }
}
if (isset($_GET['usuario'])) {
    if (comprobarUsuario($_GET['usuario'])) {
        echo 'true';
    } else {
        echo 'false';
    }
}

function registrarUsuario($nombre_usuario, $contrasena, $nombres, $appellidos, $correo_electronico) {
    require_once '../clases/Usuario.php';
    $usuario = new Usuario($nombre_usuario);

    $usuarioNuevo = array(
        "nombres" => $nombres,
        "apellidos" => $appellidos,
        "correo_electronico" => $correo_electronico,
        "contrasena" => $contrasena,
        "administrador" => 0,
        "fecha_creacion" => time(),
    );

    if ($usuario->registrarNuevoUsuario($usuarioNuevo)) {
        require_once 'Login.php';
        if (iniciarSesion($nombre_usuario, $contrasena)) {
            return true;
        }
    }
    return false;
}

function comprobarUsuario($usuario) {
    require_once '../clases/Usuario.php';
    $usuario = new Usuario($usuario);

    if ($usuario->existeUsuario()) {
        return false;
    }
    return true;
}

?>
