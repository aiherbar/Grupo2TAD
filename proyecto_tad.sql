-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2018 a las 12:22:59
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.0.27

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
(19, 5, 1, 'No apto', '2018-05-17 08:30:00', 'Madrid'),
(20, 2, 2, 'Apto', '2018-05-15 17:00:00', 'Madrid'),
(21, 3, 3, 'No apto', '2018-05-18 14:00:00', 'Sevilla'),
(22, 9, 1, 'Apto', '2018-05-17 12:00:00', 'Sevilla'),
(23, 4, 2, 'No apto', '2018-05-14 12:30:00', 'Granada'),
(24, 10, 1, 'Apto', '2018-05-16 07:30:00', 'Sevilla'),
(25, 1, 2, 'Apto', '2018-05-15 12:00:00', 'Madrid'),
(26, 15, 1, 'No apto', '2018-05-16 14:30:00', 'Sevilla'),
(27, 14, 2, 'No apto', '2018-05-14 09:00:00', 'Sevilla'),
(28, 13, 3, 'No apto', '2018-05-17 16:30:00', 'Granada'),
(29, 12, 1, 'Apto', '2018-05-18 07:00:00', 'Granada'),
(30, 11, 2, 'No apto', '2018-05-14 16:00:00', 'Madrid'),
(31, 6, 2, '', '2018-05-16 16:30:00', 'Sevilla'),
(32, 7, 3, '', '0000-00-00 00:00:00', 'Sevilla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrevistado`
--

CREATE TABLE `entrevistado` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `entrevistado`
--

INSERT INTO `entrevistado` (`id`, `dni`, `nombre`) VALUES
(1, '98798234M', 'Juan Perez'),
(2, '54883355M', 'Julian Cano'),
(3, '56392048A', 'Jorge Rivera'),
(4, '83025378H', 'Marta Botella'),
(5, '83278200J', 'Laura Manrique'),
(6, '73829104B', 'Anabel Hernández'),
(7, '73829019V', 'Jose Sanz'),
(8, '10928395F', 'Andres Garcia-Montiel'),
(9, '2748029T', 'Amparo Jimenez'),
(10, '06445922B', 'Carlos Camino'),
(11, '49193805W', 'Carmen Varas'),
(12, '50639230S', 'Angeles Ezquerro'),
(13, '84660573B', 'Sebastian Canovas'),
(14, '49429938V', 'Sonia Estrada'),
(15, '79401302N', 'Alfredo Pino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrevistador`
--

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
(1, '4264632A', 'Julian Barrera', 'RRHH'),
(2, '98765437A', 'Antonio Marín', 'TIC'),
(3, '38274920N', 'Antonia Navarro', 'Ventas');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `entrevistado`
--
ALTER TABLE `entrevistado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `entrevistador`
--
ALTER TABLE `entrevistador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
