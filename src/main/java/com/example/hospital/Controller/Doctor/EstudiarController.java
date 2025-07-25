package com.example.hospital.Controller.Doctor;

import com.example.hospital.Model.CalculadorCosto;
import com.example.hospital.Model.CostoStrategy2;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Daos.EstudiaDao;
import com.example.hospital.Model.Daos.EstudioDao;
import com.example.hospital.Model.Daos.IntervencionDao;
import com.example.hospital.Model.Daos.SeIntervieneDao;
import com.example.hospital.Model.Tablas.*;
import com.example.hospital.Vista.Doctor.DoctorView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

import static com.example.hospital.Model.ConexioBD.connection;

public class EstudiarController {

    private String nombredoc;
    private String iddoc;
    private String idPaciente;

    @FXML
    private TextField idCita;
    @FXML
    private SplitMenuButton horaSplit;
    @FXML
    private Button boton;

    private EstudiaDao estudiaDao = (EstudiaDao) DaoFactory.ESTUDIA.crear(connection);
    private EstudioDao estudioDao = (EstudioDao) DaoFactory.ESTUDIO.crear(connection);

    private Estudio intervencionSeleccionada;

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
            mostrarAlerta(Alert.AlertType.WARNING, "Estudio no seleccionado", "Por favor, selecciona un estudio antes de continuar.");
            return;
        }

        Estudia newEstudia = new Estudia(iddoc,idPaciente,intervencionSeleccionada.getIdEstudio()+"");

        boolean exito = estudiaDao.insertar(newEstudia);
        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Se registró la intervención correctamente.\n\nEl costó sera de: "+intervencionSeleccionada.getCosto()+"\n\nPuede pagar en el laboratorio: "+intervencionSeleccionada.getIdLaboratorio()+"\n\n| | | | || || ||");
            horaSplit.setText("Seleccione un estudio");
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
        ObservableList<Estudio> intervenciones = estudioDao.listar();

        // Limpiar opciones actuales
        horaSplit.getItems().clear();

        for (Estudio estudio: intervenciones) {
            String texto = estudio.getDescripcion() + " - " + estudio.getIdEstudio();
            MenuItem item = new MenuItem(texto);

            // Acción para cuando se selecciona
            item.setOnAction(e -> {
                horaSplit.setText(texto);
                intervencionSeleccionada = estudio;
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
