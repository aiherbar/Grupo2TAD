-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 14-05-2018 a las 11:01:01
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_tad`
--
CREATE DATABASE IF NOT EXISTS `proyecto_tad` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proyecto_tad`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrevista`
--

DROP TABLE IF EXISTS `entrevista`;
CREATE TABLE `entrevista` (
  `id` int(11) NOT NULL,
  `id_entrevistado` int(11) NOT NULL,
  `id_entrevistador` int(11) NOT NULL,
  `apto` varchar(10) NOT NULL COMMENT 'No apto;Apto',
  `fecha` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lugar` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `entrevista`
--

INSERT INTO `entrevista` (`id`, `id_entrevistado`, `id_entrevistador`, `apto`, `fecha`, `lugar`) VALUES
(1, 1, 1, '', '0000-00-00 00:00:00', 'Sevilla'),
(10, 2, 1, '', '0000-00-00 00:00:00', 'Madrid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrevistado`
--

DROP TABLE IF EXISTS `entrevistado`;
CREATE TABLE `entrevistado` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `entrevistado`
--

INSERT INTO `entrevistado` (`id`, `dni`, `nombre`) VALUES
(1, '98798234S', 'Juan Perez'),
(2, '54883355Z', 'Julian Cano');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrevistador`
--

DROP TABLE IF EXISTS `entrevistador`;
CREATE TABLE `entrevistador` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `departamento` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `entrevistador`
--

INSERT INTO `entrevistador` (`id`, `dni`, `nombre`, `departamento`) VALUES
(1, '11111111A', 'Julian Barrera', 'Matematicas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entrevista`
--
ALTER TABLE `entrevista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `entrevistado`
--
ALTER TABLE `entrevistado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `entrevistador`
--
ALTER TABLE `entrevistador`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entrevista`
--
ALTER TABLE `entrevista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `entrevistado`
--
ALTER TABLE `entrevistado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `entrevistador`
--
ALTER TABLE `entrevistador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
