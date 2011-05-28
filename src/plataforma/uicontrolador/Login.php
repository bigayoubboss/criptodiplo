<?php

if (isset($_POST['op'])) {
    switch ($_POST['op']) {
        case "login": {
                if (isset($_POST['usuario']) && isset($_POST['contrasena'])) {
                    require_once '../clases/Login.php';
                    $login = iniciarSesion($_POST['usuario'], $_POST['contrasena']);
                    if ($login != 0) {
                        if ($login == 2) {
                            echo "true";
                        } else {
                            if ($login == 3) {
                                echo "<span class='error'>Su cuenta se encuentra bloqueada, para reactivarla comun&iacute;quese con los administradores del sistema</span>";
                            } else {
                                echo "<span class='error'>EL ingreso a la plataforma fue deshabilitado temporalmente</span>";
                            }
                        }
                    } else {
                        echo "<span class='error'>Usuario y/o contrase&ntilde;a inv&aacute;lidos</span>";
                    }
                }
            }
            break;
        case "logout": {
                require_once '../clases/Login.php';
                if (Login::registrarSalida()) {
                    echo "true";
                } else {
                    echo "false";
                }
            }
            break;
    }
}

function iniciarSesion($usuario, $contrasena) {
    require_once '../clases/Usuario.php';
    require_once '../clases/Login.php';

    $usuario = new Usuario($usuario);
    if ($usuario->cargarUsuario()) {
        if ($usuario->verificarEstado()) {
            if ($usuario->verificarContrasena($contrasena)) {

                if (Login::loginHabilitado() || $usuario->getAdministrador()) {
                    Login::registrarIngreso($usuario->getUsuario());
                    return 2;
                } else {
                    return 1;
                }
            }
        } else {
            return 3;
        }
    }
    return 0;
}

?>
