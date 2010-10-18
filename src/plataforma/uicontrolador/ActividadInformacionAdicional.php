<?php

$id_metodo = $actividad->getIdMetodo();

switch ($id_metodo) {
    case 2: informacionSustitucion($texto);
        break;
    case 5: informacionHill($texto);
        break;
    case 7: informacionDESS($texto);
        break;
    case 12: informacionRSA($texto);
        break;
    case 13: informacionElGamal($texto);
        break;
    default:
}

function informacionSustitucion($texto) {
    $posiciones = array(0, 3, 6, 10, 16, 17);

    for ($pista = 0; $pista < sizeof($posiciones); $pista++) {

        $cifrado = substr($texto->getClave(), $posiciones[$pista], 1);
        echo "<li>La <strong>" . strtolower(chr($posiciones[$pista] + 65)) . " </strong> se cifra con la <strong>" . $cifrado . "</strong></li>";
    }
}

function informacionHill($texto) {
    $longitudPista = 12;
    echo "<li>El texto plano <strong>" . substr($texto->getTextoPlano(), 0, $longitudPista) . "</strong> se cifra en <strong>" . substr($texto->getTextoCifrado(), 0, $longitudPista) . "</strong></li>";
}

function informacionDESS($texto) {
    echo "<li>Los 6 primeros bits de la clave son: <strong>" . substr($texto->getClave(), 0, 6) . "</strong></li>";
}

function informacionRSA($texto) {
    echo "<li> Se sabe que la <strong>clave p&uacute;blica</strong> es (n,e) = " . $texto->GetClave() . " </li>";
    echo "<li>Asuma un <strong>tama&ntilde;o de bloque de 24 bits </strong></li>";
}

function informacionElGamal($texto) {
    echo "<li> Se sabe que la <strong>clave</strong> es ( <i>p</i>, <i>generador</i>, <i>aleatorio</i> ) = " . $texto->GetClave() . " </li>";
    echo "<li>Asuma un <strong>tama&ntilde;o de bloque de 16 bits </strong></li>";
}

?>
