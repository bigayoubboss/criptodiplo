-- phpMyAdmin SQL Dump
-- version 3.1.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 24, 2011 at 11:28 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `criptografia`
--

-- --------------------------------------------------------

--
-- Table structure for table `recursos`
--

DROP TABLE IF EXISTS `recursos`;
CREATE TABLE IF NOT EXISTS `recursos` (
  `id_recursos` int(10) unsigned NOT NULL auto_increment,
  `nombres_mostrar` varchar(150) NOT NULL,
  `enlace` varchar(300) NOT NULL,
  `id_metodo` int(10) unsigned NOT NULL,
  `requisitos` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id_recursos`),
  KEY `FK_actividades_recursos_1` (`id_metodo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `recursos`
--

INSERT DELAYED INTO `recursos` (`id_recursos`, `nombres_mostrar`, `enlace`, `id_metodo`, `requisitos`) VALUES
(1, 'Cifrado C&eacute;sar (Wikipedia)', 'http://es.wikipedia.org/wiki/Cifrado_C%C3%A9sar', 1, 0),
(2, 'ROT13  (Wikipedia)', 'http://en.wikipedia.org/wiki/ROT13', 1, 0),
(3, 'Cifrado por sustituci&oacute;n  (Wikipedia)', 'http://en.wikipedia.org/wiki/Substitution_cipher', 2, 0),
(4, 'Alfabeto 189 car&aacute;cteres', '../recursos/alfabeto189.pdf', 1, 0),
(5, 'Criptex - M&oacute;dulo sustituci&oacute;n', '../recursos/criptex/CriptexM1.exe', 2, 1),
(6, 'Cifradores Cl&aacute;sicos', '../recursos/slides/diplomado_01.ppt', 1000, 0),
(7, 'Script para ejecutar Criptex sobre Linux', '../recursos/ejecutar.sh', 2000, 0),
(8, 'Manual para la ejecuci&oacute;n de Criptex sobre Linux', '../recursos/manual_ejecutar.pdf', 2000, 0),
(9, 'D. Stinson, <i>Cryptography: Theory and Practice, (Discrete Mathematics and Its Applications)</i>, Chapman & Hall/CRC, 2005', 'http://www.amazon.com/Cryptography-Practice-Discrete-Mathematics-Applications/dp/0849385210', 3000, 0),
(10, 'J. Buchmann, <i>Introduction to Cryptography</i>, Springer,2004', 'http://www.amazon.com/Introduction-Cryptography-Johannes-Buchmann/dp/0387950346', 3000, 0),
(11, 'A. Menezes, <i>P. van Oorschot, and S. Vanstone, Handbook of Applied Cryptography</i>, CRC Press, 1997', 'http://www.amazon.com/Handbook-Cryptography-Discrete-Mathematics-Applications/dp/0849385237/ref=sr_1_1?ie=UTF8&s=books&qid=1264785957&sr=1-1', 3000, 0),
(12, 'M. Lucena, <i>Criptograf&iacute;a y Seguridad en Computadores</i>', 'http://wwwdi.ujaen.es/~mlucena/wiki/pmwiki.php?n=Main.LCripto', 3000, 0),
(13, 'Frecuencia caracteres - Ingl&amp;eacute;s', '../recursos/lista_de_frecuencia_ingles.pdf', 2, 0),
(14, 'Frecuencia caracteres - Espa&amp;ntilde;ol', '../recursos/lista_de_frecuencia_espanol.pdf', 2, 0),
(15, 'Frecuencia caracteres - Franc&eacute;s', '../recursos/lista_de_frecuencia_frances.pdf', 2, 0),
(16, 'Frecuencia caracteres - Alem&amp;aacute;n', '../recursos/lista_de_frecuencia_aleman.pdf', 2, 0),
(17, 'Frecuencia caracteres - Portugu&amp;eacute;s', '../recursos/lista_de_frecuencia_portugues.pdf', 2, 0),
(18, 'El alfabeto de definici&amp;oacute;n - Los conjuntos Zn - Sistema de desplazamiento con MatLab', '../recursos/slides/diplomado_02.pdf', 1000, 0),
(19, 'Otros cifradores cl&amp;aacute;sicos - El sistema af&amp;iacute;n en MatLab', '../recursos/slides/diplomado_03.pdf', 1000, 0),
(20, 'Alfabeto 189 caracteres', '../recursos/alfabeto189.pdf', 2000, 0),
(21, 'Otros cifradores polialfab&amp;eacute;ticos - El sistema de Hill con MatLab - Ataque al sistema de Hill con MatLab', '../recursos/slides/diplomado_04.pdf', 1000, 0),
(22, 'Actividad de investigaci&amp;oacute;n', '../recursos/actividad_investigacion.txt', 2000, 0),
(23, 'Cifrador Affine (Wikipedia)', 'http://en.wikipedia.org/wiki/Affine_cipher', 3, 0),
(24, 'Breaking an affine cipher ', 'http://www.math.sunysb.edu/~scott/Book331/Affine_enciphering.html', 3, 0),
(25, 'Breaking a Vigenere Cipher', 'http://nob.cs.ucdavis.edu/classes/ecs155-2005-04/handouts/vig-sample.pdf', 4, 0),
(26, 'Vigenere Cipher (Wikipedia)', 'http://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher', 4, 0),
(28, 'Criptoan&amp;aacute;lisis del sistema criptogr&amp;aacute;fio de Vigenere', '../recursos/slides/diplomado_05.pdf', 1000, 0),
(29, 'Hill Cipher Cryptanalysis', 'http://practicalcryptography.com/ciphers/hill-cipher/', 5, 0),
(30, 'Hill Cipher', 'http://www.eg.bucknell.edu/~clg017/Cryptography.html', 5, 0),
(31, 'Hill Cipher (Wikipedia)', 'http://en.wikipedia.org/wiki/Hill_cipher', 5, 0),
(32, 'Criptex - M&amp;oacute;dulo Hill', '../recursos/criptex/CriptexM2.exe', 5, 1),
(33, 'Criptex - M&amp;oacute;dulo Permutaci&amp;oacute;n', '../recursos/criptex/CriptexM3.exe', 6, 1),
(36, 'DES Simplificado o S-DES', '../recursos/slides/diplomado_07.pdf', 1000, 0),
(38, 'El criptosistema &amp;Gamma;- Pentagonal - Evaluaci&amp;oacute;n de la seguridad de un Criptosistema y Teor&amp;iacute;a de Shannon - Cifradores en bl', '../recursos/slides/diplomado_06.pdf', 1000, 0),
(39, 'Funciones de ayuda para Maple', '../recursos/begin_proof.mw', 2000, 0),
(40, 'AES Demo', 'http://www.cs.bc.edu/~straubin/cs381-05/blockciphers/rijndael_ingles2004.swf', 11, 0),
(42, 'DES (DATA ENCRYPTION STANDARD) Itinerario de la clave DES Ejemplo DES Triple DES Modos de operaci&amp;oacute;́n DES Crack', '../recursos/slides/diplomado_08.pdf', 1000, 0),
(43, 'Logo McGraw Hill', '../recursos/mgh.ico', 2000, 0),
(44, 'Subkey Generation in SDES', 'http://nsfsecurity.pr.erau.edu/crypto/keygen.html', 7, 0),
(45, 'SDES Encryption Sample', 'http://homepage.smc.edu/morgan_david/vpn/assignments/assgt-sdes-encrypt-sample.htm', 7, 0);
