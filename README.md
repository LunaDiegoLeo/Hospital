# Hospital Hernández

Aplicación de escritorio para la gestión de información y procesos de un hospital. El proyecto surgió como una propuesta para organizar en una sola solución los datos de pacientes, personal médico, citas, medicamentos, estudios clínicos, intervenciones y recursos del hospital.

La idea fue llevar el diseño de una base de datos hospitalaria a una aplicación que pudiera utilizarse desde distintos perfiles. Por eso, además del modelado y la implementación de la base de datos, desarrollé una interfaz con funciones diferenciadas para administradores, doctores y pacientes.



## 1. ¿Qué problema busca resolver?

El punto de partida fue la necesidad de administrar de forma organizada la información que se genera durante la operación de un hospital. En este tipo de institución se relacionan muchos elementos: pacientes, médicos, enfermeros, departamentos, salas, citas, seguros, medicamentos, laboratorios y procedimientos.

Si estos datos se manejan de manera aislada, consultar información o mantenerla actualizada puede volverse complicado. También es importante conservar las relaciones entre los registros; por ejemplo, saber qué médico atiende una cita, qué estudios se solicitaron a un paciente o qué medicamentos forman parte de una receta.

A partir de este planteamiento, el objetivo del proyecto fue diseñar e implementar una base de datos relacional y una aplicación que permitiera consultar y administrar esa información mediante operaciones específicas para cada tipo de usuario.

## 2. ¿Qué hace el sistema?

La aplicación cuenta con tres perfiles principales:

### Administrador

Tiene acceso a los módulos de administración del hospital. Desde su panel puede gestionar:

- Administradores.
- Pacientes.
- Doctores y enfermeros.
- Compañías aseguradoras y seguros.
- Laboratorios y estudios.
- Departamentos y salas.
- Intervenciones.
- Medicamentos.

En los módulos de administración se contemplan operaciones para **agregar, modificar, eliminar, listar y buscar** registros. De acuerdo con el manual de usuario, al registrar un paciente o doctor también se genera su usuario de acceso; al eliminarlo, se elimina igualmente el usuario asociado.

El administrador también puede cerrar sesión y cambiar su contraseña.

### Doctor

El doctor consulta las citas que tiene asignadas y puede trabajar con el paciente correspondiente a una cita activa. Desde su panel puede:

- Consultar sus citas.
- Prescribir medicamentos.
- Solicitar estudios clínicos o de laboratorio.
- Registrar intervenciones indicadas para el paciente.
- Cambiar su contraseña y cerrar sesión.

La aplicación contempla una ventana de atención de 30 minutos para identificar al paciente de una cita en curso.

### Paciente

El paciente puede consultar información relacionada con su atención médica:

- Citas médicas.
- Recetas y medicamentos.
- Estudios realizados o solicitados.
- Intervenciones.

También puede agendar nuevas citas y reagendarlas, siempre que exista disponibilidad, además de cambiar su contraseña y cerrar sesión.

## 3. Flujo general de la aplicación

El recorrido principal del sistema se puede entender de la siguiente manera:

1. **Inicio de sesión.** El usuario ingresa sus credenciales desde la pantalla inicial.
2. **Identificación del perfil.** El sistema utiliza el tipo de usuario para dirigirlo al panel correspondiente: administrador, doctor o paciente.
3. **Consulta de información.** La interfaz solicita al modelo los datos necesarios mediante los objetos de acceso a datos (DAO).
4. **Acceso a MySQL.** Los DAO ejecutan las consultas SQL utilizando la conexión JDBC.
5. **Procesamiento.** Los resultados se convierten en objetos del modelo y se muestran en las vistas.
6. **Operaciones.** Según los permisos y funciones del perfil, el usuario puede consultar o modificar información, registrar citas, generar recetas o asociar estudios e intervenciones.
7. **Persistencia.** Los cambios se guardan en la base de datos para que puedan consultarse posteriormente.

### Ejemplo: atención de un paciente

Un flujo representativo es el de una consulta médica:

- El paciente agenda una cita y esta queda relacionada con un departamento y un doctor.
- El doctor consulta sus citas y, cuando corresponde, identifica al paciente que está siendo atendido.
- Durante la atención puede generar una receta, solicitar un estudio o registrar una intervención.
- Estas operaciones se relacionan con el paciente y el doctor en la base de datos.
- Posteriormente, el paciente puede consultar sus recetas, estudios, intervenciones y citas desde su propio panel.


## 4. Diseño de la base de datos

La base de datos se diseñó para representar las entidades y relaciones que intervienen en la operación hospitalaria. El esquema contempla información personal, administrativa y médica, así como relaciones entre los diferentes módulos.

### Principales grupos de información

| Área | Información que administra |
|---|---|
| Pacientes y seguros | Datos personales, afiliación, compañías aseguradoras y tipos de cobertura. |
| Personal | Doctores, enfermeros, especialidades, departamentos y turnos. |
| Citas | Fecha, hora, paciente, doctor, departamento y costo. |
| Instalaciones | Departamentos, salas y tipos de sala. |
| Medicamentos | Medicamentos, presentaciones, tipos de medicamento y recetas. |
| Estudios | Estudios clínicos, laboratorios y relación entre ambos. |
| Intervenciones | Catálogo de intervenciones y registro de los doctores y pacientes involucrados. |
| Usuarios | Credenciales y tipo de perfil para acceder a la aplicación. |

### Relaciones importantes

Algunas de las relaciones que se modelaron son:

- Un paciente puede tener asociado un seguro.
- Una compañía puede ofrecer varios seguros y cada seguro se relaciona con un tipo de cobertura.
- Los doctores y enfermeros pertenecen a departamentos y tienen turnos asignados.
- Un doctor puede atender varias citas, mientras que cada cita corresponde a un paciente y un doctor.
- Los enfermeros pueden atender a distintos pacientes y un paciente puede recibir atención de varios enfermeros.
- Un departamento contiene salas y las intervenciones se realizan en una sala determinada.
- Los estudios pueden estar disponibles en distintos laboratorios.
- Las recetas relacionan al doctor, al paciente y al medicamento, además de guardar dosis, fecha y hora.
- Las intervenciones registran la participación de doctores y pacientes.

### Modelado documentado

Dentro de `modelado/` se incluyen los documentos que utilicé para definir y representar la base de datos:

- **Definición del problema:** requerimientos iniciales del Hospital Hernández.
- **Diagrama entidad-relación:** representación de las entidades y sus relaciones.
- **Modelo relacional:** transformación del modelo conceptual a tablas y relaciones.
- **Dependencias:** documento de dependencias funcionales del diseño.
- **Script SQL:** definición y datos de la base de datos.

Estos documentos permiten seguir el proceso desde el planteamiento inicial hasta la estructura que utiliza la aplicación.



## 5. Arquitectura y organización del código

El proyecto está desarrollado en Java y utiliza una separación por responsabilidades. La estructura principal sigue una organización de tipo **Modelo–Vista–Controlador (MVC)**, complementada con objetos DAO para el acceso a datos.

```text
Hospital/
├── modelado/
│   ├── Definición del problema.pdf
│   ├── HospitalEntidadRelacion.pdf
│   ├── HospitalModeloRelacional.pdf
│   ├── HospitalHDependecias.pdf
│   ├── hospitalh.sql
│   ├── Manual de instalacion.pdf
│   └── MANUAL USUARIO FINAL.pdf
│
├── src/
│   └── main/
│       ├── java/com/example/hospital/
│       │   ├── Config/
│       │   ├── Controller/
│       │   │   ├── Admin/
│       │   │   ├── Doctor/
│       │   │   └── Paciente/
│       │   ├── Model/
│       │   │   ├── Daos/
│       │   │   └── Tablas/
│       │   └── Vista/
│       │       ├── Admin/
│       │       ├── Doctor/
│       │       └── Paciente/
│       │
│       └── resources/
│           └── com/example/hospital/
│               ├── Vista/
│               ├── css/
│               └── imagenes/
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

### ¿Qué responsabilidad tiene cada parte?

- **Vista:** contiene las pantallas FXML y las clases que las muestran. También incluye hojas de estilo e imágenes.
- **Controller:** recibe las acciones del usuario y coordina lo que debe ocurrir en cada pantalla. Está separado por los perfiles de administrador, doctor y paciente.
- **Model/Tablas:** contiene las clases que representan las entidades del dominio, como `Paciente`, `Doctor`, `CitaMedica`, `Medicamento` e `Intervencion`.
- **Model/Daos:** contiene las clases encargadas de consultar y modificar los registros de la base de datos.
- **DaoFactory:** centraliza la creación del DAO correspondiente a cada entidad o módulo.
- **ConexioBD:** administra la conexión JDBC con MySQL.
- **Config:** contiene la configuración utilizada para conectarse a la base de datos.
- **resources:** reúne las vistas FXML, estilos CSS e imágenes de la interfaz.

### Acceso a datos: patrón DAO y fábrica

Para evitar concentrar todas las consultas SQL en los controladores, se utilizan clases DAO. Por ejemplo, `PacienteDao` se encarga de las operaciones relacionadas con pacientes y `CitaMedicaDao` de las citas médicas.

La clase `DaoFactory` permite solicitar el DAO correspondiente mediante una opción del catálogo, en lugar de construir manualmente cada implementación desde los controladores.

### Estrategia para el cálculo de costos

También se incluyeron clases relacionadas con el patrón **Strategy** para separar las reglas de cálculo de costos. En el código existen estrategias para calcular el costo de una cita y otras que ajustan un costo según características del paciente, como edad, peso o si cuenta con seguro.

Esto permite mantener las reglas de cálculo separadas de la lógica principal de las pantallas.


## 6. Tecnologías utilizadas

| Tecnología | Uso dentro del proyecto |
|---|---|
| Java 22 | Lenguaje y versión configurada para compilar el proyecto. |
| JavaFX 23 | Desarrollo de la interfaz de escritorio. |
| FXML | Definición de las pantallas y sus componentes visuales. |
| CSS | Estilos de la interfaz. |
| MySQL | Gestión y persistencia de la información. |
| JDBC | Comunicación entre Java y MySQL. |
| Maven | Administración de dependencias y construcción del proyecto. |
| ControlsFX | Componentes adicionales para JavaFX. |
| FormsFX | Componentes de formularios. |
| BootstrapFX | Recursos de estilo para JavaFX. |
| JUnit 5 | Dependencias de pruebas unitarias configuradas en Maven. |



## 7. Instalación y ejecución

### Requisitos

- JDK 22.
- MySQL Server.
- Maven (opcional si se utiliza el Maven Wrapper incluido).
- Un entorno de desarrollo compatible con Java, como IntelliJ IDEA, Eclipse o NetBeans.

### 1. Clonar el repositorio

```bash
git clone https://github.com/LunaDiegoLeo/Hospital.git
cd Hospital
```

### 2. Crear la base de datos

El proyecto contiene scripts SQL dentro de `src/main/java/com/example/hospital/DataBase/`:

- `HospitalH.sql`: crea la base de datos y sus tablas.
- `InsertHospitalH.sql`: agrega registros de ejemplo.

Primero se debe ejecutar `HospitalH.sql` en MySQL y después `InsertHospitalH.sql`.

También se conserva en `modelado/` el archivo SQL del modelado y la documentación de la base de datos.

### 3. Configurar la conexión

La clase `ConexioBD` obtiene los datos de conexión desde un archivo de texto con tres líneas:

```text
jdbc:mysql://localhost:3306/hospitalh
root
tu_contraseña
```

En el proyecto, ese archivo se encuentra en:

```text
src/main/java/com/example/hospital/Config/Bd.txt
```

**Importante:** en la versión actual, `ConexioBD.java` tiene escrita directamente una ruta absoluta de Windows para leer `Bd.txt`. Para ejecutar el proyecto en otra computadora, hay que cambiar esa ruta por una ruta relativa o por una configuración externa, además de ajustar la URL, usuario y contraseña de MySQL.

### 4. Ejecutar la aplicación

Desde la raíz del proyecto:

```bash
./mvnw clean javafx:run
```

En Windows:

```powershell
.\mvnw.cmd clean javafx:run
```

La clase de inicio configurada en Maven es:

```text
com.example.hospital.Vista.login
```


## 8. Consideraciones de la versión actual

Hay algunos detalles de configuración que conviene tener presentes al preparar el entorno:

- La conexión utiliza el controlador `com.mysql.cj.jdbc.Driver`, pero el `pom.xml` incluido no declara explícitamente la dependencia de MySQL Connector/J. Si el controlador no está disponible en el entorno de ejecución, será necesario agregar esa dependencia al proyecto.
- La ruta del archivo `Bd.txt` está escrita de forma absoluta en `ConexioBD.java`, por lo que debe adaptarse al equipo donde se ejecute.
- La configuración de conexión de ejemplo utiliza el usuario `root`; se recomienda utilizar credenciales propias del entorno local y no publicar contraseñas reales.
- El manual de instalación incluido en `modelado/` sirve como referencia, pero los nombres y rutas deben contrastarse con la estructura actual del repositorio.



## 9. Documentación del proyecto

La carpeta `modelado/` reúne los documentos que acompañan el desarrollo:

| Documento | Descripción |
|---|---|
| `A) Definicion del problema.pdf` | Planteamiento inicial y requerimientos del hospital. |
| `B)HospitalEntidadRelacion.pdf` | Modelo entidad-relación. |
| `B)HospitalModeloRelacional.pdf` | Modelo relacional. |
| `B)HospitalHDependecias.pdf` | Dependencias funcionales. |
| `B)hospitalh.sql` | Script SQL documentado dentro del modelado. |
| `E)Manual de instalacion.pdf` | Guía de instalación original. |
| `F)MANUAL USUARIO FINAL.pdf` | Descripción de las funciones disponibles para cada perfil. |



## 10. Lo que trabajé en este proyecto

Este proyecto me permitió trabajar tanto la parte de diseño de bases de datos como su integración con una aplicación de escritorio. No se trató solamente de crear tablas, sino de identificar qué información necesitaba el hospital, establecer cómo se relacionaba y después utilizar ese diseño desde los distintos módulos de la aplicación.

Durante el desarrollo trabajé con relaciones entre entidades, llaves primarias y foráneas, consultas SQL, conexión mediante JDBC, separación de responsabilidades con MVC, acceso a datos mediante DAO y creación de interfaces con JavaFX y FXML.

Uno de los puntos importantes fue conectar los procesos entre sí: una cita no es un registro aislado, sino que involucra al paciente, al doctor y al departamento; de la misma manera, una receta relaciona al paciente con el doctor y los medicamentos indicados. Esa integración es lo que permite que la información tenga sentido dentro del sistema.



**Proyecto académico – Hospital Hernández**

Desarrollado con Java, JavaFX y MySQL.
