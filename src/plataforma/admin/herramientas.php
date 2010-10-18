<?php
if (isset ($_POST['op']) && isset ($_POST['herramienta'])) {

    $opcion = $_POST['op'];
    $herramienta = $_POST['herramienta'];

    if($opcion == "cargar") {
        cargarHerramienta($herramienta);
    }
}

function cargarHerramienta($herramienta) {
    switch($herramienta) {
        case 'verEstadoUsuarios': {
                require_once '../admin/verEstadoUsuarios.php';
                formularioVerEstadoUsuarios();
                break;
            }
        case 'agregarTexto': {
                require_once '../admin/agregarTexto.php';
                formularioCargarTexto();
                break;
            }
        case 'cambiarEstadoActividad': {
                require_once '../admin/cambiarEstadoActividad.php';
                formularioCambiarEstadoActividad();
                break;
            }
        case 'cambiarEstadoSeccion': {
                require_once '../admin/cambiarEstadoSeccion.php';
                formularioCambiarEstadoSeccion();
                break;
            }
        case 'verEstadoBDTextos': {
                require_once '../admin/verNumeroTextos.php';
                formularioVerNumeroTextos();
                break;
            }
        case 'verTexto': {
                require_once '../admin/verTexto.php';
                formularioVerTexto();
                break;
            }
        case 'cambiarTexto': {
                require_once '../admin/cambiarTexto.php';
                formularioCambiarTexto();
                break;
            }
        case 'agregarRecurso': {
                require_once '../admin/agregarRecurso.php';
                formularioAgregarRecurso();
                break;
            }
    }
}

?>
