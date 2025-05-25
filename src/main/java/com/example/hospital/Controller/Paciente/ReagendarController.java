package com.example.hospital.Controller.Paciente;

import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Tablas.CitaMedica;
import com.example.hospital.Vista.Paciente.CitasView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.example.hospital.Model.ConexioBD.connection;

public class ReagendarController {

    private final Dao<CitaMedica> citaMedicaDao = (Dao<CitaMedica>) DaoFactory.CITA.crear(connection);
    private String nombre;
    private String idPaciente;

    @FXML
    private Button cerrar;
    @FXML
    private Button boton;
    @FXML
    private TextField idCita;
    @FXML
    private DatePicker fechaPicker;
    @FXML
    private SplitMenuButton horaSplit;

    private String horaSeleccionada;
    private CitaMedica citaActual;


    public void setText(String text, String id) {
        nombre = text;
        idPaciente = id;
    }

    @FXML
    private void regresar() throws IOException {
        CitasView v = new CitasView();
        v.mostrarCitas(nombre, idPaciente);
        cerrar.getScene().getWindow().hide();
    }

    @FXML
    private void reagendar() {
        if (boton.getText().equals("Buscar")) {
            if (idCita.getText().isEmpty()) {
                mostrarAlerta("Debe ingresar el ID de la cita.");
                return;
            }

            citaActual = citaMedicaDao.consultar(idCita.getText());
            if (citaActual == null) {
                mostrarAlerta("La cita no existe.");
                return;
            }

            LocalDate fechaCita = LocalDate.parse(citaActual.getFecha().toString());
            LocalDate hoy = LocalDate.now();

            long diasDiferencia = ChronoUnit.DAYS.between(hoy, fechaCita);

            if (diasDiferencia <= 1) {
                mostrarAlerta("La cita no puede reagendarse porque falta 1 día o menos.");
                return;
            }

            // Permitir la edición
            fechaPicker.setDisable(false);
            horaSplit.setDisable(false);
            boton.setText("Guardar");

            // Rellenar campos
            fechaPicker.setValue(fechaCita);
            cargarHorasDisponibles(fechaCita, citaActual.getIdDepartamento());
            horaSplit.setText(citaActual.getHora().toString());

            mostrarAlerta("Ahora puede modificar la fecha y hora.");
        } else if (boton.getText().equals("Guardar")) {
            // Guardar la nueva fecha y hora
            LocalDate nuevaFecha = fechaPicker.getValue();

            if (nuevaFecha == null || horaSeleccionada == null) {
                mostrarAlerta("Debe seleccionar una nueva fecha y hora.");
                return;
            }

            citaActual.setFecha(nuevaFecha.toString());
            citaActual.setHora(horaSeleccionada);

            boolean actualizado = citaMedicaDao.modificar(citaActual);
            if (actualizado) {
                mostrarAlerta("Cita reagendada exitosamente.");
                limpiarFormulario();
            } else {
                mostrarAlerta("Error al reagendar la cita.");
            }
        }
    }

    private void cargarHorasDisponibles(LocalDate fecha, String idDepartamento) {
        List<LocalTime> todasLasHoras = generarHorasPosibles();
        List<LocalTime> ocupadas = obtenerHorasOcupadas(fecha, idDepartamento);

        horaSplit.getItems().clear();

        for (LocalTime hora : todasLasHoras) {
            if (!ocupadas.contains(hora)) {
                MenuItem item = new MenuItem(hora.toString());
                item.setOnAction(e -> {
                    horaSeleccionada = hora.toString();
                    horaSplit.setText(horaSeleccionada);
                });
                horaSplit.getItems().add(item);
            }
        }
    }

    private List<LocalTime> generarHorasPosibles() {
        List<LocalTime> horas = new ArrayList<>();
        LocalTime hora = LocalTime.of(8, 0); // 08:00
        LocalTime fin = LocalTime.of(17, 0); // 17:00

        while (hora.isBefore(fin)) {
            horas.add(hora);
            hora = hora.plusMinutes(30);
        }
        return horas;
    }

    private List<LocalTime> obtenerHorasOcupadas(LocalDate fecha, String idDepartamento) {
        List<LocalTime> horasOcupadas = new ArrayList<>();
        String sql = "SELECT hora FROM citamedica WHERE fecha = ? AND iddepartamento = ? AND idcita <> ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, idDepartamento);
            stmt.setString(3, citaActual.getIdCita());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                horasOcupadas.add(rs.getTime("hora").toLocalTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horasOcupadas;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarFormulario() {
        idCita.clear();
        fechaPicker.setValue(null);
        horaSplit.setText("Seleccionar hora");
        horaSplit.getItems().clear();
        fechaPicker.setDisable(true);
        horaSplit.setDisable(true);
        boton.setText("Buscar");
    }
}
