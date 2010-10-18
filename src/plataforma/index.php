<?php
require_once 'clases/Login.php';

echo $_SERVER['PHP_SELF'];
Login::redireccionarUsuarios(Login::existeUsuarioActivo(),-2);
?>
