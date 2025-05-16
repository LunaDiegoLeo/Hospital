package com.example.hospital.Controller.Admin;


import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.Daos.*;
import com.example.hospital.Model.Tablas.*;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EnfermeroController implements Initializable {

    private String nombre1;
    private String fxml;
    private Connection connection= ConexioBD.getConnection();
    private DepartamentoDao departamentoDao = (DepartamentoDao) DaoFactory.DEPARTAMENTO.crear(connection);
    private TurnoDao turnoDao = (TurnoDao) DaoFactory.TURNO.crear(connection);
    private EnfermeroDao enfermeroDao = (EnfermeroDao) DaoFactory.ENFERMERO.crear(connection);
    private EspecialidadDao especialidadDao = (EspecialidadDao) DaoFactory.ESPECIALIDAD.crear(connection);
    private Dao<Usuario> usuariodao = (Dao<Usuario>) DaoFactory.USUARIO.crear(connection);


    // --------------------- FX Elements ------------------------
    @FXML
    private TableView<Enfermero> tablaAdmin;
    @FXML
    private TableColumn<Enfermero, String> idad;
    @FXML
    private TableColumn<Enfermero, String> nombread;
    @FXML
    private TableColumn<Enfermero, String> telefonod;
    @FXML
    private TableColumn<Enfermero, String> curpd;
    @FXML
    private TableColumn<Enfermero, String> cedulad;
    @FXML
    private TableColumn<Enfermero, String> rfcd;
  
    @FXML
    private TableColumn<Enfermero, String> sueldo;
    
    @FXML
    private TableColumn<Enfermero, String> iddep;
    @FXML
    private TableColumn<Enfermero, String> idturno;

    @FXML
    private TextField nombre;

    @FXML
    private TextField curp;
    @FXML
    private TextField cedula;
    @FXML
    private TextField rfc;
    @FXML
    private TextField sueldoPorDia;


    @FXML
    private SplitMenuButton departamento;
    @FXML
    private SplitMenuButton turno;
    @FXML
    private Button agre;

    @FXML
    private javafx.scene.control.Button reg; // Botón regresar, pedido explícito



    public EnfermeroController() throws Exception {
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
            alert.setContentText("Por favor, ingresa un ID de enfermero.");
            alert.showAndWait();
            return;
        }

        try {
            Enfermero enfermero = enfermeroDao.consultar(id);
            if (enfermero != null) {
                String enfermeroInfo = String.format(
                        "ID: %s%nNombre: %s%nCURP: %s%nCédula: %s%nRFC: %s%nSueldo: %.2f%nID Departamento: %s%nID Turno: %d",
                        enfermero.getIdEnfermero(),
                        enfermero.getNombre(),
                        enfermero.getCurp(),
                        enfermero.getCedula(),
                        enfermero.getRfc(),
                        enfermero.getSueldoPorDia(),
                        enfermero.getIdDepartamento(),
                        enfermero.getIdTurno()
                );

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información del Enfermero");
                alert.setHeaderText("Enfermero encontrado");
                alert.setContentText(enfermeroInfo);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enfermero no encontrado");
                alert.setContentText("No se encontró un enfermero con el ID especificado: " + id);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar el enfermero");
            alert.setContentText("Ha ocurrido un error al intentar buscar el enfermero. Por favor, intenta de nuevo.");
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
            alert.setContentText("Por favor, ingresa un ID de enfermero.");
            alert.showAndWait();
            return;
        }

        try {
            Enfermero enfermero = enfermeroDao.consultar(id);
            if (enfermero != null) {
                String enfermeroInfo = String.format(
                        "ID: %s%nNombre: %s%nCURP: %s%nCédula: %s%nRFC: %s%nSueldo: %.2f%nID Departamento: %s%nID Turno: %d",
                        enfermero.getIdEnfermero(),
                        enfermero.getNombre(),
                        enfermero.getCurp(),
                        enfermero.getCedula(),
                        enfermero.getRfc(),
                        enfermero.getSueldoPorDia(),
                        enfermero.getIdDepartamento(),
                        enfermero.getIdTurno()
                );

                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirmación de Eliminación");
                confirmAlert.setHeaderText("Enfermero encontrado");
                confirmAlert.setContentText(enfermeroInfo + "\n\n¿Deseas eliminar este enfermero?");

                ButtonType buttonTypeDelete = new ButtonType("Eliminar");
                ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
                confirmAlert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

                confirmAlert.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeDelete) {
                        try {
                            boolean usu = usuariodao.eliminar(id);
                            if (enfermeroDao.eliminar(id) && usu) {
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Eliminación exitosa");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("El enfermero con ID " + id + " ha sido eliminado correctamente.");
                                successAlert.showAndWait();
                                idbus.clear();
                            } else {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Error");
                                errorAlert.setHeaderText("Error al eliminar");
                                errorAlert.setContentText("El enfermero con ID " + id + " no pudo ser eliminado. Por favor, intente nuevamente.");
                                errorAlert.showAndWait();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText("Error al eliminar el enfermero");
                            errorAlert.setContentText("Ha ocurrido un error al intentar eliminar el enfermero. Por favor, intenta de nuevo.");
                            errorAlert.showAndWait();
                        }
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enfermero no encontrado");
                alert.setContentText("No se encontró un enfermero con el ID especificado: " + id);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar el enfermero");
            alert.setContentText("Ha ocurrido un error al intentar buscar el enfermero. Por favor, intenta de nuevo.");
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
            Enfermero enfermero = enfermeroDao.consultar(id);
            if (enfermero != null) {
                nombre.setText(enfermero.getNombre());
                curp.setText(enfermero.getCurp());
                cedula.setText(enfermero.getCedula());
                rfc.setText(enfermero.getRfc());
                sueldoPorDia.setText(String.valueOf(enfermero.getSueldoPorDia()));
                Departamento departamento1 = departamentoDao.consultar(enfermero.getIdDepartamento());
                Turno turno1 = turnoDao.consultar(enfermero.getIdTurno() + "");
                departamento.setText(departamento1.getDescripcion());
                turno.setText(turno1.getDescripcion());

                // Disable specific fields & enable others for editing
                curp.setDisable(true);
                cedula.setDisable(true);
                rfc.setDisable(true);

                nombre.setDisable(false);
                sueldoPorDia.setDisable(false);
                departamento.setDisable(false);
                turno.setDisable(false);

                idbus.setDisable(true); // Disable ID field
                agre.setDisable(false); // Enable the button
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enfermero no encontrado");
                alert.setContentText("No se encontró un enfermero con el ID especificado: " + id);
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
            String idEnfermero = idbus.getText().trim();
            String nombreText = nombre.getText().trim();
            String sueldoText = sueldoPorDia.getText().trim();
            String departamentoText = departamento.getText();
            String turnoText = turno.getText();

            if (idEnfermero.isEmpty() || nombreText.isEmpty() || sueldoText.isEmpty() || departamentoText == null || turnoText == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Campos vacíos");
                alert.setContentText("Por favor, llena todos los campos obligatorios.");
                alert.showAndWait();
                return;
            }

            double sueldo = Double.parseDouble(sueldoText);

            String idDepartamento = buscarIdDepartamentoPorDescripcion(departamentoText);
            int idTurno = buscarIdTurnoPorDescripcion(turnoText);

            // Create an Enfermero object and attempt the update
            Enfermero enfermero = new Enfermero(idEnfermero, nombreText, curp.getText(), cedula.getText(), rfc.getText(), sueldo, idTurno, idDepartamento);
            boolean updated = enfermeroDao.modificar(enfermero);

            if (updated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Enfermero actualizado correctamente.");
                alert.showAndWait();

                // Re-enable fields, clear inputs, and refresh table
                idbus.setDisable(false);
                nombre.setDisable(true);
                sueldoPorDia.setDisable(true);

                departamento.setDisable(true);
                turno.setDisable(true);
                idbus.clear();
                nombre.clear();
                sueldoPorDia.clear();

                departamento.setText("Departamento");
                turno.setText("Turno");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Actualización fallida");
                alert.setContentText("No se pudo actualizar el registro del enfermero.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entrada no válida");
            alert.setContentText("Sueldo debe ser un valor numérico válido.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error inesperado");
            alert.setContentText("Ha ocurrido un error al intentar modificar el enfermero.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        idad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idEnfermero"));
        nombread.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        curpd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("curp"));
        cedulad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("cedula"));
        rfcd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("rfc"));
        sueldo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("sueldoPorDia"));
        iddep.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idDepartamento"));
        idturno.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idTurno"));
    }
    private void listarDoctoresEnTabla() {
        ObservableList<Enfermero> lista = enfermeroDao.listar();
        tablaAdmin.setItems(lista);
    }



    @FXML
    protected void agregar() {
        try {
            String nombreText = nombre.getText().trim();
            String curpText = curp.getText().trim();
            String cedulaText = cedula.getText().trim();
            String rfcText = rfc.getText().trim();
            String sueldoText = sueldoPorDia.getText().trim();

            String departamentoText = departamento.getText();
            String turnoText = turno.getText();

            if (nombreText.isEmpty() || curpText.isEmpty() || cedulaText.isEmpty() || rfcText.isEmpty()
                    || sueldoText.isEmpty() || departamentoText == null || turnoText == null) {
                System.out.println("Debes llenar todos los campos");
                return;
            }

            double sueldo = Double.parseDouble(sueldoText);

            // Buscar IDs por nombres seleccionados en los SplitMenuButton
            String idDepartamento = buscarIdDepartamentoPorDescripcion(departamentoText);
            int idTurno = buscarIdTurnoPorDescripcion(turnoText);

            // Generar el nuevo ID del doctor
            String nuevoIdEnfermero = generarNuevoIdEnfermero();

            // Crear objeto Doctor completo
            Enfermero enfermero = new Enfermero(nuevoIdEnfermero, nombreText, curpText, cedulaText, rfcText, sueldo, idTurno, idDepartamento);

            // Insertar el doctor
            Usuario usuario = new Usuario(rfcText,rfcText+"/*/*",nuevoIdEnfermero,3);
            boolean usu= usuariodao.insertar(usuario);
            if (enfermeroDao.insertar(enfermero)&&usu) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Éxito");
                alert.setContentText("Enfermero agregado correctamente con ID: " + nuevoIdEnfermero);
                alert.showAndWait();

                // Clear input fields after successful addition
                nombre.clear();
                curp.clear();
                cedula.clear();
                rfc.clear();
                sueldoPorDia.clear();
                departamento.setText("Departamento");
                turno.setText("Turno");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al agregar el doctor");
                alert.setContentText("No se pudo agregar el enfemero. Por favor, verifica los datos e intenta nuevamente.");
                alert.showAndWait();
            }


        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entrada no válida");
            alert.setContentText("Sueldo por día debe ser valores numéricos válidos");
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

    private String generarNuevoIdEnfermero() {
        String nuevoId = "ENF0000001";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idEnfermero FROM enfermero ORDER BY idEnfermero DESC LIMIT 1")) {
            if (rs.next()) {
                String ultimoId = rs.getString("idDoctor");
                int num = Integer.parseInt(ultimoId.substring(3)) + 1;
                nuevoId = String.format("ENF%07d", num);
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
