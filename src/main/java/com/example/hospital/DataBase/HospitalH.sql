
CREATE DATABASE HospitalH;
USE HospitalH;

CREATE TABLE Compañia(IDCompañia char(10) not null,
Nombre VARCHAR(50) not null,
CONSTRAINT CompañiaPK PRIMARY KEY (IDCompañia));

CREATE TABLE TipoCobertura(IDTipoCob int not null,
Descripcion varchar(50) not null,
CONSTRAINT TipoCoberturaPK PRIMARY KEY (IDTipoCob),
CONSTRAINT TipoCoberturaCK1 CHECK(IDTipoCob>0));

CREATE TABLE Seguro(IDSeguro char(13) not null,
Telefono varchar(10) not null,
Costo DECIMAL(8,2) not null,
IDTipoCob int not null,
IDCompañia char(10) not null,
CostoCobertura DECIMAL(8,2),
CONSTRAINT SeguroPK PRIMARY KEY(IDSeguro),
CONSTRAINT SeguroFK1 FOREIGN KEY(IDTipoCob) REFERENCES TipoCobertura(IDTipoCob),
CONSTRAINT SeguroFK2 FOREIGN KEY(IDCompañia) REFERENCES Compañia(IDCompañia),
CONSTRAINT SeguroCK1 CHECK (Costo>0),
CONSTRAINT SeguroCK2 CHECK (CostoCobertura>0));

CREATE TABLE Paciente(IDPaciente char(10) not null,
Nombre varchar(50) not null,
Sexo char(1) not null,
Telefono varchar(10) not null,
Edad int not null,
Altura DECIMAL(3,2) not null,
Peso DECIMAL(5,2) not null,
FechaNacimiento DATE not null,
CURP char(18) not null,
IDSeguro char(13),
CONSTRAINT PacientePK PRIMARY KEY (IDPaciente),
CONSTRAINT PacienteFK1 FOREIGN KEY(IDSeguro) REFERENCES Seguro(IDSeguro),
CONSTRAINT PacienteCK1 CHECK(Sexo='F' or Sexo='M'),
CONSTRAINT PacienteCK2 CHECK(Edad>0),
CONSTRAINT PacienteCK3 CHECK(Altura>0),
CONSTRAINT PacienteCK4 CHECK(Peso>0));


CREATE TABLE Departamento (IDDepartamento char(5) not null,
Descripcion varchar(50) not null,
Piso int not null,
CONSTRAINT DepartamentoPK PRIMARY KEY(IDDepartamento), 
CONSTRAINT DepartamentoCH1 CHECK(Piso>0));

CREATE TABLE TipoSala(TipoSala int not null,
Descripcion varchar(50) not null,
CONSTRAINT TipoSalaPK PRIMARY KEY(TipoSala),
CONSTRAINT TipoSalaCK1 CHECK(TipoSala>0));

CREATE TABLE Sala(IDDepartamento char(5) not null,
Numero int not null,
TipoSala int not null,
CONSTRAINT SalaPK PRIMARY KEY(IDDepartamento,Numero),
CONSTRAINT SalaFK1 FOREIGN KEY(IDDepartamento) REFERENCES Departamento(IDDepartamento),
CONSTRAINT SalaFK2 FOREIGN KEY(TipoSala) REFERENCES TipoSala(TipoSala),
CONSTRAINT SalaCK1 CHECK(Numero>0));

CREATE TABLE Especialidad (IDEspecialidad int not null,
Descripcion varchar(50) not null,
CONSTRAINT EspecialidadPK PRIMARY KEY(IDEspecialidad),
CONSTRAINT EspecialidadCK1 CHECK(IDEspecialidad>0));

CREATE TABLE Turno(IDTurno int not null,
HoraInicio TIME not null,
HoraFin TIME not null,
Descripcion varchar(50) not null,
CONSTRAINT TurnoPK PRIMARY KEY(IDTurno),
CONSTRAINT TurnoCK1 CHECK(IDTurno>0));

CREATE TABLE Doctor(IDDoctor char(8) not null,
Nombre varchar(50) not null,
Telefono varchar(10) not null,
CURP char(18) not null,
Cedula varchar(8) not null,
RFC varchar(13) not null,
AñosExperiencia int not null,
SueldoPorDia DECIMAL(8,2) not null,
IDEspecialidad int not null,
IDDepartamento char(5) not null,
IDTurno int not null,
CONSTRAINT DoctorPK PRIMARY KEY(IDDoctor),
CONSTRAINT DoctorFK1 FOREIGN KEY(IDEspecialidad) REFERENCES Especialidad(IDEspecialidad),
CONSTRAINT DoctorFK2 FOREIGN KEY(IDDepartamento) REFERENCES Departamento(IDDepartamento),
CONSTRAINT DoctorFK3 FOREIGN KEY(IDTurno) REFERENCES Turno(IDTurno),
CONSTRAINT DoctorCK1 CHECK (SueldoPorDia>0),
CONSTRAINT DoctorCK2 CHECK (AñosExperiencia>0));

CREATE TABLE CitaMedica (IDCita char(15) not null,
Hora TIME not null,
Fecha Date not null,
IDPaciente char(10) not null,
IDDepartamento char(5) not null,
Costo DECIMAL(5,2) not null,
IDDoctor char(8) not null,
CONSTRAINT CitaMedicaPK PRIMARY KEY(IDCita),
CONSTRAINT CitaMedicaFK1 FOREIGN KEY(IDPaciente) REFERENCES Paciente(IDPaciente),
CONSTRAINT CitaMedicaFK2 FOREIGN KEY(IDDepartamento) REFERENCES Departamento(IDDepartamento),
CONSTRAINT CitaMedicaFK3 FOREIGN KEY(IDDoctor) REFERENCES Doctor(IDDoctor),
CONSTRAINT CitaMedicaCK1 CHECK (Costo>0));

CREATE TABLE Enfermero(IDEnfermero char(10) not null,
Nombre varchar(50) not null,
CURP char(18) not null,
Cedula varchar(8) not null,
RFC varchar(13) not null,
SueldoPorDia DECIMAL(8,2),
IDTurno int not null,
IDDepartamento char(5) not null,
CONSTRAINT EnfermeroPK PRIMARY KEY(IDEnfermero),
CONSTRAINT EnfermeroFK1 FOREIGN KEY(IDTurno) REFERENCES Turno(IDTurno),
CONSTRAINT EnfermeroFK2 FOREIGN KEY(IDDepartamento) REFERENCES Departamento(IDDepartamento),
CONSTRAINT EnfermeroCK1 CHECK (SueldoPorDia>0));


CREATE TABLE TipoMedicamento(IDTipoM int not null,
Descripcion Text not null,
CONSTRAINT TipoMedicamentoPK PRIMARY KEY(IDTipoM),
CONSTRAINT TipoMedicamentoCK1 CHECK(IDTipoM>0));

CREATE TABLE Presentacion(IDPresentacion int not null,
CantidadUnidad int not null,
Unidad Text not null,
Descripcion TEXT not null,
CONSTRAINT PresentacionPK PRIMARY KEY(IDPresentacion),
CONSTRAINT PresentacionCK1 CHECK(IDPresentacion>0),
CONSTRAINT PresentacionCK2 CHECK(CantidadUnidad>0));

CREATE TABLE Medicamento(IDMedicamento char(15) not null,
Nombre varchar(30) not null,
Descripcion Text not null,
CONSTRAINT MedicamentoPK PRIMARY KEY(IDMedicamento));

CREATE TABLE SePresenta(IDPresentacion int not null,
IDTipoM int not null,
IDMedicamento char(15) not null,
Cantidad int not null,
Costo DECIMAL(7,2) not null,
CONSTRAINT ContienePK PRIMARY KEY(IDPresentacion,IDTipoM,IDMedicamento),
CONSTRAINT ContieneFK1 FOREIGN KEY(IDPresentacion) REFERENCES Presentacion(IDPresentacion),
CONSTRAINT ContieneFK2 FOREIGN KEY(IDTipoM) REFERENCES TipoMedicamento(IDTipoM),
CONSTRAINT ContieneFK3 FOREIGN KEY(IDMedicamento) REFERENCES Medicamento(IDMedicamento),
CONSTRAINT ContieneCK1 CHECK(Cantidad>0),
CONSTRAINT ContieneCK2 CHECK(Costo>0));



CREATE TABLE Receta(IDDoctor char(8) not null,
IDPaciente char(10) not null,
IDMedicamento char(15) not null,
Dosis Text not null,
Fecha DATE not null,
Hora Time not null,
CONSTRAINT RecetaPK PRIMARY KEY(IDDoctor,IDPaciente,IDMedicamento),
CONSTRAINT RecetaFK1 FOREIGN KEY(IDDoctor) REFERENCES Doctor(IDDoctor),
CONSTRAINT RecetaFK2 FOREIGN KEY(IDPaciente) REFERENCES Paciente(IDPaciente),
CONSTRAINT RecetaFK3 FOREIGN KEY(IDMedicamento) REFERENCES Medicamento(IDMedicamento));

CREATE TABLE Laboratorio(IDLaboratorio char(5) not null,
Descripcion text not null,
Piso int not null,
CONSTRAINT LaboratorioPK PRIMARY KEY(IDLaboratorio),
CONSTRAINT LaboratorioCK1 CHECK(Piso>0));


CREATE TABLE Estudio(IDEstudio int not null,
Descripcion Text not null,
Costo DECIMAL(8,2) not null,
CONSTRAINT EstudioPK PRIMARY KEY(IDEstudio),
CONSTRAINT EstudioCK1 CHECK(Costo>0),
CONSTRAINT EstudioCK2 CHECK(IDEstudio>0));

CREATE TABLE Hace(IDLaboratorio char(5) not null,
IDEstudio int not null,
CONSTRAINT HacePK PRIMARY KEY(IDLaboratorio,IDEstudio),
CONSTRAINT HaceFK1 FOREIGN KEY(IDLaboratorio) REFERENCES Laboratorio(IDLaboratorio),
CONSTRAINT HaceFK2 FOREIGN KEY(IDEstudio) REFERENCES Estudio(IDEstudio));

CREATE TABLE Estudia(IDDoctor char(8) not null,
IDPaciente char(10) not null,
IDEstudio int not null,
CONSTRAINT EstudiaPK PRIMARY KEY(IDDoctor,IDPaciente,IDEstudio),
CONSTRAINT EstudiaFK1 FOREIGN KEY(IDDoctor) REFERENCES Doctor(IDDoctor),
CONSTRAINT EstudiaFK2 FOREIGN KEY(IDPaciente) REFERENCES Paciente(IDPaciente),
CONSTRAINT EstudiaFK3 FOREIGN KEY(IDEstudio) REFERENCES Estudio(IDEstudio));

CREATE TABLE Intervencion (IDIntervencion char(10) not null,
Nombre varchar(50) not null,
Descripcion Text not null,
IDDepartamento char(5) not null,
Numero int not null,
CONSTRAINT IntervencionPK PRIMARY KEY(IDIntervencion),
CONSTRAINT IntervencionFK1 FOREIGN KEY (IDDepartamento,Numero) REFERENCES Sala(IDDepartamento,Numero));

CREATE TABLE SeInterviene (IDDoctor char(8) not null,
IDPaciente char(10) not null,
IDIntervencion char(10) not null,
Costo DECIMAL(8,2) not null,
CONSTRAINT SeIntervienePK PRIMARY KEY(IDDoctor,IDPaciente,IDIntervencion),
CONSTRAINT SeIntervieneFK1 FOREIGN KEY(IDDoctor) REFERENCES Doctor(IDDoctor),
CONSTRAINT SeIntervieneFK2 FOREIGN KEY(IDPaciente) REFERENCES Paciente(IDPaciente),
CONSTRAINT SeIntervieneFK3 FOREIGN KEY(IDIntervencion) REFERENCES Intervencion(IDIntervencion),
CONSTRAINT SeIntervieneCK1 CHECK(Costo>0));
