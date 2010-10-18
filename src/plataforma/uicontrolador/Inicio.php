
<?php

function puedeEntrar($puede_entrar, $id_actividad) {
    if ($puede_entrar) {
        $imagen = '<img src="../css/imagenes/entrar.png" alt="entrar" />';
        $enlace = '<a href="actividad.php?act=' . $id_actividad . '" >' . $imagen . '</a>';
        return $enlace;
    } else {
        $imagen = '<img src="../css/imagenes/noEntrar.png" alt="noEntrar" />';
        return $imagen;
    }
}

function imprimirListaActividades($actividades) {
    $num = 1;
    foreach ($actividades as $actividad) {
        echo "<tr>";
        echo "<td>" . $num . "</td>";
        echo "<td>" . $actividad->getNombre() . "</td>";
        echo "<td><img src='../css/imagenes/estado" . $actividad->getEstado() . ".png' alt='estado" . $actividad->getEstado() . "'/></td>";
        echo "<td>" . $actividad->getFechaInicio() . "</td>";
        echo "<td>" . $actividad->getFechaFin() . "</td>";
        echo "<td>" . puedeEntrar($actividad->getPuedeEntrar(), $actividad->getIdActividad()) . "</td>";
        echo"</tr>";
        $num++;
    }
}

function imprimirListaAdministradores() {
    require_once '../clases/DB.php';

    $db = new DB();
    $db->conectar();

    $consulta = "SELECT correo_electronico FROM usuarios WHERE administrador=1 ORDER BY correo_electronico";
    $administradoresSQL = $db->consulta($consulta);

    while ($administrador = mysql_fetch_object($administradoresSQL)) {
        $correo_electronico = str_replace("@", " [arroba] ", $administrador->correo_electronico);
        $correo_electronico = str_replace(".", " [punto] ", $correo_electronico);
        echo "<li>" . $correo_electronico . "</li>";
    }
}

?>
