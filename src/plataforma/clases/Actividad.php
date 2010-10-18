<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Actividad
 *
 * @author Cristian
 */
class Actividad {

    private $id_actividad;
    private $id_usuario;
    private $id_texto;
    private $puede_entrar;
    private $Texto;
    private $intentos;
    private $iniciada;
    private $fecha_inicio;
    private $fecha_fin;
    private $estado;
    private $nombre;
    private $id_metodo;
    private $forma_respuesta;
    private $respuesta;
    private $recursos;
    private $mostrarTextoCifrado;
    private $mostrarClave;
    private $informacion_adicional;

    /**
     * Contiene los datos de una actividad asociada a un usuario
     * @param int $id_usuario Identificador de usuario
     * @param int $id_actividad Identificador del numero de la actividad
     */
    public function __construct($id_usuario, $id_actividad) {
        $this->id_actividad = $id_actividad;
        $this->id_usuario = $id_usuario;
    }

    /**
     * Carga la actividad con base a el id de usuario y al id de la actividad previamente cargados
     */
    public function cargarActividad() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT id_texto,intentos,iniciada,fecha_inicio,fecha_fin
                FROM actividades_por_usuario WHERE id_usuario=" . DB::limpiarSQL($this->id_usuario) . "
                    AND id_actividad=" . DB::limpiarSQL($this->id_actividad);

        $actividadSQL = $db->consulta($consulta);
        if ($actividadSQL) {
            $actividad = mysql_fetch_object($actividadSQL);
            $this->puede_entrar = false;
            if ($actividad) {
                $this->intentos = $actividad->intentos;
                $this->fecha_inicio = $actividad->fecha_inicio;
                $this->fecha_fin = $actividad->fecha_fin;
                $this->iniciada = $actividad->iniciada;
                $this->id_texto = $actividad->id_texto;
            } else {
                $this->fecha_inicio = "--";
                $this->fecha_fin = "--";
            }
            $this->cargarDatosInterfaz();
            $this->estado = $this->calcularEstado();
            if ($this->estado)
                $this->puede_entrar = true;

            mysql_free_result($actividadSQL);
            return true;
        }
        return false;
    }

    public function terminarActividad() {
        $terminarActividad = array(
            "fecha_fin" => time()
        );
        $condicion = "id_usuario=" . DB::limpiarSQL($this->id_usuario) . " AND id_actividad='" . DB::limpiarSQL($this->id_actividad) . "'";

        $db = new DB();
        $db->conectar();
        if ($db->actualizarArreglo($terminarActividad, "actividades_por_usuario", $condicion)) {
            return true;
        }
        return false;
    }

    private function cargarDatosInterfaz() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT nombre,mostrar_texto_cifrado,id_metodo,tipo_respuesta,forma_respuesta,mostrar_clave FROM actividades WHERE id_actividad =" . DB::limpiarSQL($this->id_actividad);
        $datosActividadInterfazSQL = $db->consulta($consulta);
        $datosActividadInterfaz = mysql_fetch_object($datosActividadInterfazSQL);

        mysql_free_result($datosActividadInterfazSQL);


        if ($datosActividadInterfaz) {

            $this->nombre = $datosActividadInterfaz->nombre;
            $this->mostrarTextoCifrado = $datosActividadInterfaz->mostrar_texto_cifrado;
            $this->mostrarClave = $datosActividadInterfaz->mostrar_clave;
            $this->id_metodo = $datosActividadInterfaz->id_metodo;
            $this->respuesta = $datosActividadInterfaz->tipo_respuesta;
            $this->forma_respuesta = $datosActividadInterfaz->forma_respuesta;

            $consulta = "SELECT informacion_adicional FROM metodos WHERE id_metodo = " . DB::limpiarSQL($this->id_metodo);
            $informacionAdicionalSQL = $db->consulta($consulta);
            $informacionAdicional = mysql_fetch_object($informacionAdicionalSQL);
            $this->informacion_adicional = $informacionAdicional->informacion_adicional;

            $db->desconectar();
            return $datosActividadInterfaz->forma_respuesta;
        }

        $db->desconectar();
        return false;
    }

    /**
     * Calcula el estado de una actividad dependiendo de si esta habilitada, comenzada o terminada
     * @return int El estado de la actividad (0: Deshabilitada, 1: Habilitada sin iniciar,
     * 2: Habilitada iniciada, 3: Habilitada terminada)
     */
    private function calcularEstado() {
        $estado = 0;
        if ($this->estaHabilitada()) {
            if ($this->fecha_inicio == "--") {
                $estado = 1;
            } else {
                if ($this->fecha_fin == "--") {
                    $estado = 2;
                } else {
                    $estado = 3;
                }
            }
        } else {
            $estado = 0;
        }
        return $estado;
    }

    /**
     * Verifica si una actividad esta habilitada o no
     * @return boolean TRUE si esta habitliada, FALSE si no
     */
    private function estaHabilitada() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT habilitada FROM actividades WHERE id_actividad =" . DB::limpiarSQL($this->id_actividad);
        $actividadHabilitadaSQL = $db->consulta($consulta);
        $actividadHabilitada = mysql_fetch_object($actividadHabilitadaSQL);

        mysql_free_result($actividadHabilitadaSQL);
        $db->desconectar();

        if ($actividadHabilitada) {
            return $actividadHabilitada->habilitada;
        }
        return false;
    }

    /**
     * Inicia una actividad, asociando un usuario con un texto y una actividad.
     * Tambien se deshabiltia el texto para que otro usuario no lo use
     * @return boolean TRUE si se pudo registrar la actividad, FALSE si no
     */
    public function iniciarActividad() {
        require_once 'DB.php';

        $id_texto = $this->generarTexto();
        $actividadNueva = array(
            "id_usuario" => $this->id_usuario,
            "id_actividad" => $this->id_actividad,
            "id_texto" => $id_texto,
            "iniciada" => 1,
            "fecha_inicio" => time(),
            "fecha_fin" => "--"
        );

        $textoAsignado = array(
            "asignado" => 1
        );
        $condicion = "id_texto=" . DB::limpiarSQL($id_texto);

        $db = new DB();
        $db->conectar();

        if ($db->insertarArreglo("actividades_por_usuario", $actividadNueva) &&
                $db->actualizarArreglo($textoAsignado, "textos", $condicion)) {
            $db->desconectar();
            return true;
        }
        return false;
    }

    /**
     * Selecciona un texto aleatoriamente segunel metodo de la actividad actual
     * @return int Id del texto o FALSE si no pudo escoger unt exto
     */
    private function generarTexto() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        if ($this->id_metodo == 100) {
            $consulta = "SELECT id_texto FROM textos WHERE asignado=0 AND id_metodo<6 ORDER BY RAND() LIMIT 1";
        } else {
            $consulta = "SELECT id_texto FROM textos WHERE asignado=0 AND id_metodo=" . DB::limpiarSQL($this->id_metodo) . " ORDER BY RAND() LIMIT 1";
        }


        $id_textoSQL = $db->consulta($consulta);
        $id_texto = mysql_fetch_object($id_textoSQL);

        $db->desconectar();
        if ($id_textoSQL) {
            return $id_texto->id_texto;
        }
        return false;
    }

    public function cargarRecursos() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $recursos = Array();

        $consulta = "SELECT nombres_mostrar,id_recursos FROM recursos WHERE id_metodo=" . DB::limpiarSQL($this->id_metodo);
        $recursosSQL = $db->consulta($consulta);
        $indice = 0;
        while ($recurso = mysql_fetch_object($recursosSQL)) {
            $recursos[$indice] = $recurso;
            $indice++;
        }

        $this->recursos = $recursos;
    }

    public function cargarTextoAsignado() {
        require_once 'Texto.php';
        $this->Texto = new Texto($this->id_texto);
        $this->Texto->cargarTexto();
    }

    public function getFechaInicio($formatoUnix=false) {
        if ($formatoUnix)
            return $this->fecha_inicio;
        if ($this->fecha_inicio == '--')
            return $this->fecha_inicio;
        return date('d/m/Y h:i A', $this->fecha_inicio);
    }

    public function getFechaFin($formatoUnix=false) {
        if ($formatoUnix)
            return $this->fecha_fin;
        if ($this->fecha_fin == '--')
            return $this->fecha_fin;
        return date('d/m/Y h:i A', $this->fecha_fin);
    }

    public function getEstado() {
        return $this->estado;
    }

    public function getNombre() {
        return $this->nombre;
    }

    public function getPuedeEntrar() {
        return $this->puede_entrar;
    }

    public function getIdActividad() {
        return $this->id_actividad;
    }

    public function getIdMetodo() {
        return $this->id_metodo;
    }

    public function getTexto() {
        return $this->Texto;
    }

    public function getFormaRespuesta() {
        return html_entity_decode($this->forma_respuesta, ENT_COMPAT, 'UTF-8');
    }

    public function getRespuesta() {
        return $this->respuesta;
    }

    public function getRecursos() {
        return $this->recursos;
    }

    public function getMostrarTextoCifrado() {
        return $this->mostrarTextoCifrado;
    }

    public function getMostrarClave() {
        return $this->mostrarClave;
    }

    public function getInformacionAdicional() {
        return $this->informacion_adicional;
    }

    public function getIdTexto() {
        return $this->id_texto;
    }

}

?>
