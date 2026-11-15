-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2025 a las 18:42:38
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospitalh`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `IdAdmin` char(8) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`IdAdmin`, `Nombre`) VALUES
('ADMIN001', 'J. Perez'),
('ADMIN002', 'P. Solito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citamedica`
--

CREATE TABLE `citamedica` (
  `IDCita` char(15) NOT NULL,
  `Hora` time NOT NULL,
  `Fecha` date NOT NULL,
  `IDPaciente` char(10) NOT NULL,
  `IDDepartamento` char(5) NOT NULL,
  `Costo` decimal(5,2) NOT NULL,
  `IDDoctor` char(8) NOT NULL
) ;

--
-- Volcado de datos para la tabla `citamedica`
--

INSERT INTO `citamedica` (`IDCita`, `Hora`, `Fecha`, `IDPaciente`, `IDDepartamento`, `Costo`, `IDDoctor`) VALUES
('CITA00000000001', '13:00:00', '2025-05-28', 'PAC0000003', 'DEP07', 350.00, 'DOC00017'),
('CITA00000000002', '11:30:00', '2025-05-28', 'PAC0000016', 'DEP07', 400.00, 'DOC00017'),
('CITA00000000003', '12:00:00', '2025-04-03', 'PAC0000006', 'DEP03', 300.00, 'DOC00002'),
('CITA00000000004', '14:00:00', '2025-04-04', 'PAC0000011', 'DEP08', 450.00, 'DOC00008'),
('CITA00000000005', '08:15:00', '2025-04-05', 'PAC0000009', 'DEP02', 375.00, 'DOC00013'),
('CITA00000000006', '08:30:00', '2025-05-26', 'PAC0000002', 'DEP04', 390.00, 'DOC00001'),
('CITA00000000007', '11:30:00', '2025-04-07', 'PAC0000014', 'DEP06', 420.00, 'DOC00010'),
('CITA02266720752', '14:00:00', '2026-05-02', 'PAC0000016', 'DEP05', 345.00, 'DOC00005'),
('CITA03607879666', '14:00:00', '2025-05-02', 'PAC0000016', 'DEP03', 350.00, 'DOC00013'),
('CITA04725809062', '19:00:00', '2025-05-26', 'PAC0000016', 'DEP07', 350.00, 'DOC00017'),
('CITA05237086054', '11:30:00', '2025-06-27', 'PAC0000016', 'DEP08', 375.00, 'DOC00008');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compañia`
--

CREATE TABLE `compañia` (
  `IDCompañia` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compañia`
--

INSERT INTO `compañia` (`IDCompañia`, `Nombre`) VALUES
('COMP000001', 'SaludPlus'),
('COMP000002', 'MediCare'),
('COMP000003', 'VidaSegura'),
('COMP000004', 'ClinicAssure'),
('COMP000005', 'SeguroVital');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `IDDepartamento` char(5) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Piso` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`IDDepartamento`, `Descripcion`, `Piso`) VALUES
('DEP01', 'Emergencias', 1),
('DEP02', 'Cardiología', 2),
('DEP03', 'Neurología', 3),
('DEP04', 'Pediatría', 4),
('DEP05', 'Radiología', 5),
('DEP06', 'Oncología', 6),
('DEP07', 'Traumatología', 7),
('DEP08', 'Urología', 8),
('DEP09', 'Ginecología', 9),
('DEP10', 'Dermatología', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `IDDoctor` char(8) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `CURP` char(18) NOT NULL,
  `Cedula` varchar(8) NOT NULL,
  `RFC` varchar(13) NOT NULL,
  `AñosExperiencia` int(11) NOT NULL,
  `SueldoPorDia` decimal(8,2) NOT NULL,
  `IDEspecialidad` int(11) NOT NULL,
  `IDDepartamento` char(5) NOT NULL,
  `IDTurno` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`IDDoctor`, `Nombre`, `Telefono`, `CURP`, `Cedula`, `RFC`, `AñosExperiencia`, `SueldoPorDia`, `IDEspecialidad`, `IDDepartamento`, `IDTurno`) VALUES
('DOC00001', 'Juan Perez', '5553000001', 'PEPJ850101HDFABC01', 'CED00001', 'RFC0000000001', 10, 1500.00, 1, 'DEP01', 2),
('DOC00002', 'Maria Hernandez', '5553000002', 'HEHM900202MDFABC02', 'CED00002', 'RFC0000000002', 8, 1700.00, 2, 'DEP02', 3),
('DOC00003', 'Carlos Lopez', '5553000003', 'LOPC780303HDFABC03', 'CED00003', 'RFC0000000003', 15, 2000.00, 3, 'DEP03', 1),
('DOC00004', 'Ana Diaz', '5553000004', 'DIZA920404MDFABC04', 'CED00004', 'RFC0000000004', 5, 1300.00, 4, 'DEP04', 2),
('DOC00005', 'Luis Martinez', '5553000005', 'MALM830505HDFABC05', 'CED00005', 'RFC0000000005', 12, 1800.00, 5, 'DEP05', 4),
('DOC00006', 'Sofia Gomez', '5553000006', 'GOSF880606MDFABC06', 'CED00006', 'RFC0000000006', 7, 1600.00, 6, 'DEP06', 3),
('DOC00007', 'Diego Sanchez', '5553000007', 'SAND750707HDFABC07', 'CED00007', 'RFC0000000007', 20, 2500.00, 7, 'DEP07', 1),
('DOC00008', 'Elena Torres', '5553000008', 'TORE810808MDFABC08', 'CED00008', 'RFC0000000008', 9, 1400.00, 8, 'DEP08', 4),
('DOC00009', 'Miguel Ramirez', '5553000009', 'RAMM790909HDFABC09', 'CED00009', 'RFC0000000009', 14, 2100.00, 9, 'DEP09', 2),
('DOC00010', 'Laura Flores', '5553000010', 'FLOL920010MDFABC10', 'CED00010', 'RFC0000000010', 6, 1550.00, 10, 'DEP10', 3),
('DOC00011', 'Pablo Ruiz', '5553000011', 'RUIP860111HDFABC11', 'CED00011', 'RFC0000000011', 11, 1750.00, 11, 'DEP01', 1),
('DOC00012', 'Adriana Ortiz', '5553000012', 'ORTE880212MDFABC12', 'CED00012', 'RFC0000000012', 13, 1900.00, 12, 'DEP02', 2),
('DOC00013', 'Fernando Castro', '5553000013', 'CAST820313HDFABC13', 'CED00013', 'RFC0000000013', 16, 2300.00, 13, 'DEP03', 4),
('DOC00014', 'Carla Perez', '5553000014', 'PERC950414MDFABC14', 'CED00014', 'RFC0000000014', 7, 1650.00, 14, 'DEP04', 3),
('DOC00015', 'Ricardo Morales', '5553000015', 'MORR730515HDFABC15', 'CED00015', 'RFC0000000015', 18, 2400.00, 15, 'DEP05', 1),
('DOC00016', 'fasffsdf', '6666666', 'sfasf', 'asfa', '33', 33, 33.50, 15, 'DEP09', 2),
('DOC00017', 'Juan Perez 2', '4611234567', 'HELD051210HGTRNGA9', '12345678', 'MAHS991127643', 33, 3000.00, 10, 'DEP07', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermero`
--

CREATE TABLE `enfermero` (
  `IDEnfermero` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `CURP` char(18) NOT NULL,
  `Cedula` varchar(8) NOT NULL,
  `RFC` varchar(13) NOT NULL,
  `SueldoPorDia` decimal(8,2) DEFAULT NULL,
  `IDTurno` int(11) NOT NULL,
  `IDDepartamento` char(5) NOT NULL
) ;

--
-- Volcado de datos para la tabla `enfermero`
--

INSERT INTO `enfermero` (`IDEnfermero`, `Nombre`, `CURP`, `Cedula`, `RFC`, `SueldoPorDia`, `IDTurno`, `IDDepartamento`) VALUES
('ENF0000001', 'Lucía Mendoza', 'MELU901001MDFABC01', 'CENF0001', 'RFCENF000001', 950.00, 2, 'DEP01'),
('ENF0000002', 'Marco Ríos', 'RIMR850202HDFABC02', 'CENF0002', 'RFCENF000002', 980.00, 3, 'DEP02'),
('ENF0000003', 'Teresa Delgado', 'DELT870303MDFABC03', 'CENF0003', 'RFCENF000003', 910.00, 1, 'DEP03'),
('ENF0000004', 'Oscar Jiménez', 'JIJO840404HDFABC04', 'CENF0004', 'RFCENF000004', 970.00, 4, 'DEP04'),
('ENF0000005', 'Valeria Torres', 'TOVL930505MDFABC05', 'CENF0005', 'RFCENF000005', 920.00, 2, 'DEP05'),
('ENF0000006', 'Gabriel Herrera', 'HEHG790606HDFABC06', 'CENF0006', 'RFCENF000006', 990.00, 3, 'DEP06'),
('ENF0000007', 'Daniela Romero', 'RODA920707MDFABC07', 'CENF0007', 'RFCENF000007', 965.00, 1, 'DEP07'),
('ENF0000008', 'Andrés Morales', 'MOAN830808HDFABC08', 'CENF0008', 'RFCENF000008', 930.00, 4, 'DEP08'),
('ENF0000009', 'Samantha Ruiz', 'RUSA950909MDFABC09', 'CENF0009', 'RFCENF000009', 960.00, 2, 'DEP09'),
('ENF0000010', 'Javier Navarro', 'NAJA780101HDFABC10', 'CENF0010', 'RFCENF000010', 940.00, 3, 'DEP10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `IDEspecialidad` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`IDEspecialidad`, `Descripcion`) VALUES
(1, 'Cardiología'),
(2, 'Neurología'),
(3, 'Pediatría'),
(4, 'Dermatología'),
(5, 'Oncología'),
(6, 'Traumatología'),
(7, 'Urología'),
(8, 'Ginecología'),
(9, 'Radiología'),
(10, 'Anestesiología'),
(11, 'Psiquiatría'),
(12, 'Endocrinología'),
(13, 'Gastroenterología'),
(14, 'Neumología'),
(15, 'Reumatología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudia`
--

CREATE TABLE `estudia` (
  `IDDoctor` char(8) NOT NULL,
  `IDPaciente` char(10) NOT NULL,
  `IDEstudio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudia`
--

INSERT INTO `estudia` (`IDDoctor`, `IDPaciente`, `IDEstudio`) VALUES
('DOC00001', 'PAC0000001', 1),
('DOC00002', 'PAC0000003', 2),
('DOC00005', 'PAC0000006', 3),
('DOC00007', 'PAC0000005', 1),
('DOC00008', 'PAC0000008', 4),
('DOC00010', 'PAC0000016', 5),
('DOC00013', 'PAC0000012', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudio`
--

CREATE TABLE `estudio` (
  `IDEstudio` int(11) NOT NULL,
  `Descripcion` text NOT NULL,
  `Costo` decimal(8,2) NOT NULL
) ;

--
-- Volcado de datos para la tabla `estudio`
--

INSERT INTO `estudio` (`IDEstudio`, `Descripcion`, `Costo`) VALUES
(1, 'Análisis de sangre completo', 500.00),
(2, 'Radiografía de tórax', 350.00),
(3, 'Ultrasonido abdominal', 750.00),
(4, 'Electrocardiograma', 400.00),
(5, 'Tomografía computarizada (TC)', 2000.00),
(6, 'Prueba de función hepática', 600.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hace`
--

CREATE TABLE `hace` (
  `IDLaboratorio` char(5) NOT NULL,
  `IDEstudio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hace`
--

INSERT INTO `hace` (`IDLaboratorio`, `IDEstudio`) VALUES
('LAB01', 1),
('LAB01', 2),
('LAB01', 3),
('LAB02', 3),
('LAB02', 4),
('LAB02', 5),
('LAB02', 6),
('LAB03', 1),
('LAB03', 5),
('LAB03', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intervencion`
--

CREATE TABLE `intervencion` (
  `IDIntervencion` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` text NOT NULL,
  `IDDepartamento` char(5) NOT NULL,
  `Numero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `intervencion`
--

INSERT INTO `intervencion` (`IDIntervencion`, `Nombre`, `Descripcion`, `IDDepartamento`, `Numero`) VALUES
('INT00001', 'Cirugía general', 'Intervención quirúrgica para procedimientos generales', 'DEP01', 1),
('INT00002', 'Cirugía cardíaca', 'Procedimiento quirúrgico para enfermedades cardíacas', 'DEP02', 2),
('INT00003', 'Endoscopia', 'Procedimiento diagnóstico para observar el interior del tracto digestivo', 'DEP02', 1),
('INT00004', 'Cirugía ortopédica', 'Intervención para fracturas o lesiones óseas', 'DEP02', 2),
('INT00005', 'Cirugía neuroquirúrgica', 'Intervención para afecciones del sistema nervioso', 'DEP03', 1),
('INT00006', 'Cirugía estética', 'Procedimiento quirúrgico para mejorar la apariencia física', 'DEP03', 2),
('INT00007', 'Extracción de cálculos renales', 'Procedimiento para eliminar piedras del riñón', 'DEP08', 1),
('INT00008', 'Cirugía de apendicitis', 'Intervención para la extracción del apéndice inflamado', 'DEP04', 2),
('INT00009', 'Cesárea', 'Intervención para el parto por cirugía', 'DEP09', 1),
('INT00010', 'Laparoscopia', 'Cirugía mínimamente invasiva para diagnóstico o tratamiento', 'DEP05', 2),
('INT00011', 'Endoprótesis vascular', 'Procedimiento para insertar prótesis en vasos sanguíneos', 'DEP06', 1),
('INT00012', 'Cirugía maxilofacial', 'Procedimiento quirúrgico para afecciones de la cara y mandíbula', 'DEP06', 2),
('INT00013', 'Cirugía de varices', 'Intervención para eliminar o tratar las venas varicosas', 'DEP07', 1),
('INT00014', 'Cirugía de tiroides', 'Procedimiento para tratar afecciones de la glándula tiroides', 'DEP07', 2),
('INT00015', 'Biopsia', 'Extracción de muestra de tejido para análisis', 'DEP08', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laboratorio`
--

CREATE TABLE `laboratorio` (
  `IDLaboratorio` char(5) NOT NULL,
  `Descripcion` text NOT NULL,
  `Piso` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `laboratorio`
--

INSERT INTO `laboratorio` (`IDLaboratorio`, `Descripcion`, `Piso`) VALUES
('LAB01', 'Laboratorio de análisis clínicos generales', 2),
('LAB02', 'Laboratorio especializado en microbiología', 3),
('LAB03', 'Laboratorio de pruebas de imagenología y radiología', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--

CREATE TABLE `medicamento` (
  `IDMedicamento` char(15) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT INTO `medicamento` (`IDMedicamento`, `Nombre`, `Descripcion`) VALUES
('MED000000000001', 'Paracetamol', 'Analgésico y antipirético'),
('MED000000000002', 'Ibuprofeno', 'Antiinflamatorio no esteroideo'),
('MED000000000003', 'Amoxicilina', 'Antibiótico de amplio espectro'),
('MED000000000004', 'Omeprazol', 'Inhibidor de la bomba de protones'),
('MED000000000005', 'Loratadina', 'Antihistamínico'),
('MED000000000006', 'Metformina', 'Antidiabético oral'),
('MED000000000007', 'Losartán', 'Antihipertensivo'),
('MED000000000008', 'Salbutamol', 'Broncodilatador'),
('MED000000000009', 'Diclofenaco', 'Antiinflamatorio y analgésico'),
('MED000000000010', 'Ácido fólico', 'Suplemento vitamínico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `IDPaciente` char(10) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Sexo` char(1) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Altura` decimal(3,2) NOT NULL,
  `Peso` decimal(5,2) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `CURP` char(18) NOT NULL,
  `IDSeguro` char(13) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`IDPaciente`, `Nombre`, `Sexo`, `Telefono`, `Edad`, `Altura`, `Peso`, `FechaNacimiento`, `CURP`, `IDSeguro`) VALUES
('PAC0000001', 'Juan Perez', 'M', '5552000001', 30, 1.75, 70.50, '1995-04-10', 'PEPJ950410HDFABC01', 'SEG0000000001'),
('PAC0000002', 'Maria Lopez', 'F', '5552000002', 25, 1.65, 58.30, '2000-08-15', 'LOPM000815MDFABC02', 'SEG0000000002'),
('PAC0000003', 'Carlos Sanchez', 'M', '5552000003', 40, 1.80, 85.00, '1985-02-20', 'SANC850220HDFABC03', 'SEG0000000003'),
('PAC0000004', 'Ana Martinez', 'F', '5552000004', 35, 1.68, 60.00, '1990-05-30', 'MARA900530MDFABC04', 'SEG0000000004'),
('PAC0000005', 'Luis Ramirez', 'M', '5552000005', 28, 1.70, 75.00, '1997-12-12', 'RAML971212HDFABC05', 'SEG0000000005'),
('PAC0000006', 'Sofia Hernandez', 'F', '5552000006', 50, 1.58, 65.00, '1975-11-20', 'HERR751120MDFABC06', NULL),
('PAC0000007', 'Diego Torres', 'M', '5552000007', 60, 1.72, 80.00, '1965-07-07', 'TORD650707HDFABC07', NULL),
('PAC0000008', 'Elena Ruiz', 'F', '5552000008', 55, 1.62, 68.00, '1970-03-03', 'RUIE700303MDFABC08', NULL),
('PAC0000009', 'Miguel Chavez', 'M', '5552000009', 45, 1.78, 82.00, '1979-09-09', 'CHAM790909HDFABC09', 'SEG0000000006'),
('PAC0000010', 'Laura Flores', 'F', '5552000010', 33, 1.66, 59.00, '1992-06-25', 'FLOL920625MDFABC10', 'SEG0000000007'),
('PAC0000011', 'Pablo Garcia', 'M', '5552000011', 29, 1.80, 76.00, '1996-01-15', 'GARP960115HDFABC11', 'SEG0000000008'),
('PAC0000012', 'Adriana Mendoza', 'F', '5552000012', 38, 1.64, 62.00, '1987-10-10', 'MENM871010MDFABC12', 'SEG0000000009'),
('PAC0000013', 'Fernando Diaz', 'M', '5552000013', 42, 1.82, 90.00, '1982-02-02', 'DIAF820202HDFABC13', 'SEG0000000010'),
('PAC0000014', 'Carla Castillo', 'F', '5552000014', 27, 1.60, 55.00, '1998-04-04', 'CASC980404MDFABC14', NULL),
('PAC0000015', 'Ricardo Morales', 'M', '5552000015', 52, 1.75, 78.00, '1973-12-30', 'MORR731230HDFABC15', 'SEG0000000006'),
('PAC0000016', 'Juan De La Rosa', 'F', '4617890166', 19, 2.00, 45.00, '2025-05-22', 'HELD051210HGTRNGA8', 'SEG0000000008');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presentacion`
--

CREATE TABLE `presentacion` (
  `IDPresentacion` int(11) NOT NULL,
  `CantidadUnidad` int(11) NOT NULL,
  `Unidad` text NOT NULL,
  `Descripcion` text NOT NULL
) ;

--
-- Volcado de datos para la tabla `presentacion`
--

INSERT INTO `presentacion` (`IDPresentacion`, `CantidadUnidad`, `Unidad`, `Descripcion`) VALUES
(1, 60, 'ml', 'Frasco con 60 ml'),
(2, 120, 'ml', 'Frasco con 120 ml'),
(3, 500, 'mg', 'Tableta de 500 mg'),
(4, 250, 'mg', 'Tableta de 250 mg'),
(5, 30, 'ml', 'Gotero con 30 ml'),
(6, 90, 'ml', 'Frasco con 90 ml'),
(7, 100, 'mg', 'Tableta de 100 mg'),
(8, 200, 'mg', 'Tableta de 200 mg'),
(9, 15, 'ml', 'Gotero con 15 ml'),
(10, 10, 'mg', 'Tabletas de 10 mg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE `receta` (
  `IDDoctor` char(8) NOT NULL,
  `IDPaciente` char(10) NOT NULL,
  `IDMedicamento` char(15) NOT NULL,
  `Dosis` text NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`IDDoctor`, `IDPaciente`, `IDMedicamento`, `Dosis`, `Fecha`, `Hora`) VALUES
('DOC00001', 'PAC0000001', 'MED000000000003', '1 tableta cada 8 horas por 5 días', '2025-04-15', '09:00:00'),
('DOC00002', 'PAC0000003', 'MED000000000001', '10 ml cada 12 horas por 7 días', '2025-04-16', '10:30:00'),
('DOC00005', 'PAC0000006', 'MED000000000003', '5 ml cada 12 horas por 7 días', '2025-04-17', '08:45:00'),
('DOC00008', 'PAC0000008', 'MED000000000004', '1 tableta con el desayuno diariamente', '2025-04-18', '11:15:00'),
('DOC00010', 'PAC0000016', 'MED000000000009', '1 sobre cada 24 horas durante 3 días', '2025-05-18', '13:00:00'),
('DOC00017', 'PAC0000016', 'MED000000000005', '1 pastilla una vez al dia', '2025-05-26', '19:26:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `IDDepartamento` char(5) NOT NULL,
  `Numero` int(11) NOT NULL,
  `TipoSala` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`IDDepartamento`, `Numero`, `TipoSala`) VALUES
('DEP01', 1, 1),
('DEP04', 1, 1),
('DEP08', 2, 1),
('DEP02', 1, 2),
('DEP05', 1, 2),
('DEP09', 2, 2),
('DEP03', 1, 3),
('DEP06', 1, 3),
('DEP09', 1, 3),
('DEP10', 2, 3),
('DEP01', 2, 4),
('DEP07', 1, 4),
('DEP02', 2, 5),
('DEP08', 1, 5),
('DEP03', 2, 6),
('DEP04', 2, 7),
('DEP10', 1, 7),
('DEP05', 2, 8),
('DEP06', 2, 9),
('DEP07', 2, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

CREATE TABLE `seguro` (
  `IDSeguro` char(13) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Costo` decimal(8,2) NOT NULL,
  `IDTipoCob` int(11) NOT NULL,
  `IDCompañia` char(10) NOT NULL,
  `CostoCobertura` decimal(8,2) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `seguro`
--

INSERT INTO `seguro` (`IDSeguro`, `Telefono`, `Costo`, `IDTipoCob`, `IDCompañia`, `CostoCobertura`) VALUES
('SEG0000000001', '5551000001', 1500.00, 1, 'COMP000001', 30000.00),
('SEG0000000002', '5551000002', 1800.00, 2, 'COMP000002', 45000.00),
('SEG0000000003', '5551000003', 2200.00, 3, 'COMP000003', 55000.00),
('SEG0000000004', '5551000004', 3000.00, 4, 'COMP000004', 75000.00),
('SEG0000000005', '5551000005', 4000.00, 5, 'COMP000005', 100000.00),
('SEG0000000006', '5551000006', 2500.00, 2, 'COMP000001', 50000.00),
('SEG0000000007', '5551000007', 2700.00, 3, 'COMP000002', 60000.00),
('SEG0000000008', '5551000008', 3500.00, 4, 'COMP000003', 70000.00),
('SEG0000000009', '5551000009', 4200.00, 5, 'COMP000004', 140000.00),
('SEG0000000010', '5551000010', 5000.00, 1, 'COMP000005', 75000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seinterviene`
--

CREATE TABLE `seinterviene` (
  `IDDoctor` char(8) NOT NULL,
  `IDPaciente` char(10) NOT NULL,
  `IDIntervencion` char(10) NOT NULL,
  `Costo` decimal(8,2) NOT NULL
) ;

--
-- Volcado de datos para la tabla `seinterviene`
--

INSERT INTO `seinterviene` (`IDDoctor`, `IDPaciente`, `IDIntervencion`, `Costo`) VALUES
('DOC00001', 'PAC0000016', 'INT00001', 15000.00),
('DOC00003', 'PAC0000003', 'INT00006', 20000.00),
('DOC00007', 'PAC0000004', 'INT00009', 18000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sepresenta`
--

CREATE TABLE `sepresenta` (
  `IDPresentacion` int(11) NOT NULL,
  `IDTipoM` int(11) NOT NULL,
  `IDMedicamento` char(15) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Costo` decimal(7,2) NOT NULL
) ;

--
-- Volcado de datos para la tabla `sepresenta`
--

INSERT INTO `sepresenta` (`IDPresentacion`, `IDTipoM`, `IDMedicamento`, `Cantidad`, `Costo`) VALUES
(1, 1, 'MED000000000001', 1, 25.00),
(2, 2, 'MED000000000002', 1, 40.00),
(3, 1, 'MED000000000003', 20, 90.00),
(4, 2, 'MED000000000004', 10, 60.00),
(5, 1, 'MED000000000005', 1, 20.00),
(6, 2, 'MED000000000006', 1, 45.00),
(7, 1, 'MED000000000007', 20, 70.00),
(8, 2, 'MED000000000008', 10, 55.00),
(9, 1, 'MED000000000009', 1, 18.00),
(10, 2, 'MED000000000010', 30, 50.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocobertura`
--

CREATE TABLE `tipocobertura` (
  `IDTipoCob` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ;

--
-- Volcado de datos para la tabla `tipocobertura`
--

INSERT INTO `tipocobertura` (`IDTipoCob`, `Descripcion`) VALUES
(1, 'Básica'),
(2, 'Intermedia'),
(3, 'Avanzada'),
(4, 'Premium'),
(5, 'Total');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomedicamento`
--

CREATE TABLE `tipomedicamento` (
  `IDTipoM` int(11) NOT NULL,
  `Descripcion` text NOT NULL
) ;

--
-- Volcado de datos para la tabla `tipomedicamento`
--

INSERT INTO `tipomedicamento` (`IDTipoM`, `Descripcion`) VALUES
(1, 'Genérico'),
(2, 'De marca');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposala`
--

CREATE TABLE `tiposala` (
  `TipoSala` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ;

--
-- Volcado de datos para la tabla `tiposala`
--

INSERT INTO `tiposala` (`TipoSala`, `Descripcion`) VALUES
(1, 'Cuarto general'),
(2, 'Cuarto privado'),
(3, 'Quirófano'),
(4, 'Sala de espera'),
(5, 'Sala de emergencias'),
(6, 'Unidad de cuidados intensivos'),
(7, 'Sala de recuperación'),
(8, 'Sala de tratamiento'),
(9, 'Salón de consultas'),
(10, 'Sala de observación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `IDTurno` int(11) NOT NULL,
  `HoraInicio` time NOT NULL,
  `HoraFin` time NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`IDTurno`, `HoraInicio`, `HoraFin`, `Descripcion`) VALUES
(1, '00:00:00', '06:00:00', 'Turno Madrugada'),
(2, '06:00:00', '12:00:00', 'Turno Mañana'),
(3, '12:00:00', '18:00:00', 'Turno Tarde'),
(4, '18:00:00', '00:00:00', 'Turno Noche');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `idExterno` varchar(20) NOT NULL,
  `Tipo` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `password`, `idExterno`, `Tipo`) VALUES
('Admin', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'ADMIN001', 1),
('HELD051210HGTRNGA8', 'da4ff69f3a6030ac036cebb5705545ec232d11bd', 'PAC0000016', 2),
('MAHS991127643', '0f3e5bcfd4c5b0263e13809251bd2a43d75a028b', 'DOC00017', 3),
('Pedrito', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'ADMIN002', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`IdAdmin`);

--
-- Indices de la tabla `citamedica`
--
ALTER TABLE `citamedica`
  ADD PRIMARY KEY (`IDCita`),
  ADD KEY `CitaMedicaFK1` (`IDPaciente`),
  ADD KEY `CitaMedicaFK2` (`IDDepartamento`),
  ADD KEY `CitaMedicaFK3` (`IDDoctor`);

--
-- Indices de la tabla `compañia`
--
ALTER TABLE `compañia`
  ADD PRIMARY KEY (`IDCompañia`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`IDDepartamento`);

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`IDDoctor`),
  ADD KEY `DoctorFK1` (`IDEspecialidad`),
  ADD KEY `DoctorFK2` (`IDDepartamento`),
  ADD KEY `DoctorFK3` (`IDTurno`);

--
-- Indices de la tabla `enfermero`
--
ALTER TABLE `enfermero`
  ADD PRIMARY KEY (`IDEnfermero`),
  ADD KEY `EnfermeroFK1` (`IDTurno`),
  ADD KEY `EnfermeroFK2` (`IDDepartamento`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`IDEspecialidad`);

--
-- Indices de la tabla `estudia`
--
ALTER TABLE `estudia`
  ADD PRIMARY KEY (`IDDoctor`,`IDPaciente`,`IDEstudio`),
  ADD KEY `EstudiaFK2` (`IDPaciente`),
  ADD KEY `EstudiaFK3` (`IDEstudio`);

--
-- Indices de la tabla `estudio`
--
ALTER TABLE `estudio`
  ADD PRIMARY KEY (`IDEstudio`);

--
-- Indices de la tabla `hace`
--
ALTER TABLE `hace`
  ADD PRIMARY KEY (`IDLaboratorio`,`IDEstudio`),
  ADD KEY `HaceFK2` (`IDEstudio`);

--
-- Indices de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD PRIMARY KEY (`IDIntervencion`),
  ADD KEY `IntervencionFK1` (`IDDepartamento`,`Numero`);

--
-- Indices de la tabla `laboratorio`
--
ALTER TABLE `laboratorio`
  ADD PRIMARY KEY (`IDLaboratorio`);

--
-- Indices de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`IDMedicamento`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`IDPaciente`),
  ADD KEY `PacienteFK1` (`IDSeguro`);

--
-- Indices de la tabla `presentacion`
--
ALTER TABLE `presentacion`
  ADD PRIMARY KEY (`IDPresentacion`);

--
-- Indices de la tabla `receta`
--
ALTER TABLE `receta`
  ADD PRIMARY KEY (`IDDoctor`,`IDPaciente`,`IDMedicamento`),
  ADD KEY `RecetaFK2` (`IDPaciente`),
  ADD KEY `RecetaFK3` (`IDMedicamento`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`IDDepartamento`,`Numero`),
  ADD KEY `SalaFK2` (`TipoSala`);

--
-- Indices de la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD PRIMARY KEY (`IDSeguro`),
  ADD KEY `SeguroFK1` (`IDTipoCob`),
  ADD KEY `SeguroFK2` (`IDCompañia`);

--
-- Indices de la tabla `seinterviene`
--
ALTER TABLE `seinterviene`
  ADD PRIMARY KEY (`IDDoctor`,`IDPaciente`,`IDIntervencion`),
  ADD KEY `SeIntervieneFK2` (`IDPaciente`),
  ADD KEY `SeIntervieneFK3` (`IDIntervencion`);

--
-- Indices de la tabla `sepresenta`
--
ALTER TABLE `sepresenta`
  ADD PRIMARY KEY (`IDPresentacion`,`IDTipoM`,`IDMedicamento`),
  ADD KEY `ContieneFK2` (`IDTipoM`),
  ADD KEY `ContieneFK3` (`IDMedicamento`);

--
-- Indices de la tabla `tipocobertura`
--
ALTER TABLE `tipocobertura`
  ADD PRIMARY KEY (`IDTipoCob`);

--
-- Indices de la tabla `tipomedicamento`
--
ALTER TABLE `tipomedicamento`
  ADD PRIMARY KEY (`IDTipoM`);

--
-- Indices de la tabla `tiposala`
--
ALTER TABLE `tiposala`
  ADD PRIMARY KEY (`TipoSala`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`IDTurno`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `citamedica`
--
ALTER TABLE `citamedica`
  ADD CONSTRAINT `CitaMedicaFK1` FOREIGN KEY (`IDPaciente`) REFERENCES `paciente` (`IDPaciente`),
  ADD CONSTRAINT `CitaMedicaFK2` FOREIGN KEY (`IDDepartamento`) REFERENCES `departamento` (`IDDepartamento`),
  ADD CONSTRAINT `CitaMedicaFK3` FOREIGN KEY (`IDDoctor`) REFERENCES `doctor` (`IDDoctor`);

--
-- Filtros para la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `DoctorFK1` FOREIGN KEY (`IDEspecialidad`) REFERENCES `especialidad` (`IDEspecialidad`),
  ADD CONSTRAINT `DoctorFK2` FOREIGN KEY (`IDDepartamento`) REFERENCES `departamento` (`IDDepartamento`),
  ADD CONSTRAINT `DoctorFK3` FOREIGN KEY (`IDTurno`) REFERENCES `turno` (`IDTurno`);

--
-- Filtros para la tabla `enfermero`
--
ALTER TABLE `enfermero`
  ADD CONSTRAINT `EnfermeroFK1` FOREIGN KEY (`IDTurno`) REFERENCES `turno` (`IDTurno`),
  ADD CONSTRAINT `EnfermeroFK2` FOREIGN KEY (`IDDepartamento`) REFERENCES `departamento` (`IDDepartamento`);

--
-- Filtros para la tabla `estudia`
--
ALTER TABLE `estudia`
  ADD CONSTRAINT `EstudiaFK1` FOREIGN KEY (`IDDoctor`) REFERENCES `doctor` (`IDDoctor`),
  ADD CONSTRAINT `EstudiaFK2` FOREIGN KEY (`IDPaciente`) REFERENCES `paciente` (`IDPaciente`),
  ADD CONSTRAINT `EstudiaFK3` FOREIGN KEY (`IDEstudio`) REFERENCES `estudio` (`IDEstudio`);

--
-- Filtros para la tabla `hace`
--
ALTER TABLE `hace`
  ADD CONSTRAINT `HaceFK1` FOREIGN KEY (`IDLaboratorio`) REFERENCES `laboratorio` (`IDLaboratorio`),
  ADD CONSTRAINT `HaceFK2` FOREIGN KEY (`IDEstudio`) REFERENCES `estudio` (`IDEstudio`);

--
-- Filtros para la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD CONSTRAINT `IntervencionFK1` FOREIGN KEY (`IDDepartamento`,`Numero`) REFERENCES `sala` (`IDDepartamento`, `Numero`);

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `PacienteFK1` FOREIGN KEY (`IDSeguro`) REFERENCES `seguro` (`IDSeguro`);

--
-- Filtros para la tabla `receta`
--
ALTER TABLE `receta`
  ADD CONSTRAINT `RecetaFK1` FOREIGN KEY (`IDDoctor`) REFERENCES `doctor` (`IDDoctor`),
  ADD CONSTRAINT `RecetaFK2` FOREIGN KEY (`IDPaciente`) REFERENCES `paciente` (`IDPaciente`),
  ADD CONSTRAINT `RecetaFK3` FOREIGN KEY (`IDMedicamento`) REFERENCES `medicamento` (`IDMedicamento`);

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `SalaFK1` FOREIGN KEY (`IDDepartamento`) REFERENCES `departamento` (`IDDepartamento`),
  ADD CONSTRAINT `SalaFK2` FOREIGN KEY (`TipoSala`) REFERENCES `tiposala` (`TipoSala`);

--
-- Filtros para la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD CONSTRAINT `SeguroFK1` FOREIGN KEY (`IDTipoCob`) REFERENCES `tipocobertura` (`IDTipoCob`),
  ADD CONSTRAINT `SeguroFK2` FOREIGN KEY (`IDCompañia`) REFERENCES `compañia` (`IDCompañia`);

--
-- Filtros para la tabla `seinterviene`
--
ALTER TABLE `seinterviene`
  ADD CONSTRAINT `SeIntervieneFK1` FOREIGN KEY (`IDDoctor`) REFERENCES `doctor` (`IDDoctor`),
  ADD CONSTRAINT `SeIntervieneFK2` FOREIGN KEY (`IDPaciente`) REFERENCES `paciente` (`IDPaciente`),
  ADD CONSTRAINT `SeIntervieneFK3` FOREIGN KEY (`IDIntervencion`) REFERENCES `intervencion` (`IDIntervencion`);

--
-- Filtros para la tabla `sepresenta`
--
ALTER TABLE `sepresenta`
  ADD CONSTRAINT `ContieneFK1` FOREIGN KEY (`IDPresentacion`) REFERENCES `presentacion` (`IDPresentacion`),
  ADD CONSTRAINT `ContieneFK2` FOREIGN KEY (`IDTipoM`) REFERENCES `tipomedicamento` (`IDTipoM`),
  ADD CONSTRAINT `ContieneFK3` FOREIGN KEY (`IDMedicamento`) REFERENCES `medicamento` (`IDMedicamento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
