-- phpMyAdmin SQL Dump
-- version 3.1.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 23, 2011 at 12:11 AM
-- Server version: 5.0.51
-- PHP Version: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `criptografia`
--

-- --------------------------------------------------------

--
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
CREATE TABLE IF NOT EXISTS `actividades` (
  `id_actividad` int(10) unsigned NOT NULL auto_increment,
  `habilitada` tinyint(1) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `id_metodo` int(10) unsigned NOT NULL,
  `forma_respuesta` text NOT NULL,
  `tipo_respuesta` int(10) unsigned NOT NULL,
  `mostrar_texto_cifrado` tinyint(1) unsigned NOT NULL,
  `mostrar_clave` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id_actividad`),
  KEY `FK_actividades_metodo` (`id_metodo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `actividades`
--

INSERT INTO `actividades` (`id_actividad`, `habilitada`, `nombre`, `id_metodo`, `forma_respuesta`, `tipo_respuesta`, `mostrar_texto_cifrado`, `mostrar_clave`) VALUES
(1, 1, 'Desplazamiento I', 1, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt;  Ingrese la clave con la cual el alfabeto fue desplazado. ', 3, 1, 0),
(2, 1, 'Desplazamiento II', 1, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt;  Ingrese la clave con la cual el alfabeto fue desplazado. ', 3, 1, 0),
(3, 1, 'Sustituci&oacute;n', 2, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt;  Ingrese el alfabeto sin separadores entre caracteres y todo en may&amp;uacute;scula. Por ejemplo: ABCDEFGHIJKLMNOPQRSTUVWXYZ', 3, 1, 0),
(4, 1, 'Affine', 3, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt; Ingrese el A y el B de la clave separados por una coma. Por ejemplo: 1,1 o 10,10', 3, 1, 0),
(5, 1, 'Vigenere', 4, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt;  Ingrese las 8 letras de la palabra clave en may&amp;uacute;scula. Por ejemplo: ABCDEFGH', 3, 1, 0),
(6, 0, 'Hill', 5, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt;   Sí la matriz clave es la matriz A (en rojo), ingrésela como se muestra en el recuadro azul de la imagen. Separando las filas con punto y coma y los elementos de la fila con coma.&lt;br/&gt; &lt;p class=&quot;centrado&quot;&gt;&lt;img src=&quot;../css/imagenes/hillEjemplo.png&quot; alt=&quot;Ejemplo Clave Hill&quot;&gt;&lt;/p&gt;', 3, 1, 0),
(7, 0, 'Permutaci&oacute;n', 6, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt; Ingrese el texto plano en min&amp;uacute;scula y sin espacios y pulse el bot&amp;oacute;n', 2, 1, 0),
(8, 0, 'Cl&aacute;sicos I', 100, 'Objetivo: Atacar &lt;br /&gt; &lt;br /&gt; Ingrese la clave del texto cifrado teniendo en cuenta las anteriores actividades', 3, 1, 0),
(9, 0, 'DES-Simplificado', 7, 'Objetivo: Atacar-Descifrar &lt;br /&gt; &lt;br /&gt;  Ingrese los 10 bits de la clave y el texto plano separados una coma. Por ejemplo 1010101010,PlAnO', 4, 1, 0),
(10, 0, 'Triple DES-Simplificado', 8, 'Objetivo: Descifrar &lt;br /&gt; &lt;br /&gt; Ingrese el texto plano. Por ejemplo: CrYpT', 2, 1, 1),
(11, 0, 'DES', 9, 'Objetivo: Descifrar &lt;br /