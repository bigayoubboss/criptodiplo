<?php

if (isset($_GET['act'])) {
    require_once '../clases/Actividad.php';

    if (!isset($_SESSION)) {
        session_start();
    }
    $id_usuario = $_SESSION['idUsuario'];

    $actividad = new Actividad($id_usuario, $_GET['act']);
    $actividad->cargarActividad(true);

    switch ($actividad->getEstado()) {
        case 0:
            header('Location: ../ui/inicio.php');
            break;
        case 1:
            $actividad = comenzarActividad($actividad);
        case 2:
        case 3:
            $texto = cargarActividad($actividad);
            break;
    }
}

if (isset($_POST['op'])) {
    switch ($_POST['op']) {
        case "completarActividad": {
                if (isset($_POST['clave']) && isset($_POST['idActividad'])) {
                    if (!isset($_SESSION)) {
                        session_start();
                    }

                    $id_usuario = $_SESSION['idUsuario'];
                    $id_actividad = $_POST['idActividad'];
                    $clave = $_POST['clave'];

                    require_once '../clases/Actividad.php';
                    $actividad = new Actividad($id_usuario, $id_actividad);
                    $actividad->cargarActividad(true);
                    $actividad->cargarTextoAsignado();

                    $texto = $actividad->getTexto();
                    $respuesta = $actividad->getRespuesta();
                    switch ($respuesta) {
                        case 1:
                            $claveValida = $texto->getTextoCifrado();
                            break;
                        case 2:
                            $claveValida = $texto->getTextoPlano();
                            break;
                        case 3:
                            $claveValida = $texto->getClave();
                            break;
                        case 4:
                            $claveValida = $texto->getClave() . "," . $texto->getTextoPlano();
                            break;
                    }

                    $actividad->registrarIntentoActividad();
                    
                    $clave = DB::limpiarSQL($clave);

                    if ($clave == $claveValida) {
                        $actividad->terminarActividad();
                        echo "true";
                    } else {
                        echo "<span class='error'>Respuesta equivocada, intentalo nuevamente</span>";
                    }
                }
            }
            break;
    }
}

function comenzarActividad($actividad) {
    require_once '../clases/Actividad.php';
    if ($actividad->iniciarActividad()) {
        $actividad->cargarActividad(true);
        return $actividad;
    } else {
        echo "<span class='error'>Ocurrio un error comuniquese con el administrador del sistema</span>";
        return false;
    }
}

function cargarActividad($actividad) {
    $actividad->cargarTextoAsignado();
    return $actividad->getTexto();
}

function mostrarTexto() {
    echo "terminada";
}

?>
