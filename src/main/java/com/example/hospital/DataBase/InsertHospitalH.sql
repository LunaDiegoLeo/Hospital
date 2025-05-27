USE HospitalH;
-- Insertar 5 compañías
INSERT INTO Compañia VALUES
  ('COMP000001', 'SaludPlus'),
  ('COMP000002', 'MediCare'),
  ('COMP000003', 'VidaSegura'),
  ('COMP000004', 'ClinicAssure'),
  ('COMP000005', 'SeguroVital');

-- Insertar 5 tipos de cobertura
INSERT INTO TipoCobertura VALUES
  (1, 'Básica'),
  (2, 'Intermedia'),
  (3, 'Avanzada'),
  (4, 'Premium'),
  (5, 'Total');

-- Insertar 10 seguros
INSERT INTO Seguro VALUES
  ('SEG0000000001', '5551000001', 1500.00, 1, 'COMP000001',  30000.00),
  ('SEG0000000002', '5551000002', 1800.00, 2, 'COMP000002',  45000.00),
  ('SEG0000000003', '5551000003', 2200.00, 3, 'COMP000003',  55000.00),
  ('SEG0000000004', '5551000004', 3000.00, 4, 'COMP000004',  75000.00),
  ('SEG0000000005', '5551000005', 4000.00, 5, 'COMP000005', 100000.00),
  ('SEG0000000006', '5551000006', 2500.00, 2, 'COMP000001',  50000.00),
  ('SEG0000000007', '5551000007', 2700.00, 3, 'COMP000002',  60000.00),
  ('SEG0000000008', '5551000008', 3500.00, 4, 'COMP000003',  70000.00),
  ('SEG0000000009', '5551000009', 4200.00, 5, 'COMP000004', 140000.00),
  ('SEG0000000010', '5551000010', 5000.00, 1, 'COMP000005',  75000.00);
  
INSERT INTO Paciente VALUES
  ('PAC0000001', 'Juan Perez',           'M', '5552000001', 30, 1.75, 70.50, '1995-04-10', 'PEPJ950410HDFABC01', 'SEG0000000001'),
  ('PAC0000002', 'Maria Lopez',          'F', '5552000002', 25, 1.65, 58.30, '2000-08-15', 'LOPM000815MDFABC02', 'SEG0000000002'),
  ('PAC0000003', 'Carlos Sanchez',       'M', '5552000003', 40, 1.80, 85.00, '1985-02-20', 'SANC850220HDFABC03', 'SEG0000000003'),
  ('PAC0000004', 'Ana Martinez',         'F', '5552000004', 35, 1.68, 60.00, '1990-05-30', 'MARA900530MDFABC04', 'SEG0000000004'),
  ('PAC0000005', 'Luis Ramirez',         'M', '5552000005', 28, 1.70, 75.00, '1997-12-12', 'RAML971212HDFABC05', 'SEG0000000005'),
  ('PAC0000006', 'Sofia Hernandez',      'F', '5552000006', 50, 1.58, 65.00, '1975-11-20', 'HERR751120MDFABC06', NULL),
  ('PAC0000007', 'Diego Torres',         'M', '5552000007', 60, 1.72, 80.00, '1965-07-07', 'TORD650707HDFABC07', NULL),
  ('PAC0000008', 'Elena Ruiz',           'F', '5552000008', 55, 1.62, 68.00, '1970-03-03', 'RUIE700303MDFABC08', NULL),
  ('PAC0000009', 'Miguel Chavez',        'M', '5552000009', 45, 1.78, 82.00, '1979-09-09', 'CHAM790909HDFABC09', 'SEG0000000006'),
  ('PAC0000010', 'Laura Flores',         'F', '5552000010', 33, 1.66, 59.00, '1992-06-25', 'FLOL920625MDFABC10', 'SEG0000000007'),
  ('PAC0000011', 'Pablo Garcia',         'M', '5552000011', 29, 1.80, 76.00, '1996-01-15', 'GARP960115HDFABC11', 'SEG0000000008'),
  ('PAC0000012', 'Adriana Mendoza',      'F', '5552000012', 38, 1.64, 62.00, '1987-10-10', 'MENM871010MDFABC12', 'SEG0000000009'),
  ('PAC0000013', 'Fernando Diaz',        'M', '5552000013', 42, 1.82, 90.00, '1982-02-02', 'DIAF820202HDFABC13', 'SEG0000000010'),
  ('PAC0000014', 'Carla Castillo',       'F', '5552000014', 27, 1.60, 55.00, '1998-04-04', 'CASC980404MDFABC14', NULL),
  ('PAC0000015', 'Ricardo Morales',      'M', '5552000015', 52, 1.75, 78.00, '1973-12-30', 'MORR731230HDFABC15', NULL);

-- Insertar 5 historiales para pacientes seleccionados al azar
INSERT INTO Historial VALUES
  ('HIST000001', 'No fumador; hace ejercicio 3 veces/semana', '2024-01-10', 'Penicilina',      'Ninguna',        'PAC0000002'),
  ('HIST000002', 'Fumador ocasional; dieta equilibrada',      '2024-02-05', 'Polen',           'Hipertensión',   'PAC0000005'),
  ('HIST000003', 'Sedentario; sugiere mejorar actividad física', '2024-03-12', 'Ninguna',         'Diabetes tipo II','PAC0000007'),
  ('HIST000004', 'Ciclista aficionado; no fuma',               '2024-04-01', 'Ácaros del polvo','Asma',           'PAC0000010'),
  ('HIST000005', 'Camina diario 30 min; no bebe alcohol',      '2024-04-15', 'Ninguna',         'Ninguna',        'PAC0000014');

INSERT INTO Departamento VALUES
  ('DEP01', 'Emergencias',      1),
  ('DEP02', 'Cardiología',      2),
  ('DEP03', 'Neurología',       3),
  ('DEP04', 'Pediatría',        4),
  ('DEP05', 'Radiología',       5),
  ('DEP06', 'Oncología',        6),
  ('DEP07', 'Traumatología',    7),
  ('DEP08', 'Urología',         8),
  ('DEP09', 'Ginecología',      9),
  ('DEP10', 'Dermatología',    10);

-- TipoSala
INSERT INTO TipoSala VALUES
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

-- Sala
INSERT INTO Sala VALUES
  ('DEP01', 1, 1),
  ('DEP01', 2, 4),
  ('DEP02', 1, 2),
  ('DEP02', 2, 5),
  ('DEP03', 1, 3),
  ('DEP03', 2, 6),
  ('DEP04', 1, 1),
  ('DEP04', 2, 7),
  ('DEP05', 1, 2),
  ('DEP05', 2, 8),
  ('DEP06', 1, 3),
  ('DEP06', 2, 9),
  ('DEP07', 1, 4),
  ('DEP07', 2, 10),
  ('DEP08', 1, 5),
  ('DEP08', 2, 1),
  ('DEP09', 1, 3),
  ('DEP09', 2, 2),
  ('DEP10', 1, 7),
  ('DEP10', 2, 3);

-- Especialidades
INSERT INTO Especialidad VALUES
  (1,  'Cardiología'),
  (2,  'Neurología'),
  (3,  'Pediatría'),
  (4,  'Dermatología'),
  (5,  'Oncología'),
  (6,  'Traumatología'),
  (7,  'Urología'),
  (8,  'Ginecología'),
  (9,  'Radiología'),
  (10, 'Anestesiología'),
  (11, 'Psiquiatría'),
  (12, 'Endocrinología'),
  (13, 'Gastroenterología'),
  (14, 'Neumología'),
  (15, 'Reumatología');

-- Turnos
INSERT INTO Turno VALUES
  (1, '00:00:00', '06:00:00', 'Turno Madrugada'),
  (2, '06:00:00', '12:00:00', 'Turno Mañana'),
  (3, '12:00:00', '18:00:00', 'Turno Tarde'),
  (4, '18:00:00', '00:00:00', 'Turno Noche');

-- Doctores
INSERT INTO Doctor VALUES
  ('DOC00001', 'Juan Perez',        '5553000001', 'PEPJ850101HDFABC01', 'CED00001', 'RFC0000000001', 10, 1500.00,  1, 'DEP01', 2),
  ('DOC00002', 'Maria Hernandez',   '5553000002', 'HEHM900202MDFABC02', 'CED00002', 'RFC0000000002',  8, 1700.00,  2, 'DEP02', 3),
  ('DOC00003', 'Carlos Lopez',      '5553000003', 'LOPC780303HDFABC03', 'CED00003', 'RFC0000000003', 15, 2000.00,  3, 'DEP03', 1),
  ('DOC00004', 'Ana Diaz',          '5553000004', 'DIZA920404MDFABC04', 'CED00004', 'RFC0000000004',  5, 1300.00,  4, 'DEP04', 2),
  ('DOC00005', 'Luis Martinez',     '5553000005', 'MALM830505HDFABC05', 'CED00005', 'RFC0000000005', 12, 1800.00,  5, 'DEP05', 4),
  ('DOC00006', 'Sofia Gomez',       '5553000006', 'GOSF880606MDFABC06', 'CED00006', 'RFC0000000006',  7, 1600.00,  6, 'DEP06', 3),
  ('DOC00007', 'Diego Sanchez',     '5553000007', 'SAND750707HDFABC07', 'CED00007', 'RFC0000000007', 20, 2500.00,  7, 'DEP07', 1),
  ('DOC00008', 'Elena Torres',      '5553000008', 'TORE810808MDFABC08', 'CED00008', 'RFC0000000008',  9, 1400.00,  8, 'DEP08', 4),
  ('DOC00009', 'Miguel Ramirez',    '5553000009', 'RAMM790909HDFABC09', 'CED00009', 'RFC0000000009', 14, 2100.00,  9, 'DEP09', 2),
  ('DOC00010', 'Laura Flores',      '5553000010', 'FLOL920010MDFABC10', 'CED00010', 'RFC0000000010',  6, 1550.00, 10, 'DEP10', 3),
  ('DOC00011', 'Pablo Ruiz',        '5553000011', 'RUIP860111HDFABC11', 'CED00011', 'RFC0000000011', 11, 1750.00, 11, 'DEP01', 1),
  ('DOC00012', 'Adriana Ortiz',     '5553000012', 'ORTE880212MDFABC12', 'CED00012', 'RFC0000000012', 13, 1900.00, 12, 'DEP02', 2),
  ('DOC00013', 'Fernando Castro',   '5553000013', 'CAST820313HDFABC13', 'CED00013', 'RFC0000000013', 16, 2300.00, 13, 'DEP03', 4),
  ('DOC00014', 'Carla Perez',       '5553000014', 'PERC950414MDFABC14', 'CED00014', 'RFC0000000014',  7, 1650.00, 14, 'DEP04', 3),
  ('DOC00015', 'Ricardo Morales',   '5553000015', 'MORR730515HDFABC15', 'CED00015', 'RFC0000000015', 18, 2400.00, 15, 'DEP05', 1);

-- Enfermeros
INSERT INTO Enfermero VALUES
  ('ENF0000001', 'Lucía Mendoza',     'MELU901001MDFABC01', 'CENF0001', 'RFCENF000001', 950.00,  2, 'DEP01'),
  ('ENF0000002', 'Marco Ríos',        'RIMR850202HDFABC02', 'CENF0002', 'RFCENF000002', 980.00,  3, 'DEP02'),
  ('ENF0000003', 'Teresa Delgado',    'DELT870303MDFABC03', 'CENF0003', 'RFCENF000003', 910.00,  1, 'DEP03'),
  ('ENF0000004', 'Oscar Jiménez',     'JIJO840404HDFABC04', 'CENF0004', 'RFCENF000004', 970.00,  4, 'DEP04'),
  ('ENF0000005', 'Valeria Torres',    'TOVL930505MDFABC05', 'CENF0005', 'RFCENF000005', 920.00,  2, 'DEP05'),
  ('ENF0000006', 'Gabriel Herrera',   'HEHG790606HDFABC06', 'CENF0006', 'RFCENF000006', 990.00,  3, 'DEP06'),
  ('ENF0000007', 'Daniela Romero',    'RODA920707MDFABC07', 'CENF0007', 'RFCENF000007', 965.00,  1, 'DEP07'),
  ('ENF0000008', 'Andrés Morales',    'MOAN830808HDFABC08', 'CENF0008', 'RFCENF000008', 930.00,  4, 'DEP08'),
  ('ENF0000009', 'Samantha Ruiz',     'RUSA950909MDFABC09', 'CENF0009', 'RFCENF000009', 960.00,  2, 'DEP09'),
  ('ENF0000010', 'Javier Navarro',    'NAJA780101HDFABC10', 'CENF0010', 'RFCENF000010', 940.00,  3, 'DEP10');

-- Citas médicas
INSERT INTO CitaMedica VALUES
  ('CITA00000000001', '09:00:00', '2025-04-01', 'PAC0000001', 'DEP01', 350.00, 'DOC00003', 'Dolor de cabeza persistente'),
  ('CITA00000000002', '10:30:00', '2025-04-02', 'PAC0000003', 'DEP05', 400.00, 'DOC00005', 'Revisión por dolor abdominal'),
  ('CITA00000000003', '12:00:00', '2025-04-03', 'PAC0000006', 'DEP03', 300.00, 'DOC00002', 'Consulta dermatológica'),
  ('CITA00000000004', '14:00:00', '2025-04-04', 'PAC0000011', 'DEP08', 450.00, 'DOC00008', 'Evaluación por mareos y náuseas'),
  ('CITA00000000005', '08:15:00', '2025-04-05', 'PAC0000009', 'DEP02', 375.00, 'DOC00013', 'Control de presión arterial'),
  ('CITA00000000006', '16:45:00', '2025-04-06', 'PAC0000002', 'DEP04', 390.00, 'DOC00001', 'Consulta ginecológica'),
  ('CITA00000000007', '11:30:00', '2025-04-07', 'PAC0000014', 'DEP06', 420.00, 'DOC00010', 'Diagnóstico por dolor en articulaciones');

-- Cuida
INSERT INTO Cuida VALUES
  ('PAC0000001', 'ENF0000001'),
  ('PAC0000002', 'ENF0000002'),
  ('PAC0000003', 'ENF0000003'),
  ('PAC0000004', 'ENF0000004'),
  ('PAC0000005', 'ENF0000005'),
  ('PAC0000006', 'ENF0000006'),
  ('PAC0000007', 'ENF0000007'),
  ('PAC0000008', 'ENF0000008'),
  ('PAC0000009', 'ENF0000009'),
  ('PAC0000010', 'ENF0000010'),
  ('PAC0000011', 'ENF0000001');

-- TipoMedicamento
INSERT INTO TipoMedicamento VALUES
  (1, 'Genérico'),
  (2, 'De marca');

-- Insertar en Presentacion
INSERT INTO Presentacion VALUES
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

-- Insertar en Medicamento (10 registros)
INSERT INTO Medicamento VALUES
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
  
INSERT INTO SePresenta VALUES
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
-- Recetas
INSERT INTO Receta VALUES
  ('DOC00001', 'PAC0000001', 'MED000000000003', '1 tableta cada 8 horas por 5 días', '2025-04-15', '09:00:00'),
  ('DOC00002', 'PAC0000003', 'MED000000000001', '10 ml cada 12 horas por 7 días', '2025-04-16', '10:30:00'),
  ('DOC00005', 'PAC0000006', 'MED000000000003', '5 ml cada 12 horas por 7 días', '2025-04-17', '08:45:00'),
  ('DOC00008', 'PAC0000008', 'MED000000000004', '1 tableta con el desayuno diariamente', '2025-04-18', '11:15:00'),
  ('DOC00010', 'PAC0000010', 'MED000000000009', '1 sobre cada 24 horas durante 3 días', '2025-04-18', '13:00:00');

-- Laboratorios
INSERT INTO Laboratorio (IDLaboratorio, Descripcion, Piso) VALUES
  ('LAB01', 'Laboratorio de análisis clínicos generales', 2),
  ('LAB02', 'Laboratorio especializado en microbiología', 3),
  ('LAB03', 'Laboratorio de pruebas de imagenología y radiología', 4);

INSERT INTO Estudio (IDEstudio, Descripcion, Costo) VALUES
  (1, 'Análisis de sangre completo', 500.00),
  (2, 'Radiografía de tórax', 350.00),
  (3, 'Ultrasonido abdominal', 750.00),
  (4, 'Electrocardiograma', 400.00),
  (5, 'Tomografía computarizada (TC)', 2000.00),
  (6, 'Prueba de función hepática', 600.00);

-- Insertar 10 registros en la tabla Hace
INSERT INTO Hace (IDLaboratorio, IDEstudio) VALUES
  ('LAB01', 1),
  ('LAB01', 2),
  ('LAB02', 3),
  ('LAB02', 4),
  ('LAB03', 5),
  ('LAB03', 6),
  ('LAB01', 3),
  ('LAB02', 5),
  ('LAB02', 6),
  ('LAB03', 1);

-- Estudia
INSERT INTO Estudia VALUES
  ('DOC00001', 'PAC0000001', 1),
  ('DOC00002', 'PAC0000003', 2),
  ('DOC00005', 'PAC0000006', 3),
  ('DOC00008', 'PAC0000008', 4),
  ('DOC00010', 'PAC0000010', 5),
  ('DOC00013', 'PAC0000012', 6),
  ('DOC00007', 'PAC0000005', 1);
  
-- Intervenciones
INSERT INTO Intervencion VALUES
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

-- SeInterviene
INSERT INTO SeInterviene VALUES
  ('DOC00001', 'PAC0000001', 'INT00001', 15000.00),
  ('DOC00003', 'PAC0000003', 'INT00006', 20000.00),
  ('DOC00007', 'PAC0000004', 'INT00009', 18000.00);
