package com.example.hospital.Controller.Doctor;

import com.example.hospital.Model.CalculadorCosto;
import com.example.hospital.Model.CostoStrategy2;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.IntervencionDao;
import com.example.hospital.Model.Daos.MedicamentoDao;
import com.example.hospital.Model.Daos.RecetaDao;
import com.example.hospital.Model.Daos.SeIntervieneDao;
import com.example.hospital.Model.Tablas.*;
import com.example.hospital.Vista.Doctor.DoctorView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

import static com.example.hospital.Model.ConexioBD.connection;

public class RecetarController {

    private String nombredoc;
    private String iddoc;
    private String idPaciente;

    @FXML
    private TextField idCita;
    @FXML
    private TextField idCita1;
    @FXML
    private SplitMenuButton horaSplit;
    @FXML
    private Button boton;

    private RecetaDao intervencionDao = (RecetaDao) DaoFactory.RECETA.crear(connection);
    private MedicamentoDao seIntervieneDao = (MedicamentoDao) DaoFactory.MEDICAMENTO.crear(connection);

    private Medicamento intervencionSeleccionada;

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
            mostrarAlerta(Alert.AlertType.WARNING, "Medicamento no seleccionado", "Por favor, seleccione una medicamento antes de continuar.");
            return;
        }
        if (idCita1.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Debe de ingresar la dosis a tomar", "");
            return;
        }
        Receta receta = new Receta(iddoc,idPaciente,intervencionSeleccionada.getIdMedicamento(),idCita1.getText(),"","");


        boolean exito = intervencionDao.insertar(receta);
        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Se registró la intervención correctamente.\n\nPuede revisar los medicamentos en el sistema");
            horaSplit.setText("Seleccione un medicamento");
            intervencionSeleccionada = null;
            idCita1.setText("");
            idCita.setDisable(true);
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
        ObservableList<Medicamento> intervenciones = seIntervieneDao.listar();

        // Limpiar opciones actuales
        horaSplit.getItems().clear();

        for (Medicamento medicamento : intervenciones) {
            String texto = medicamento.getNombre() + " - " + medicamento.getIdMedicamento();
            MenuItem item = new MenuItem(texto);

            // Acción para cuando se selecciona
            item.setOnAction(e -> {
                horaSplit.setText(texto);
                intervencionSeleccionada = medicamento;
                idCita1.setDisable(false);
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
