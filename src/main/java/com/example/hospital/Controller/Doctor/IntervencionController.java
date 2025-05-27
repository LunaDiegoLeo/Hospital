package com.example.hospital.Controller.Doctor;

import com.example.hospital.Model.CalculadorCosto;
import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Model.CostoStrategy2;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.IntervencionDao;
import com.example.hospital.Model.Daos.SeIntervieneDao;
import com.example.hospital.Model.Tablas.Intervencion;
import com.example.hospital.Model.Tablas.Paciente;
import com.example.hospital.Model.Tablas.SeInterviene;
import com.example.hospital.Vista.Doctor.DoctorView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;

import java.sql.Connection;
import java.util.Arrays;

import static com.example.hospital.Model.ConexioBD.connection;

public class IntervencionController {

    private String nombredoc;
    private String iddoc;
    private String idPaciente;

    @FXML
    private TextField idCita;
    @FXML
    private SplitMenuButton horaSplit;
    @FXML
    private Button boton;

    private IntervencionDao intervencionDao = (IntervencionDao) DaoFactory.INTERVENCION.crear(connection);
    private SeIntervieneDao seIntervieneDao = (SeIntervieneDao) DaoFactory.SEINTERVIENE.crear(connection);

    private Intervencion intervencionSeleccionada;

    public void setText(String nombre, String id, String idpaciente) {
        this.nombredoc = nombre;
        this.iddoc = id;
        this.idPaciente = idpaciente;
        idCita.setText(idpaciente);
        idCita.setEditable(false);
        horaSplit.setDisable(false);
        cargarInte();
    }

    @FXML
    private void intervenir() {
        if (intervencionSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Intervención no seleccionada", "Por favor, selecciona una intervención antes de continuar.");
            return;
        }
        CalculadorCosto calculador = new CalculadorCosto(
                20000,
                Arrays.asList(
                        new CostoStrategy2.CostoPorSeguroStrategy(),
                        new CostoStrategy2.CostoPorEdadStrategy(),
                        new CostoStrategy2.CostoPorPesoStrategy()
                )
        );
        Paciente pac= (Paciente) DaoFactory.PACIENTE.crear(connection).consultar(idPaciente);
        double costo= calculador.calcularCostoFinal(pac);

        SeInterviene seInterviene = new SeInterviene(
                iddoc,
                idPaciente,
                intervencionSeleccionada.getIdIntervencion(),
                costo
        );

        boolean exito = seIntervieneDao.insertar(seInterviene);
        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Se registró la intervención correctamente.\n\nEl costó sera de: "+costo+"\n\nPuede pagar en recepción\n\n| | | | || || ||");
            horaSplit.setText("Seleccione una intervencion");
            intervencionSeleccionada = null;
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al registrar la intervención.");
        }
    }

    @FXML
    private void regresar() throws Exception {
        DoctorView view = new DoctorView();
        view.mostrar(nombredoc, iddoc);
        boton.getScene().getWindow().hide();
    }

    public void cargarInte() {
        ObservableList<Intervencion> intervenciones = intervencionDao.listar();

        // Limpiar opciones actuales
        horaSplit.getItems().clear();

        for (Intervencion intervencion : intervenciones) {
            String texto = intervencion.getNombre() + " - " + intervencion.getIdIntervencion();
            MenuItem item = new MenuItem(texto);

            // Acción para cuando se selecciona
            item.setOnAction(e -> {
                horaSplit.setText(texto);
                intervencionSeleccionada = intervencion;
            });

            horaSplit.getItems().add(item);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
