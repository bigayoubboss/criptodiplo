<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/

/**
 * Description of Perfil
 *
 * @author Cristian
 */
class Perfil {
    private $id_usuario;
    private $usuario;
    private $tiempoTotal;
    private $actividadesTerminadas;
    private $numeroActividades;
    private $actividades;

    /**
     * Contiene las actividades asociadas a un usuario
     * @param int $id_usuario Identificador de usuario
     */
    public function  __construct($id_usuario) {
        $this->id_usuario = $id_usuario;
    }

    /**
     * Carga un perfil que contiene una lista de todas las actividades disponibles y
     * el estado de cada una con el usuario
     */
    public function cargarPerfil() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT id_actividad FROM actividades ORDER BY id_actividad ASC";
        $actividadesDisponiblesSQL = $db->consulta($consulta);
        $actividades = array();
        while ($actividadDisponible = mysql_fetch_object($actividadesDisponiblesSQL)) {

            require_once '../clases/Actividad.php';
            $actividad = new Actividad($this->id_usuario,$actividadDisponible->id_actividad);
            $actividad->cargarActividad();

            $actividades[$actividadDisponible->id_actividad] = $actividad;

        }
        $this->actividades = $actividades;
        $this->calcularTiempoTotal($actividades);
        $this->actividadesTerminadas = $this->contarActividadesTerminadas();
        $this->numeroActividades = sizeof($actividades);

        require_once 'Usuario.php';
        $usuario = new Usuario(Usuario::devolverUsuario($this->id_usuario));
        $usuario->cargarUsuario();

        $this->usuario = $usuario;
    }

    private function calcularTiempoTotal($actividades) {
        $this->tiempoTotal = 0;
        foreach ($actividades as $actividad) {
            if($actividad->getFechaInicio()!='' && $actividad->getFechaFin()!="--") {
                $this->tiempoTotal +=  $actividad->getFechaFin(true) - $actividad->getFechaInicio(true);
            }
        }
    }

    private function contarActividadesTerminadas() {
        require_once 'DB.php';
        $db = new DB();
        $db->conectar();

        $consulta = "SELECT COUNT(*) AS actividades FROM actividades_por_usuario WHERE fecha_inicio!='' AND fecha_fin!='--' AND id_usuario=".$this->id_usuario;
        $actividadesTerminadasSQL = $db->consulta($consulta);
        $actividadesTerminadas = mysql_fetch_object($actividadesTerminadasSQL);

        $db->desconectar();

        return $actividadesTerminadas->actividades;
    }

    public function getActividades() {
        return $this->actividades;
    }

    public function getUsuario() {
        return $this->usuario;
    }

    public function getActividadesTerminadas() {
        return $this->actividadesTerminadas;
    }

    public function getNumeroActividades() {
        return $this->numeroActividades;
    }

    public function getTiempoTotal() {

        $segundos = $this->tiempoTotal;

        $minutos = $segundos/60;
        $horas = floor($minutos/60);
        $dias = floor($horas/24);

        $segundos_60 = $segundos%60%60%60;
        $minutos_60 = $minutos%60;
        $horas_24 = $horas%24;


        if($segundos < 60) {
            $tiempoTotal = $segundos_60.' segundos';
        } elseif($segundos>60 && $segundos<3600) {
            $tiempoTotal = $minutos_60.' minutos y '.$segundos_60.' segundos';
        } elseif($segundos>3600 && $segundos<86400) {
            $tiempoTotal = $horas_24.' horas '.$minutos_60.' minutos y '.$segundos_60.' segundos';
        } else {
            $tiempoTotal = $dias.' d&iacute;as '.$horas_24.' horas '.$minutos_60.' minutos y '.$segundos_60.' segundos';
        }
        return $tiempoTotal;
    }

}
?>
