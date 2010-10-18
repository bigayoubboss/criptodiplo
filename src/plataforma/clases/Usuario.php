<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
class Usuario {

    private $id_usuario;
    private $nombres;
    private $apellidos;
    private $usuario;
    private $contrasena;
    private $correo_electronico;
    private $administrador;
    private $fecha_creacion;
    private $ultimo_acceso;

    /**
     * Crea un usuario
     * @param String $usuario Nick del usuario
     */
    public function __construct($usuario) {
        $this->usuario = $usuario;
    }

    public function __toString() {

    }

    /**
     * Carga los valores de un usuario existente
     * @return boolean TRUE si el usuario existe y sus datos se cargaron correctamente,
     * FALSE si el usuario no existe u ocurrio un error al cargar sus datos
     */
    public function cargarUsuario() {
        if ($this->existeUsuario()) {
            require_once 'DB.php';
            $db = new DB();
            $db->conectar();

            $consulta = "SELECT * FROM usuarios WHERE usuario='" . DB::limpiarSQL($this->usuario) . "'";
            $usuarioSQL = $db->consulta($consulta);
            $usuario = mysql_fetch_object($usuarioSQL);

            $this->id_usuario = $usuario->id_usuario;
            $this->nombres = $usuario->nombres;
            $this->apellidos = $usuario->apellidos;
            $this->contrasena = $usuario->contrasena;
            $this->correo_electronico = $usuario->correo_electronico;
            $this->administrador = $usuario->administrador;
            $this->fecha_creacion = $usuario->fecha_creacion;
            $this->ultimo_acceso = $this->ultimoAcceso();

            mysql_free_result($usuarioSQL);
            $db->desconectar();
            return true;
        }
        return false;
    }

    /**
     * Verifica si existe un usuario registrado con el nombre de usuario especificado
     * @return  boolean TRUE si  el nombre de usuario ha sido utilizado, FALSE si esta disponible
     */
    public function existeUsuario() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT COUNT(*) FROM usuarios WHERE usuario='" . DB::limpiarSQL($this->usuario) . "'";
        $usuarioSQL = $db->consulta($consulta);
        $usuario = mysql_fetch_array($usuarioSQL);

        mysql_free_result($usuarioSQL);
        $db->desconectar();

        if ($usuario['COUNT(*)'])
            return true;
        return false;
    }

    /**
     * Verifica si una contrasena dada coincide para el usuario previamente cargado
     * @param string $contrasena Contraseña a comparar
     * @return boolean TRUE si la contrasena es la misma que la alojada en
     * la base de datos, FALSE si no
     */
    public function verificarContrasena($contrasena) {
        require_once 'DB.php';

        $contrasena = DB::limpiarSQL($contrasena);
        if ($contrasena === $this->contrasena) {
            return true;
        }
        return false;
    }

    /**
     * Registra un usuario en la base de datos, con la condicion que el nombre
     * de usuario debe estar disponible
     * @param array $usuario Datos del usuario de la forma atributo=>valor
     * @return boolean TRUE si se pudo crear el usuario, FALSE si ocurrio un problema
     */
    public function registrarNuevoUsuario($usuario) {
        if (!$this->existeUsuario()) {
            require_once 'DB.php';
            $db = new DB();
            $db->conectar();

            $usuario['usuario'] = $this->usuario;
            if ($db->insertarArreglo("usuarios", $usuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el id de un suaurio previamente creado
     * @return int id del usuario si existe o o FALSE si no
     */
    public function devolverIdUsuario() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT id_usuario FROM usuarios WHERE usuario='" . DB::limpiarSQL($this->usuario) . "'";
        $idUsuarioSQL = $db->consulta($consulta);
        $id_usuario = mysql_fetch_array($idUsuarioSQL);

        mysql_free_result($idUsuarioSQL);
        $db->desconectar();

        if ($id_usuario['id_usuario'])
            return $id_usuario['id_usuario'];
        return false;
    }

    /**
     * Devuelve el nombre de usuario de un usuario segun su id
     * @return String id del usuario si existe o o FALSE si no
     */
    public static function devolverUsuario($id_usuario) {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT usuario FROM usuarios WHERE id_usuario=" . DB::limpiarSQL($id_usuario);
        $usuarioSQL = $db->consulta($consulta);
        $usuario = mysql_fetch_array($usuarioSQL);

        mysql_free_result($usuarioSQL);
        $db->desconectar();

        if ($usuario['usuario'])
            return $usuario['usuario'];
        return false;
    }

    private function ultimoAcceso() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT fecha_ingreso FROM criptografia.login
            WHERE id_usuario=" . $this->id_usuario . " ORDER BY fecha_ingreso DESC LIMIT 0,1";
        $ultimo_accesoSQL = $db->consulta($consulta);
        $ultimo_acceso = mysql_fetch_array($ultimo_accesoSQL);

        mysql_free_result($ultimo_accesoSQL);
        $db->desconectar();

        if ($ultimo_acceso['fecha_ingreso'])
            return $ultimo_acceso['fecha_ingreso'];
        return false;
    }

    public static function registroHabilitado() {
        require_once 'DB.php';

        $db = new DB();
        $db->conectar();

        $consulta = "SELECT habilitada from secciones_habilitadas WHERE seccion='registro'";

        $registroSQL = $db->consulta($consulta);
        $registroHabilitado = mysql_fetch_object($registroSQL);

        return $registroHabilitado->habilitada;
    }

    public function getNombres() {
        return $this->nombres;
    }

    public function getApellidos() {
        return $this->apellidos;
    }

    public function getUsuario() {
        return $this->usuario;
    }

    public function getCorreoElectronico() {
        $this->correo_electronico = str_replace("@", " [arroba] ", $this->correo_electronico);
        $this->correo_electronico = str_replace(".", " [punto] ", $this->correo_electronico);
        return $this->correo_electronico;
    }

    public function getAdministrador() {
        return $this->administrador;
    }

    public function getFechaCreacion() {
        return $this->fecha_creacion;
    }

    public function getUltimoAcceso() {

        return date('d/m/Y h:i A', $this->ultimo_acceso);
    }

}

?>
