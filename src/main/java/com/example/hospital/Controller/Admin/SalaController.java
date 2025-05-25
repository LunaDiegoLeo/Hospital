package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.*;
import com.example.hospital.Model.Tablas.Departamento;
import com.example.hospital.Model.Tablas.Sala;
import com.example.hospital.Model.Tablas.TipoSala;
import com.example.hospital.Vista.Admin.GenericView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaController {
    private Connection connection= ConexioBD.getConnection();

    private SalaDao salaDao = (SalaDao) DaoFactory.SALA.crear(connection);
    private TipoSalaDao tipoSalaDao = (TipoSalaDao) DaoFactory.TIPOSALA.crear(connection);
    private DepartamentoDao departamentoDao = (DepartamentoDao) DaoFactory.DEPARTAMENTO.crear(connection);


    @FXML
    private javafx.scene.control.Button reg;
    private String nombre1;
    private String fxml;
    @FXML
    private SplitMenuButton depas;
    @FXML
    private SplitMenuButton tipos;

    @FXML
    private TableView<Sala> tablaAdmin;
    @FXML
    private TableColumn<Sala, String> idad;
    @FXML
    private TableColumn<Sala, String> numerod;
    @FXML
    private TableColumn<Sala, String> desd;

    @FXML
    public void initialize() {
        if (depas!=null){
            cargarDepartamentos();
        }
        if (tipos!=null){
            cargarTipoSalas();
        }


        if (tablaAdmin != null) {
            inicializarTabla();
            cargarDatosTabla();
        }
    }

    private void inicializarTabla() {
        idad.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdDepartamento()));
        numerod.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getNumero())));
        desd.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTipoSala()));
    }

    private void cargarDatosTabla() {
        try {
            tablaAdmin.setItems(salaDao.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField idbus;

    @FXML
    private void buscar() {
        try {
            String entrada = idbus.getText().trim();

            if (entrada.isEmpty() || !entrada.contains("-")) {
                mostrarAlertaError("Formato inválido. Debe ser: DEP01-1");
                return;
            }

            // El DAO espera el formato DEP01-1, así que se manda tal cual
            Sala sala = salaDao.consultar(entrada);

            if (sala != null) {
                mostrarAlertaInfo("Sala encontrada:\nDepartamento: " + sala.getIdDepartamento() +
                        "\nNúmero: " + sala.getNumero() +
                        "\nTipo: " + sala.getTipoSala());
            } else {
                mostrarAlertaError("Sala no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlertaError("Error inesperado: " + e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        try {
            String entrada = idbus.getText().trim();

            if (entrada.isEmpty() || !entrada.contains("-")) {
                mostrarAlertaError("Formato inválido. Debe ser: DEP01-1");
                return;
            }

            Sala sala = salaDao.consultar(entrada);

            if (sala != null) {
                // Confirmación de eliminación
                javafx.scene.control.Alert confirmacion = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmar eliminación");
                confirmacion.setHeaderText("¿Desea eliminar esta sala?");
                confirmacion.setContentText("Departamento: " + sala.getIdDepartamento() +
                        "\nNúmero: " + sala.getNumero() +
                        "\nTipo: " + sala.getTipoSala());

                java.util.Optional<javafx.scene.control.ButtonType> resultado = confirmacion.showAndWait();

                if (resultado.isPresent() && resultado.get() == javafx.scene.control.ButtonType.OK) {
                    boolean eliminado = salaDao.eliminar(entrada);
                    if (eliminado) {
                        mostrarAlertaInfo("Sala eliminada exitosamente.");
                        idbus.setText("");
                    } else {
                        mostrarAlertaError("Error al eliminar la sala.");
                    }
                }
            } else {
                mostrarAlertaError("Sala no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlertaError("Error inesperado: " + e.getMessage());
        }
    }


    private void cargarDepartamentos() {
        departamentos = javafx.collections.FXCollections.observableArrayList();
        departamentoDao.listar().forEach(dep -> {
            String depText = dep.getIdDepartamento() + " - " + dep.getDescripcion();
            departamentos.add(depText);

            javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(depText);
            item.setOnAction(e -> depas.setText(depText));
            depas.getItems().add(item);
        });
    }

    private void cargarTipoSalas() {
        tipoSalas = javafx.collections.FXCollections.observableArrayList();
        tipoSalaDao.listar().forEach(ts -> {
            String tipoText = ts.getIdTipoSala() + " - " + ts.getDescripcion();
            tipoSalas.add(tipoText);

            javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(tipoText);
            item.setOnAction(e -> tipos.setText(tipoText));
            tipos.getItems().add(item);
        });
    }

    @FXML
    private Button agre;
    @FXML
    private void agregar() {
        try {
            String depaText = depas.getText();
            String tipoText = tipos.getText();

            if (depaText == null || depaText.isEmpty() || tipoText == null || tipoText.isEmpty()) {
                mostrarAlertaError("Debes seleccionar un Departamento y un Tipo de Sala.");
                return;
            }

            // Extraer IDDepartamento y descripcion del tipo sala
            String idDepartamento = depaText.split(" - ")[0];
            String descripcionTipoSala = tipoText.split(" - ")[1];

            // Buscar el TipoSala real
            if (descripcionTipoSala == null) {
                mostrarAlertaError("Tipo de sala no encontrado.");
                return;
            }

            // Obtener siguiente numero de sala en el departamento
            int numero = obtenerSiguienteNumeroSala(idDepartamento);

            // Crear sala y guardar
            Sala sala = new Sala(idDepartamento, numero, descripcionTipoSala.trim());

            if (salaDao.insertar(sala)) {
                mostrarAlertaInfo("Sala agregada correctamente:\nDepartamento: " + idDepartamento +
                        "\nNúmero: " + numero + "\nLlave primaria:"+idDepartamento+"-"+numero+"\nTipo: " + tipoText);
                depas.setText("Selecciona");
                tipos.setText("Selecciona");

            } else {
                mostrarAlertaError("Error al agregar la sala.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlertaError("Error inesperado: " + e.getMessage());
        }
    }

    public int obtenerSiguienteNumeroSala(String idDepartamento) {
        int siguienteNumero = 1; // Por defecto si no hay ninguna sala
        String sql = "SELECT MAX(Numero) AS max_numero FROM Sala WHERE IDDepartamento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDepartamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int maxNumero = rs.getInt("max_numero");
                if (maxNumero > 0) {
                    siguienteNumero = maxNumero + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siguienteNumero;
    }


    private void mostrarAlertaError(String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarAlertaInfo(String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("Éxito");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private Sala salaSeleccionada = null;


    @FXML
    private void buscar2() {
        try {
            String entrada = idbus.getText().trim();

            if (entrada.isEmpty() || !entrada.contains("-")) {
                mostrarAlertaError("Formato inválido. Debe ser: DEP01-1");
                return;
            }

            Sala sala = salaDao.consultar(entrada);

            if (sala != null) {
                salaSeleccionada = sala;

                mostrarAlertaInfo("Sala encontrada:\nDepartamento: " + sala.getIdDepartamento() +
                        "\nNúmero: " + sala.getNumero() +
                        "\nTipo: " + sala.getTipoSala());

                Departamento dep = departamentoDao.consultar(sala.getIdDepartamento());
                depas.setText(sala.getIdDepartamento() + " - " + dep.getDescripcion());
                TipoSala ts = consultarPorNombre(sala.getTipoSala());
                // Establecer en SplitMenuButton el tipo de sala (ya es una String descripción)
                tipos.setText(ts.getIdTipoSala()+" - "+sala.getTipoSala());

                depas.setDisable(false);
                tipos.setDisable(false);
                idbus.setDisable(true);
                agre.setText("Actualizar");

                // Cambiar comportamiento del botón AGRE a modo actualización
                agre.setOnAction(event -> {
                    try {
                        // Eliminar la sala anterior
                        if (salaDao.eliminar(salaSeleccionada.getIdDepartamento() + "-" + salaSeleccionada.getNumero())) {
                            // Extraer nuevos valores desde los SplitMenuButton
                            String nuevoDep = depas.getText().split(" - ")[0];
                            String tipoDesc = tipos.getText().split(" - ")[1];


                            Sala nuevaSala = new Sala(nuevoDep, salaSeleccionada.getNumero(), tipoDesc);

                            if (salaDao.insertar(nuevaSala)) {
                                mostrarAlertaInfo("Sala actualizada exitosamente.");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Nueva sala");
                                alert.setHeaderText("Sala actualizada exitosamente.");
                                TipoSala ts2 = consultarPorNombre(tipoDesc);
                                alert.setContentText("Departamento: " + nuevoDep + "\nNúmero: " + salaSeleccionada.getNumero() +
                                        "\nNuevo ID: " + nuevoDep + "-" + salaSeleccionada.getNumero() +
                                        "\nTipo: " + tipoDesc +
                                        "\nID Tipo de Sala: " + ts2.getIdTipoSala());
                                alert.showAndWait();
                                agre.setText("Buscar");
                                idbus.setText("");
                                idbus.setDisable(false);
                                depas.setDisable(true);
                                tipos.setDisable(true);
                                limpiarCampos();
                                agre.setOnAction(evt -> buscar2()); // Restaurar comportamiento original
                            } else {
                                mostrarAlertaError("Error al agregar la nueva sala.");
                            }
                        } else {
                            mostrarAlertaError("No se pudo eliminar la sala original.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        mostrarAlertaError("Error al actualizar: " + ex.getMessage());
                    }
                });

            } else {
                mostrarAlertaError("Sala no encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlertaError("Error inesperado: " + e.getMessage());
        }
    }
    private void limpiarCampos() {
        depas.setText("Selecciona");
        tipos.setText("Selecciona");
        idbus.setText("");
        agre.setText("Buscar");
    }





    public TipoSala consultarPorNombre(String descripcion) {
        String sql = "SELECT TipoSala, Descripcion FROM TipoSala WHERE Descripcion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, descripcion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TipoSala(rs.getInt("TipoSala"), rs.getString("Descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    private javafx.collections.ObservableList<String> departamentos;
    private javafx.collections.ObservableList<String> tipoSalas;

    public SalaController() throws Exception {
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
    public void setText(String text, String fxml) {
        this.nombre1 = text;
        this.fxml = fxml + "-view.fxml";
    }

    
}
