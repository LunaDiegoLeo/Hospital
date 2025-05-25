package com.example.hospital.Controller.Admin;


import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.*;
import com.example.hospital.Model.Tablas.*;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    private String nombre1;
    private String fxml;
    private Connection connection= ConexioBD.getConnection();
    private DepartamentoDao departamentoDao = (DepartamentoDao) DaoFactory.DEPARTAMENTO.crear(connection);
    private TurnoDao turnoDao = (TurnoDao) DaoFactory.TURNO.crear(connection);
    private DoctorDao doctorDao = (DoctorDao) DaoFactory.DOCTOR.crear(connection);
    private EspecialidadDao especialidadDao = (EspecialidadDao) DaoFactory.ESPECIALIDAD.crear(connection);
    private Dao<Usuario> usuariodao = (Dao<Usuario>) DaoFactory.USUARIO.crear(connection);


    // --------------------- FX Elements ------------------------
    @FXML
    private TableView<Doctor> tablaAdmin;
    @FXML
    private TableColumn<Doctor, String> idad;
    @FXML
    private TableColumn<Doctor, String> nombread;
    @FXML
    private TableColumn<Doctor, String> telefonod;
    @FXML
    private TableColumn<Doctor, String> curpd;
    @FXML
    private TableColumn<Doctor, String> cedulad;
    @FXML
    private TableColumn<Doctor, String> rfcd;
    @FXML
    private TableColumn<Doctor, String> aniod;
    @FXML
    private TableColumn<Doctor, String> sueldo;
    @FXML
    private TableColumn<Doctor, String> ides;
    @FXML
    private TableColumn<Doctor, String> iddep;
    @FXML
    private TableColumn<Doctor, String> idturno;
    
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField curp;
    @FXML
    private TextField cedula;
    @FXML
    private TextField rfc;
    @FXML
    private TextField sueldoPorDia;
    @FXML
    private TextField aniosExp;
    @FXML
    private SplitMenuButton especialidad;
    @FXML
    private SplitMenuButton departamento;
    @FXML
    private SplitMenuButton turno;
    @FXML
    private Button agre;

    @FXML
    private javafx.scene.control.Button reg; // Botón regresar, pedido explícito

    public DoctorController() throws Exception {
    }

    public void setText(String text, String fxml) {
        this.nombre1 = text;
        this.fxml = fxml + "-view.fxml";
    }
    @FXML
    private TextField idbus;

    @FXML
    private void buscar() {
        String id = idbus.getText().trim();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Por favor, ingresa un ID de doctor.");
            alert.showAndWait();
            return;
        }

        try {
            Doctor doctor = doctorDao.consultar(id);
            if (doctor != null) {
                String doctorInfo = String.format(
                        "ID: %s%nNombre: %s%nTeléfono: %s%nCURP: %s%nCédula: %s%nRFC: %s%nAños de Experiencia: %d%nSueldo: %.2f%nID Especialidad: %d%nID Departamento: %s%nID Turno: %d",
                        doctor.getIdDoctor(),
                        doctor.getNombre(),
                        doctor.getTelefono(),
                        doctor.getCurp(),
                        doctor.getCedula(),
                        doctor.getRfc(),
                        doctor.getAniosExperiencia(),
                        doctor.getSueldoPorDia(),
                        doctor.getIdEspecialidad(),
                        doctor.getIdDepartamento(),
                        doctor.getIdTurno()
                );

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información del Doctor");
                alert.setHeaderText("Doctor encontrado");
                alert.setContentText(doctorInfo);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Doctor no encontrado");
                alert.setContentText("No se encontró un doctor con el ID especificado: " + id);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar el doctor");
            alert.setContentText("Ha ocurrido un error al intentar buscar el doctor. Por favor, intenta de nuevo.");
            alert.showAndWait();
        }

    }
    
    @FXML
    private void eliminar() {
        String id = idbus.getText().trim();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Por favor, ingresa un ID de doctor.");
            alert.showAndWait();
            return;
        }

        try {
            Doctor doctor = doctorDao.consultar(id);
            if (doctor != null) {
                String doctorInfo = String.format(
                        "ID: %s%nNombre: %s%nTeléfono: %s%nCURP: %s%nCédula: %s%nRFC: %s%nAños de Experiencia: %d%nSueldo: %.2f%nID Especialidad: %d%nID Departamento: %s%nID Turno: %d",
                        doctor.getIdDoctor(),
                        doctor.getNombre(),
                        doctor.getTelefono(),
                        doctor.getCurp(),
                        doctor.getCedula(),
                        doctor.getRfc(),
                        doctor.getAniosExperiencia(),
                        doctor.getSueldoPorDia(),
                        doctor.getIdEspecialidad(),
                        doctor.getIdDepartamento(),
                        doctor.getIdTurno()
                );

                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirmación de Eliminación");
                confirmAlert.setHeaderText("Doctor encontrado");
                confirmAlert.setContentText(doctorInfo + "\n\n¿Deseas eliminar este doctor?");

                ButtonType buttonTypeDelete = new ButtonType("Eliminar");
                ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
                confirmAlert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

                confirmAlert.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeDelete) {
                        try {
                            boolean usu= usuariodao.eliminar(id);
                            if(doctorDao.eliminar(id)&&usu){
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Eliminación exitosa");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("El doctor con ID " + id + " ha sido eliminado correctamente.");
                                successAlert.showAndWait();
                                idbus.clear();
                            } else {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Error");
                                errorAlert.setHeaderText("Error al eliminar");
                                errorAlert.setContentText("El doctor con ID " + id + " no pudo ser eliminado. Por favor, intente nuevamente.");
                                errorAlert.showAndWait();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText("Error al eliminar el doctor");
                            errorAlert.setContentText("Ha ocurrido un error al intentar eliminar el doctor. Por favor, intenta de nuevo.");
                            errorAlert.showAndWait();
                        }
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Doctor no encontrado");
                alert.setContentText("No se encontró un doctor con el ID especificado: " + id);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar el doctor");
            alert.setContentText("Ha ocurrido un error al intentar buscar el doctor. Por favor, intenta de nuevo.");
            alert.showAndWait();
        }
    }

    @FXML
    private void buscar2() {
        String id = idbus.getText().trim();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Por favor, ingresa un ID de doctor.");
            alert.showAndWait();
            return;
        }

        try {
            Doctor doctor = doctorDao.consultar(id);
            if (doctor != null) {
                nombre.setText(doctor.getNombre());
                telefono.setText(doctor.getTelefono());
                curp.setText(doctor.getCurp());
                cedula.setText(doctor.getCedula());
                rfc.setText(doctor.getRfc());
                sueldoPorDia.setText(String.valueOf(doctor.getSueldoPorDia()));
                aniosExp.setText(String.valueOf(doctor.getAniosExperiencia()));
                Especialidad especialidad1= especialidadDao.consultar(doctor.getIdEspecialidad()+"");
                Departamento departamento1= departamentoDao.consultar(doctor.getIdDepartamento());
                Turno turno1= turnoDao.consultar(doctor.getIdTurno()+"");
                especialidad.setText(especialidad1.getDescripcion());
                departamento.setText(departamento1.getDescripcion());
                turno.setText(turno1.getDescripcion());

                // Disable specific fields & enable others for editing
                curp.setDisable(true);
                cedula.setDisable(true);
                rfc.setDisable(true);

                nombre.setDisable(false);
                telefono.setDisable(false);
                sueldoPorDia.setDisable(false);
                aniosExp.setDisable(false);
                especialidad.setDisable(false);
                departamento.setDisable(false);
                turno.setDisable(false);

                idbus.setDisable(true); // Disable ID field
                agre.setDisable(false); // Enable the button
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Doctor no encontrado");
                alert.setContentText("No se encontró un doctor con el ID especificado: " + id);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar el doctor");
            alert.setContentText("Ha ocurrido un error al intentar buscar el doctor. Por favor, intenta de nuevo.");
            alert.showAndWait();
        }
    }


    @FXML
    private void modificar() {
        try {
            String idDoctor = idbus.getText().trim();
            String nombreText = nombre.getText().trim();
            String telefonoText = telefono.getText().trim();
            String sueldoText = sueldoPorDia.getText().trim();
            String aniosExpText = aniosExp.getText().trim();
            String especialidadText = especialidad.getText();
            String departamentoText = departamento.getText();
            String turnoText = turno.getText();

            if (idDoctor.isEmpty() || nombreText.isEmpty() || telefonoText.isEmpty() || sueldoText.isEmpty() || aniosExpText.isEmpty() ||
                    especialidadText == null || departamentoText == null || turnoText == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Campos vacíos");
                alert.setContentText("Por favor, llena todos los campos obligatorios.");
                alert.showAndWait();
                return;
            }

            double sueldo = Double.parseDouble(sueldoText);
            int anios = Integer.parseInt(aniosExpText);
            int idEspecialidad = buscarIdEspecialidadPorDescripcion(especialidadText);
            String idDepartamento = buscarIdDepartamentoPorDescripcion(departamentoText);
            int idTurno = buscarIdTurnoPorDescripcion(turnoText);

            Doctor doctor = new Doctor(
                    idDoctor,
                    nombreText,
                    telefonoText,
                    curp.getText(),
                    cedula.getText(),
                    rfc.getText(),
                    anios,
                    sueldo,
                    idEspecialidad,
                    idDepartamento,
                    idTurno
            );

            if (doctorDao.modificar(doctor)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Doctor actualizado correctamente.");
                alert.showAndWait();

                // Re-enable fields, clear inputs, and refresh table
                idbus.setDisable(false);
                nombre.setDisable(true);
                telefono.setDisable(true);
                sueldoPorDia.setDisable(true);
                aniosExp.setDisable(true);
                especialidad.setDisable(true);
                departamento.setDisable(true);
                turno.setDisable(true);
                idbus.clear();
                nombre.clear();
                telefono.clear();
                sueldoPorDia.clear();
                aniosExp.clear();
                especialidad.setText("Especialidad");
                departamento.setText("Departamento");
                turno.setText("Turno");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Actualización fallida");
                alert.setContentText("No se pudo actualizar el registro del doctor.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entrada no válida");
            alert.setContentText("Sueldo y años de experiencia deben ser valores numéricos.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error inesperado");
            alert.setContentText("Ha ocurrido un error al intentar modificar el doctor.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (especialidad != null){
            cargarEspecialidades();
        }
        if (departamento != null){
            cargarDepartamentos();
        }
        if (turno != null){
            cargarTurnos();
        }
        if(tablaAdmin != null){
            configurarTabla();
            listarDoctoresEnTabla();
        }
    }


    private void configurarTabla() {
        idad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idDoctor"));
        nombread.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        telefonod.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("telefono"));
        curpd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("curp"));
        cedulad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("cedula"));
        rfcd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("rfc"));
        aniod.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("aniosExperiencia"));
        sueldo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("sueldoPorDia"));
        ides.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idEspecialidad"));
        iddep.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idDepartamento"));
        idturno.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idTurno"));
    }
    private void listarDoctoresEnTabla() {
        ObservableList<Doctor> lista = doctorDao.listar();
        tablaAdmin.setItems(lista);
    }



    @FXML
    protected void agregar() {
        try {
            String nombreText = nombre.getText().trim();
            String telefonoText = telefono.getText().trim();
            String curpText = curp.getText().trim();
            String cedulaText = cedula.getText().trim();
            String rfcText = rfc.getText().trim();
            String sueldoText = sueldoPorDia.getText().trim();
            String aniosExpText = aniosExp.getText().trim();

            String especialidadText = especialidad.getText();
            String departamentoText = departamento.getText();
            String turnoText = turno.getText();

            if (nombreText.isEmpty() || telefonoText.isEmpty() || curpText.isEmpty() || cedulaText.isEmpty() || rfcText.isEmpty()
                    || sueldoText.isEmpty() || aniosExpText.isEmpty()
                    || especialidadText == null || departamentoText == null || turnoText == null) {
                System.out.println("Debes llenar todos los campos");
                return;
            }

            double sueldo = Double.parseDouble(sueldoText);
            int anios = Integer.parseInt(aniosExpText);

            // Buscar IDs por nombres seleccionados en los SplitMenuButton
            int idEspecialidad = buscarIdEspecialidadPorDescripcion(especialidadText);
            String idDepartamento = buscarIdDepartamentoPorDescripcion(departamentoText);
            int idTurno = buscarIdTurnoPorDescripcion(turnoText);

            // Generar el nuevo ID del doctor
            String nuevoIdDoctor = generarNuevoIdDoctor();

            // Crear objeto Doctor completo
            Doctor doctor = new Doctor(
                    nuevoIdDoctor,
                    nombreText,
                    telefonoText,
                    curpText,
                    cedulaText,
                    rfcText,
                    anios,
                    sueldo,
                    idEspecialidad,
                    idDepartamento,
                    idTurno
            );

            // Insertar el doctor
            Usuario usuario = new Usuario(rfcText,rfcText+"/*/*",nuevoIdDoctor,3);
            boolean usu= usuariodao.insertar(usuario);
            if (doctorDao.insertar(doctor) && usu) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Éxito");
                alert.setContentText("Doctor agregado correctamente con ID: " + nuevoIdDoctor);
                alert.showAndWait();

                // Clear input fields after successful addition
                nombre.clear();
                telefono.clear();
                curp.clear();
                cedula.clear();
                rfc.clear();
                sueldoPorDia.clear();
                aniosExp.clear();
                especialidad.setText("Especialidad");
                departamento.setText("Departamento");
                turno.setText("Turno");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al agregar el doctor");
                alert.setContentText("No se pudo agregar el doctor. Por favor, verifica los datos e intenta nuevamente.");
                alert.showAndWait();
            }


        } catch (NumberFormatException nfe) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entrada no válida");
            alert.setContentText("Sueldo por día y años de experiencia deben ser valores numéricos válidos");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int buscarIdEspecialidadPorDescripcion(String descripcion) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idEspecialidad FROM especialidad WHERE descripcion = '" + descripcion + "'")) {
            if (rs.next()) {
                return rs.getInt("idEspecialidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Especialidad no encontrada");
        alert.setContentText("No se encontró la especialidad con la descripción: " + descripcion);
        alert.showAndWait();
        throw new RuntimeException("Especialidad no encontrada: " + descripcion);
    }

    private String buscarIdDepartamentoPorDescripcion(String descripcion) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT IDDepartamento FROM Departamento WHERE Descripcion = '" + descripcion + "'")) {
            if (rs.next()) {
                return rs.getString("IDDepartamento");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Departamento no encontrado");
        alert.setContentText("No se encontró el departamento con la descripción: " + descripcion);
        alert.showAndWait();
        throw new RuntimeException("Departamento no encontrado: " + descripcion);
    }

    private int buscarIdTurnoPorDescripcion(String descripcion) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT IDTurno FROM Turno WHERE Descripcion = '" + descripcion + "'")) {
            if (rs.next()) {
                return rs.getInt("IDTurno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Turno no encontrado");
        alert.setContentText("No se encontró el turno con la descripción: " + descripcion);
        alert.showAndWait();
        throw new RuntimeException("Turno no encontrado: " + descripcion);
    }

    private String generarNuevoIdDoctor() {
        String nuevoId = "DOC00001";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idDoctor FROM doctor ORDER BY idDoctor DESC LIMIT 1")) {
            if (rs.next()) {
                String ultimoId = rs.getString("idDoctor");
                int num = Integer.parseInt(ultimoId.substring(3)) + 1;
                nuevoId = String.format("DOC%05d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoId;
    }


    @FXML
    protected void regresar() {
        try {
            GenericView paciente = new GenericView();
            paciente.mostrar(fxml, nombre1);
            reg.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --------------------- Inicialización ------------------------


    private void cargarEspecialidades() {
        ObservableList<Especialidad> especialidades = especialidadDao.listar();
        if (!especialidades.isEmpty()) {
            especialidad.getItems().clear();
            especialidades.forEach(e -> {
                javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(e.getDescripcion());
                item.setOnAction(ev -> especialidad.setText(e.getDescripcion()));
                especialidad.getItems().add(item);
            });
        }
    }

    private void cargarDepartamentos() {
        ObservableList<Departamento> departamentos = departamentoDao.listar();
        if (!departamentos.isEmpty()) {
            departamento.getItems().clear();
            departamentos.forEach(d -> {
                javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(d.getDescripcion());
                item.setOnAction(ev -> departamento.setText(d.getDescripcion()));
                departamento.getItems().add(item);
            });
        }
    }

    private void cargarTurnos() {
        ObservableList<Turno> turnos = turnoDao.listar();
        if (!turnos.isEmpty()) {
            turno.getItems().clear();
            turnos.forEach(t -> {
                javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(t.getDescripcion());
                item.setOnAction(ev -> turno.setText(t.getDescripcion()));
                turno.getItems().add(item);
            });
        }
    }


}
