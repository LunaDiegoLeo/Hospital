package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Daos.DaoFactory;
import com.example.hospital.Model.Daos.UsuarioDao;
import com.example.hospital.Model.Tablas.Adminis;
import com.example.hospital.Model.Tablas.Paciente;
import com.example.hospital.Model.Tablas.Seguro;
import com.example.hospital.Model.Tablas.Usuario;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteController {
    private Connection connection= ConexioBD.getConnection();
    private Dao<Paciente> pacienteDaoDao = (Dao<Paciente>) DaoFactory.PACIENTE.crear(connection);
    private Dao<Usuario> usuariodao = (Dao<Usuario>) DaoFactory.USUARIO.crear(connection);
    private Dao<Seguro> seguroDao = (Dao<Seguro>) DaoFactory.SEGURO.crear(connection);


    @FXML
    private TextField nombre;
    @FXML
    private TextField curp;
    @FXML
    private SplitMenuButton sexo;
    @FXML
    private TextField edad;
    @FXML
    private TextField altura;
    @FXML
    private TextField peso;
    @FXML
    private TextField telefono;
    @FXML
    private SplitMenuButton seguros;
    @FXML
    private DatePicker datapicker;
    @FXML
    private Button reg;
    
    @FXML
    private TableColumn<Paciente, String> idad;
    @FXML
    private TableColumn<Paciente, String> nombread;
    @FXML
    private TableView<Paciente> tablaAdmin;
    @FXML
    private TableColumn<Paciente, String> sexod;
    @FXML
    private TableColumn<Paciente, String> telfonod;
    @FXML
    private TableColumn<Paciente, String> edadd;
    @FXML
    private TableColumn<Paciente, String> alturad;
    @FXML
    private TableColumn<Paciente, String> pesod;
    @FXML
    private TableColumn<Paciente, String> FechaNacd;
    @FXML
    private TableColumn<Paciente, String> curdd;
    @FXML
    private TableColumn<Paciente, String> segurod;
    @FXML
    private Button agre;
    

    private String nombre1;
    private String fxml;

    public PacienteController() throws Exception {
    }

    public void setText(String text, String fxml) {
        this.nombre1=text;
        this.fxml=fxml+"-view.fxml";
    }


    @FXML
    protected void regresar() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar(fxml,nombre1);
            reg.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void agregar() {
        try {
            String idPaciente = generarIdPaciente();
            String nombrePaciente = nombre.getText();
            String curpPaciente = curp.getText();
            String sexoPaciente = sexo.getText();
            String edadPaciente = edad.getText();
            String alturaPaciente = altura.getText();
            String pesoPaciente = peso.getText();
            String telefonoPaciente = telefono.getText();
            String fechaNacimiento = datapicker.getValue() != null ? datapicker.getValue().toString() : "";
            String idSeguro = seguros.getText();
            Usuario usuario = new Usuario(curpPaciente, curpPaciente+"*/*/", idPaciente, 2);
            // Validaciones básicas
            if (nombrePaciente.isEmpty() || curpPaciente.isEmpty() || sexoPaciente.isEmpty() || edadPaciente.isEmpty() ||
                    alturaPaciente.isEmpty() || pesoPaciente.isEmpty() || telefonoPaciente.isEmpty() ||
                    fechaNacimiento.isEmpty() || idSeguro.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Debe de llenar todos los campos");
                alert.showAndWait();

                return;
            }
            if (sexoPaciente.equals("Selecciona sexo")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Debe seleccionar un sexo");
                alert.showAndWait();
                return;
            }
            if (idSeguro.equals("Selecciona seguro")) {
                idSeguro=null;
            }

            boolean b1= usuariodao.insertar(usuario);
            if (b1==false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("El paciente ya existe");
                alert.showAndWait();
                return;
            }

            Paciente paciente = new Paciente(
                    idPaciente,
                    nombrePaciente,
                    sexoPaciente,
                    telefonoPaciente,
                    Integer.parseInt(edadPaciente),
                    Double.parseDouble(alturaPaciente),
                    Double.parseDouble(pesoPaciente),
                    fechaNacimiento,
                    curpPaciente,
                    idSeguro
            );

            if (pacienteDaoDao.insertar(paciente)) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Agregado");
               alert.setContentText("Se ha agregado el paciente con exito con un id de: "+idPaciente+"\nY nombre de usuario: "+curpPaciente);
               alert.showAndWait();
                limpiarCampos();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Ha ocurrido un error al insertar el paciente");
                alert.showAndWait();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        nombre.clear();
        if (curp!=null) {
            curp.clear();
        }
        sexo.setText("Selecciona sexo");
        edad.clear();
        altura.clear();
        peso.clear();
        telefono.clear();
        seguros.setText("Selecciona seguro");
        datapicker.setValue(null);
    }


    @FXML
    public void initialize() {
        if (tablaAdmin != null) {
            // Configurar columnas
            idad.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
            nombread.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            sexod.setCellValueFactory(new PropertyValueFactory<>("sexo"));
            telfonod.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            edadd.setCellValueFactory(new PropertyValueFactory<>("edad"));
            alturad.setCellValueFactory(new PropertyValueFactory<>("altura"));
            pesod.setCellValueFactory(new PropertyValueFactory<>("peso"));
            FechaNacd.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            curdd.setCellValueFactory(new PropertyValueFactory<>("curp"));
            segurod.setCellValueFactory(new PropertyValueFactory<>("idSeguro"));

            // Llenar tabla
            cargarPacientes();
        }


        // También inicializas los seguros
        if (seguros != null) {
            try {
                seguroDao.listar().forEach(seguro -> {
                    MenuItem item = new MenuItem(seguro.getId());
                    item.setOnAction(e -> seguros.setText(seguro.getId()));
                    seguros.getItems().add(item);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    private TextField idbus;

    @FXML
    private void buscar() {
        try {
            String idPaciente = idbus.getText().trim();

            if (idPaciente.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID vacío");
                alert.setContentText("Por favor, introduzca un ID de paciente.");
                alert.showAndWait();
                return;
            }

            Paciente paciente = pacienteDaoDao.consultar(idPaciente);

            if (paciente == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Paciente no encontrado");
                alert.setContentText("No se encontró un paciente con el ID proporcionado.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Paciente encontrado");
                alert.setHeaderText("Información del paciente:");
                alert.setContentText(
                        "ID: " + paciente.getIdPaciente() + "\n" +
                                "Nombre: " + paciente.getNombre() + "\n" +
                                "Sexo: " + paciente.getSexo() + "\n" +
                                "Teléfono: " + paciente.getTelefono() + "\n" +
                                "Edad: " + paciente.getEdad() + "\n" +
                                "Altura: " + paciente.getAltura() + " cm\n" +
                                "Peso: " + paciente.getPeso() + " kg\n" +
                                "Fecha de nacimiento: " + paciente.getFechaNacimiento() + "\n" +
                                "CURP: " + paciente.getCurp() + "\n" +
                                "Seguro: " + (paciente.getIdSeguro() != null ? paciente.getIdSeguro() : "No asignado")
                );
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error en la búsqueda");
            alert.setContentText("Ocurrió un error al realizar la búsqueda del paciente.");
            alert.showAndWait();
        }
    }


    @FXML
    private void cargarPacientes() {
        try {
            ObservableList<Paciente> listaPacientes = pacienteDaoDao.listar();
            tablaAdmin.setItems(listaPacientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminar() {
        try {
            String idPaciente = idbus.getText().trim();

            if (idPaciente.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID vacío");
                alert.setContentText("Por favor, introduzca un ID de paciente.");
                alert.showAndWait();
                return;
            }

            Paciente paciente = pacienteDaoDao.consultar(idPaciente);

            if (paciente == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Paciente no encontrado");
                alert.setContentText("No se encontró un paciente con el ID proporcionado.");
                alert.showAndWait();
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmación");
            confirmAlert.setHeaderText("Eliminar paciente");
            confirmAlert.setContentText("¿Está seguro de que desea eliminar al paciente con la siguiente información?\n\n" +
                    "ID: " + paciente.getIdPaciente() + "\n" +
                    "Nombre: " + paciente.getNombre() + "\n" +
                    "Sexo: " + paciente.getSexo() + "\n" +
                    "Teléfono: " + paciente.getTelefono() + "\n" +
                    "Edad: " + paciente.getEdad() + "\n" +
                    "Altura: " + paciente.getAltura() + " cm\n" +
                    "Peso: " + paciente.getPeso() + " kg\n" +
                    "Fecha de nacimiento: " + paciente.getFechaNacimiento() + "\n" +
                    "CURP: " + paciente.getCurp() + "\n" +
                    "Seguro: " + (paciente.getIdSeguro() != null ? paciente.getIdSeguro() : "No asignado"));

            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (pacienteDaoDao.eliminar(idPaciente)&& usuariodao.eliminar(paciente.getCurp())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText("Paciente eliminado");
                    alert.setContentText("El paciente con ID " + idPaciente + " ha sido eliminado exitosamente.");
                    alert.showAndWait();
                    idbus.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error al eliminar");
                    alert.setContentText("Ocurrió un error al intentar eliminar al paciente.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar paciente");
            alert.setContentText("Ha ocurrido un error inesperado.");
            alert.showAndWait();
        }
    }

    @FXML
    private void buscar2() {
        try {
            String idPaciente = idbus.getText().trim();

            if (idPaciente.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID vacío");
                alert.setContentText("Por favor, introduzca un ID de paciente.");
                alert.showAndWait();
                return;
            }

            Paciente paciente = pacienteDaoDao.consultar(idPaciente);

            if (paciente == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Paciente no encontrado");
                alert.setContentText("No se encontró un paciente con el ID proporcionado.");
                alert.showAndWait();
            } else {
                // Populate fields
                nombre.setText(paciente.getNombre());
                sexo.setText(paciente.getSexo());
                edad.setText(String.valueOf(paciente.getEdad()));
                altura.setText(String.valueOf(paciente.getAltura()));
                peso.setText(String.valueOf(paciente.getPeso()));
                telefono.setText(paciente.getTelefono());
                seguros.setText(paciente.getIdSeguro() != null ? paciente.getIdSeguro() : "Selecciona seguro");
                datapicker.setValue(paciente.getFechaNacimiento() != null ? java.time.LocalDate.parse(paciente.getFechaNacimiento()) : null);

                // Enable fields for editing
                nombre.setDisable(false);
                sexo.setDisable(false);
                edad.setDisable(false);
                altura.setDisable(false);
                peso.setDisable(false);
                telefono.setDisable(false);
                seguros.setDisable(false);
                datapicker.setDisable(false);
                idbus.setDisable(true);
                agre.setDisable(false);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Paciente encontrado");
                alert.setContentText("El paciente ha sido cargado y los campos están habilitados para edición.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al buscar paciente");
            alert.setContentText("Ha ocurrido un error inesperado.");
            alert.showAndWait();
        }
    }

    @FXML
    private void modificar() {
        try {
            String idPaciente = idbus.getText().trim();
            Paciente paciente = pacienteDaoDao.consultar(idPaciente);
            if (paciente == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Modificación fallida");
                alert.setContentText("No se encontró un paciente con el ID proporcionado.");
                alert.showAndWait();
                return;
            }

            String nombrePaciente = nombre.getText();
            String sexoPaciente = sexo.getText();
            String edadPaciente = edad.getText();
            String alturaPaciente = altura.getText();
            String pesoPaciente = peso.getText();
            String telefonoPaciente = telefono.getText();
            String fechaNacimiento = datapicker.getValue() != null ? datapicker.getValue().toString() : "";
            String idSeguro = seguros.getText();

// Validations
            if (nombrePaciente.isEmpty() || sexoPaciente.isEmpty() || edadPaciente.isEmpty() ||
                    alturaPaciente.isEmpty() || pesoPaciente.isEmpty() || telefonoPaciente.isEmpty() || fechaNacimiento.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Modificación fallida");
                alert.setContentText("Debe llenar todos los campos obligatorios.");
                alert.showAndWait();
                return;
            }

            if (sexoPaciente.equals("Selecciona sexo")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Modificación fallida");
                alert.setContentText("Debe seleccionar un sexo válido.");
                alert.showAndWait();
                return;
            }

            if (idSeguro.equals("Selecciona seguro")) {
                idSeguro = null;
            }

// Set updated values to paciente object
            paciente.setNombre(nombrePaciente);
            paciente.setSexo(sexoPaciente);
            paciente.setEdad(Integer.parseInt(edadPaciente));
            paciente.setAltura(Double.parseDouble(alturaPaciente));
            paciente.setPeso(Double.parseDouble(pesoPaciente));
            paciente.setTelefono(telefonoPaciente);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setIdSeguro(idSeguro);

            // Build updated Paciente object
            Paciente updatedPaciente = new Paciente(
                    idPaciente,
                    nombrePaciente,
                    sexoPaciente,
                    telefonoPaciente,
                    Integer.parseInt(edadPaciente),
                    Double.parseDouble(alturaPaciente),
                    Double.parseDouble(pesoPaciente),
                    fechaNacimiento,
                    paciente.getCurp(),
                    idSeguro
            );
            
            // Update information in the database
            if (pacienteDaoDao.modificar(updatedPaciente)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Modificación exitosa");
                alert.setContentText("La información del paciente ha sido actualizada correctamente.");
                alert.showAndWait();
                limpiarCampos();
                idbus.setDisable(false); 
                idbus.clear();
                nombre.setDisable(true);
                sexo.setDisable(true);
                edad.setDisable(true);
                altura.setDisable(true);
                peso.setDisable(true);
                telefono.setDisable(true);
                seguros.setDisable(true);
                datapicker.setDisable(true);
                idbus.setDisable(false);
                agre.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al modificar");
                alert.setContentText("Ocurrió un error al modificar la información del paciente.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al modificar paciente");
            alert.setContentText("Ha ocurrido un error inesperado.");
            alert.showAndWait();
        }
    }


    private String generarIdPaciente() {
        String sql = "SELECT Idpaciente FROM paciente ORDER BY Idpaciente DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("Idpaciente");
                int num = Integer.parseInt(lastId.substring(3));
                return String.format("PAC%07d", num + 1);
            } else {
                return "PAC0000001";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
