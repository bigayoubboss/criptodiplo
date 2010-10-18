<?php

/**
 * This is a database class. Instance of
 * this class can store connection information.
 */
class DB {
    protected $dbUsuario = "criptografia";
    protected $dbContrasena = "mysql8000?";
    protected $dbNombre = "criptografia";
    protected $dbHost = "localhost";

    private $enlace;
    private $ultimoError;
    private $ultimaInstruccion;

    /**
     * Realiza la conexión con la base de datos pre establecida
     * @return boolean TRUE si se realizo la conexión, FALSE si no
     */
    public function conectar() {
        $this->enlace = mysql_connect($this->dbHost, $this->dbUsuario, $this->dbContrasena);
        if ($this->enlace) {
            if($this->seleccionarDB($this->dbNombre)) {
                return $this->enlace;
            }
        }
        $this->ultimoError = mysql_error();
        return false;
    }

    /**
     * Cierra la conexión
     * @return boolean TRUE si la conexión se cierra sin problemas, FALSE si no
     */
    public function desconectar() {
        return mysql_close($this->enlace);
    }

    /**
     * Selecciona una base de datos para trabajar con ella
     * @param string $db Base de datos a seleccionar
     * @return boolean TRUE si se selecciono la base de datos correctamente, FALSE si no
     */
    private function seleccionarDB($db) {
        if(mysql_select_db($db)) {
            return true;
        } else {
            $this->ultimoError = mysql_error();
            return false;
        }
    }

    /**
     * Inserta un arreglo de datos en la tabla de la base de datos especificada
     * @param string $tabla Tabla en donde se van a insertar los datos
     * @param array $datos Los datos a insertar en forma de arreglo de la forma columna=>valor
     * @return int 0 si no se pudo realizar la operacion, numero mayor a 0
     * correspondiente al id generado por al base de datos
     */
    public function insertarArreglo($tabla, $datos) {
        if (empty($datos) || !$this->existeTabla($tabla)) {
            $this->ultimoError = "No se especific&oacute; ning&uacute;n dato para realizar esta operaci&oacute;n";
            return false;
        }
        $columnas = "(";
        $valores = "(";
        foreach ($datos as $columna=>$valor) {
            $columnas .= $this->limpiarSQL($columna).",";
            if($this->existeColumna($columna, $tabla)) {
                $tipo = $this->tipoColumna($columna, $tabla);
                $valores .= $this->formatearParaInsertar($tipo, $valor);
            } else {
                $this->ultimoError = "La columna: ".$columna." no es una columna v&aacute;lida";
                return false;
            }
        }
        $columnas = rtrim($columnas, ',').')';
        $valores = rtrim($valores, ',').')';

        $insertar = "INSERT INTO $tabla $columnas VALUES $valores";

        return $this->insertar($insertar);
    }

    /**
     * Ejecuta una instruccion sql de tipo insert
     * @param string $insertar Instruccion SQL
     * @return int 0 si no se pudo realizar la operacion, numero mayor a 0
     * correspondiente al id generado por al base de datos
     */
    private function insertar($insertar) {
        $this->ultimaInstruccion = $insertar;

        $resutlado = mysql_query($insertar);
        if (!$resutlado) {
            $this->ultimoError=mysql_error();
            return false;
        }
        $idInsertado = mysql_insert_id();
        if ($idInsertado == 0) return true;
        else return $idInsertado;
    }

    /**
     * Actualiza un arreglo de datos en la tabla de la base de datos especificada
     * @param array $datos Los datos a insertar en forma de arreglo de la forma columna=>valor
     * @param string $tabla Tabla en donde se van a insertar los datos
     * @param string $condicion es la condicion para que el udpate se realize
     * @return int 0 si no se pudo realizar la operacion, numero mayor a 0
     * correspondiente al numero de columnas afectadas por la actualizacion
     */
    public function actualizarArreglo($datos, $tabla, $condicion) {
        if (empty($datos) || !$this->existeTabla($tabla) || empty($condicion)) {
            $this->ultimoError = "No se especific&oacute; ning&uacute;n dato para realizar esta operaci&oacute;n";
            return false;
        }
        $valores = "";
        foreach ($datos as $columna=>$valor) {

            $columna = $this->limpiarSQL($columna);

            if($this->existeColumna($columna, $tabla)) {
                $tipo = $this->tipoColumna($columna, $tabla);
                $valores .= $columna."=".$this->formatearParaInsertar($tipo, $valor);
            } else {
                $this->ultimoError = "La columna: ".$columna." no es una columna v&aacute;lida";
                return false;
            }
        }

        $valores = rtrim($valores, ',');

        $actualizar = "UPDATE $tabla SET $valores  WHERE $condicion";

        return $this->actualizar($actualizar);
    }

    /**
     * Ejecuta una instruccion sql de tipo update
     * @param string $actualizar Instruccion SQL
     * @return int 0 si no se pudo realizar la operacion, numero mayor a 0
     * correspondiente al numero de columnas afectadas por la actualizacion
     */
    private function actualizar($actualizar) {
        $this->ultimaInstruccion = $actualizar;

        $resutlado = mysql_query($actualizar);
        if (!$resutlado) {
            $this->ultimoError=mysql_error();
            return false;
        }
        $filasAfectadas = mysql_affected_rows();
        if ($filasAfectadas == 0) return true;
        else return $filasAfectadas;
    }

    /**
     * Le da el formato a un string de acuerdo al tipo de la columna en la cual
     * se vaya a insertar el dato
     * @param string $tipo Tipo de la columna en donde se va a insertar el dato
     * @param string $valor Dato a insertar
     * @return string Dato formateado
     */
    private function formatearParaInsertar($tipo,$valor) {
        if ($tipo=="string" || $tipo=="blob") {
            return '"'.$this->limpiarSQL($valor).'",';
        } else {
            if ($tipo == "int") {
                return $this->limpiarSQL($valor).',';
            }
        }
    }

    /**
     * Verifica la existencia de una columna
     * @param string $columna Columna sobre la cual se desea verificar su existencia
     * @param string $tabla Tabla a la que pertenece la columna
     * @return boolean TRUE si existe, FALSE si no
     */
    public function existeColumna($columna, $tabla) {
        $columna = $this->limpiarSQL($columna);
        $consulta = "SELECT ".$columna." FROM ".$tabla." LIMIT 0, 1 ";
        $resultado = mysql_query($consulta);

        if($resultado) {
            mysql_free_result($resultado);
            return true;
        }
        return false;
    }

    /**
     * Verifica la existencia de una tabla
     * @param string $tabla Tabla sobre la cual se desea conocer su existencia
     * @return boolean TRUE si existe, FALSE si no
     */
    public function existeTabla($tabla) {
        $tabla = $this->limpiarSQL($tabla);
        $consulta = "SELECT * FROM ".$tabla." LIMIT 0, 1 ";
        $resultado = mysql_query($consulta);

        if($resultado) {
            mysql_free_result($resultado);
            return true;
        }
        return false;

    }

    /**
     * Devuelve el tipo de una columna
     * @param string $columna Nombre de la columna
     * @param string $tabla Tabla a la que pertenece la columna
     * @return string Retorna el tipo de la columna o FALSE si no se puede determinar
     */
    public function tipoColumna($columna, $tabla) {
        $consulta =  "SELECT ".$columna." FROM ".$tabla." LIMIT 0, 1 ";
        $resultado = mysql_query($consulta);
        if(!$resultado) {
            $this->lastError=mysql_error();
            return false;
        }
        $tipo = mysql_field_type($resultado, 0);
        if($tipo) {
            mysql_free_result($resultado);
            return $tipo;
        } else {
            $this->ultimoError = "No se pudo determinar el tipo de la columna";
            mysql_free_result($resultado);
            return false;
        }
    }

    /**
     * Realiza una consulta MySQL
     * @param string $consulta consulta a ejecutar
     * @return result Resultado de la consulta o FALSE si ocurrio algun error
     */
    public function consulta($consulta) {
        $this->ultimaInstruccion = $consulta;

        $resultado = mysql_query($consulta);
        if($resultado) {
            return $resultado;
        }
        $this->ultimoError = mysql_error();
        return false;
    }

    public static function limpiarSQL($dato) {

        $dato = htmlentities($dato,ENT_COMPAT,'UTF-8');
        $dato = mysql_escape_string($dato);
        
        return $dato;
    }

    /**
     * Retorna el ultimo error generado por el servidor MySQL
     * @return String Ultimo error ocurrido
     */
    public function getUltimoError() {
        return $this->ultimoError;
    }

    public function getEnlace() {
        return $this->enlace;
    }
}
?>
