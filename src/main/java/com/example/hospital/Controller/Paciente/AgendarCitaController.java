package com.example.hospital.Controller.Paciente;

import com.example.hospital.Model.CostoStrategy;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.Tablas.CalculadorDeCosto;
import com.example.hospital.Model.Tablas.CitaMedica;
import com.example.hospital.Model.Tablas.Departamento;
import com.example.hospital.Vista.Paciente.CitasView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.example.hospital.Model.ConexioBD.connection;

public class AgendarCitaController {

    @FXML private TextField idPacienteField;
    @FXML private DatePicker fechaPicker;
    @FXML private SplitMenuButton departamentoSplit;
    @FXML private SplitMenuButton horaSplit;
    @FXML private Button agendarBtn;
    @FXML private Button cerrar;

    private String nombre;
    private String departamentoSeleccionado;
    private String horaSeleccionada;

    private final Dao<Departamento> departamentoDao = (Dao<Departamento>) DaoFactory.DEPARTAMENTO.crear(connection);
    private final Dao<CitaMedica> citaMedicaDao = (Dao<CitaMedica>) DaoFactory.CITA.crear(connection);

    public void initialize() {
        System.out.println(idPacienteField.getText());

        cargarDepartamentos();

        fechaPicker.valueProperty().addListener((obs, oldFecha, nuevaFecha) -> {
            if (nuevaFecha != null && departamentoSeleccionado != null) {
                cargarHorasDisponibles(nuevaFecha, departamentoSeleccionado);
            }
        });
    }

    private void cargarDepartamentos() {
        List<Departamento> departamentos = departamentoDao.listar();
        departamentoSplit.getItems().clear();

        for (Departamento dep : departamentos) {
            MenuItem item = new MenuItem(dep.getDescripcion());
            item.setOnAction(e -> {
                departamentoSeleccionado = dep.getIdDepartamento();
                departamentoSplit.setText(dep.getDescripcion());
                LocalDate fecha = fechaPicker.getValue();
                if (fecha != null) {
                    cargarHorasDisponibles(fecha, departamentoSeleccionado);
                }
            });
            departamentoSplit.getItems().add(item);
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
        LocalTime hora = LocalTime.of(8, 0);
        LocalTime fin = LocalTime.of(17, 0);

        while (hora.isBefore(fin)) {
            horas.add(hora);
            hora = hora.plusMinutes(30);
        }
        return horas;
    }

    private List<LocalTime> obtenerHorasOcupadas(LocalDate fecha, String idDepartamento) {
        List<LocalTime> horasOcupadas = new ArrayList<>();
        String sql = "SELECT hora FROM citamedica WHERE fecha = ? AND iddepartamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, idDepartamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                horasOcupadas.add(rs.getTime("hora").toLocalTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horasOcupadas;
    }

    @FXML
    private void agendarCita() {
        String idPaciente = idPacienteField.getText();
        LocalDate fecha = fechaPicker.getValue();

        if (idPaciente.isEmpty() || fecha == null || horaSeleccionada == null || departamentoSeleccionado == null) {
            mostrarAlerta("Completa todos los campos.");
            return;
        }

        LocalDate hoy = LocalDate.now();

        long diasDiferencia = ChronoUnit.DAYS.between(hoy, fecha);

        if (diasDiferencia <= 0) {
            mostrarAlerta("La cita debe hacerse con al menos un dia de anticipación.");
            return;
        }

        LocalTime hora = LocalTime.parse(horaSeleccionada);
        String idCita = generarIdCitaUnico();

        CitaMedica cita = new CitaMedica();
        cita.setIdCita(idCita);
        cita.setIdPaciente(idPaciente);
        cita.setFecha(fecha.toString());
        cita.setHora(hora.toString());
        cita.setIdDepartamento(departamentoSeleccionado);

        CostoStrategy estrategia = new CostoStrategy.CostoFlexibleStrategy();
        CalculadorDeCosto calc = new CalculadorDeCosto(estrategia);
        double costo = calc.calcular(cita);
        cita.setCosto(costo);

        boolean exito = citaMedicaDao.insertar(cita);
        if (exito) {
            mostrarTicket(cita, costo);
            limpiarFormulario();
        } else {
            mostrarAlerta("Error al agendar cita.");
        }
    }

    private void mostrarTicket(CitaMedica cita, double costoFinal) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Cita Agendada");
        alerta.setHeaderText("Cita registrada exitosamente");

        StringBuilder ticket = new StringBuilder();
        ticket.append("🧾 TICKET DE CITA MÉDICA\n\n");
        ticket.append("ID Cita: ").append(cita.getIdCita()).append("\n");
        ticket.append("Fecha: ").append(cita.getFecha()).append("\n");
        ticket.append("Hora: ").append(cita.getHora()).append("\n");
        ticket.append("ID Paciente: ").append(cita.getIdPaciente()).append("\n");
        ticket.append("Departamento: ").append(cita.getIdDepartamento()).append("\n");
        ticket.append(String.format("Total a pagar: $%.2f\n", costoFinal));
        ticket.append("\nCódigo de barras:\n");
        ticket.append("| | | || || | ||| | || | || | || || | ||| |\n");

        alerta.setContentText(ticket.toString());
        alerta.showAndWait();
    }

    @FXML
    private void regresar() throws IOException {
        CitasView v = new CitasView();
        v.mostrarCitas(nombre, idPacienteField.getText());
        cerrar.getScene().getWindow().hide();
    }

    private void limpiarFormulario() {
        fechaPicker.setValue(null);
        departamentoSplit.setText("Seleccionar departamento");
        horaSplit.setText("Seleccionar hora");
        horaSplit.getItems().clear();
        departamentoSeleccionado = null;
        horaSeleccionada = null;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setText(String text, String id) {
        nombre = text;
        idPacienteField.setText(id);
        idPacienteField.setEditable(false);
    }

    private String generarIdCitaAleatorio() {
        Random random = new Random();
        long numero = 1_000_000_000L + (long)(random.nextDouble() * 8_999_999_999L);
        return "CITA" + String.format("%011d", numero);
    }

    private String generarIdCitaUnico() {
        String nuevoId;
        do {
            nuevoId = generarIdCitaAleatorio();
        } while (existeIdCita(nuevoId));
        return nuevoId;
    }

    private boolean existeIdCita(String id) {
        String sql = "SELECT 1 FROM citamedica WHERE idcita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
