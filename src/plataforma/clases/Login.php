<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Login
 *
 * @author SirLock
 */
class Login {

    /**
     * Registra el ingreso de un usuario a la plataforma, generando un registro en la base de datos
     * y su respectiva sesion de php
     * @param String $usuario nombre de usuario
     * @return boolean TRUE si se pudo registrar el usuario, FALSE si no
     */
    public static function registrarIngreso($usuario) {
        require_once 'DB.php';
        require_once 'Usuario.php';

        $usuario = new Usuario($usuario);
        $id_usuario = $usuario->devolverIdUsuario();
        $cadenaAleatoria = Login::cadenaAleatoria();
        if ($id_usuario) {
            $login = array(
                "id_usuario" => $id_usuario,
                "no_cache" => $cadenaAleatoria,
                "fecha_ingreso" => time(),
                "activo" => 1
            );
            $db = new DB();
            $db->conectar();
            if ($db->insertarArreglo("login", $login)) {
                $db->desconectar();
                Login::registrarSesion($id_usuario, $cadenaAleatoria);
                return true;
            }
        }
        return false;
    }

    /**
     * Registra la salida del usuario activo en al base de datos, anotando su hora de salida
     * y marcandolo como no activo
     * @return boolean TRUE si se pudo hacer la actualziacion, FALSE si no
     */
    public static function registrarSalida() {
        require_once 'DB.php';

        session_start();
        $id_usuario = $_SESSION['usuario'];
        $aleatorio = $_SESSION['nocache'];

        $cierreSesion = array(
            "fecha_salida" => time(),
            "activo" => 0
        );
        $condicion = "id_usuario=" . DB::limpiarSQL($id_usuario) . " AND no_cache='" . DB::limpiarSQL($aleatorio) . "'";

        $db = new DB();
        $db->conectar();
        if ($db->actualizarArreglo($cierreSesion, "login", $condicion)) {
            session_destroy();
            return true;
        }
        return false;
    }

    /**
     * Genera una cadena aleatoria para el registro de la sesion
     * @return String cadena aleatoria
     */
    private static function cadenaAleatoria() {
        $tamaño = 64;
        $aleatorio = "";
        for ($i = 1; $i <= $tamaño; $i++) {
            srand((double) microtime() * 1000000);
            switch (rand(1, 3)) {
                case 1:
                    $caracter = rand(48, 57);
                    break;
                case 2:
                    $caracter = rand(65, 90);
                    break;
                case 3:
                    $caracter = rand(97, 122);
                    break;
            }

            $aleatorio .= chr($caracter);
        }
        return $aleatorio;
    }

    /**
     * Crea la sesion de PHP con la cadena aleatoria y el id del usuario
     * @param <type> $id_usuario
     * @param <type> $no_cache
     */
    private static function registrarSesion($id_usuario, $no_cache) {
        session_start();
        $_SESSION['usuario'] = $id_usuario;
        $_SESSION['nocache'] = $no_cache;
    }

    /**
     * Verifica si existe alguna sesion valida para el sistema
     * @return int -1: Si no existe sesion activa, 0: si existe una sesion de administrador activa,
     * 1: si existe una sesion de usurio activa
     */
    public static function existeUsuarioActivo() {
        session_start();
        if (isset($_SESSION['nocache']) && isset($_SESSION['usuario'])) {
            require_once 'DB.php';

            $db = new DB();
            $db->conectar();

            $consulta = "SELECT COUNT(*) FROM login WHERE id_usuario=" .
                    DB::limpiarSQL($_SESSION['usuario']) . " AND no_cache='" .
                    DB::limpiarSQL($_SESSION['nocache']) . "' AND activo=1";
            $usuarioSQL = $db->consulta($consulta);
            $usuario = mysql_fetch_array($usuarioSQL);

            mysql_free_result($usuarioSQL);

            if ($usuario['COUNT(*)']) {

                $consulta = "SELECT administrador FROM usuarios WHERE id_usuario=" . DB::limpiarSQL($_SESSION['usuario']);

                $usuarioSQL = $db->consulta($consulta);
                $usuario = mysql_fetch_object($usuarioSQL);

                mysql_free_result($usuarioSQL);
                $db->desconectar();

                return $usuario->administrador;
            }
        }
        return -1;
    }

    /**
     * Redirecciona a un usuario dependiendo su rol
     * @param int $administrador Rol del usuario (-1: visitante, 0: usuario, 1:administrador)
     * @param int $tipo_archivo Permisos sobre el tipo de archivo (-2: Redireccion obligatoria, -1: visitante,
     * 0: usuario, 1: administrador)
     */
    public static function redireccionarUsuarios($administrador, $tipo_archivo) {
        if ($administrador != $tipo_archivo) {

            $origen = $_SERVER['PHP_SELF'];

            $destino = "";
            if (strpos($origen, "/ui/") || strpos($origen, "/uicontrolador/") || strpos($origen, "/clases/")) {
                $destino = "../";
            }

            switch ($administrador) {
                case 0: {
                        header('Location: ' . $destino . 'ui/inicio.php');
                        return true;
                    }
                case 1: {
                        header('Location: ' . $destino . 'ui/admin.php');
                        return true;
                    }
                case -1: {
                        header('Location: ' . $destino . 'ui/login.php');
                        return false;
                    }
            }
        }
        return true;
    }

    public static function loginHabilitado() {
        require_once 'DB.php';

        $db = new DB();
        $db->conectar();

        $consulta = "SELECT habilitada from secciones_habilitadas WHERE seccion='login'";

        $loginSQL = $db->consulta($consulta);
        $loginHabilitado = mysql_fetch_object($loginSQL);

        return $loginHabilitado->habilitada;
    }

}

?>
