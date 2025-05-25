package com.example.hospital.Controller;

import com.example.hospital.Model.Daos.Dao;
import com.example.hospital.Model.DaoFactory;
import com.example.hospital.Model.Tablas.Paciente;
import com.example.hospital.Model.Tablas.Usuario;
import com.example.hospital.Model.UsuarioBd;
import com.example.hospital.Vista.Admin.AdminView;
import com.example.hospital.Vista.Paciente.PacienteView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.hospital.Model.ConexioBD.connection;

public class HelloController {

    @FXML
    private PasswordField pass;
    @FXML
    private TextField usuario;
    @FXML
    private Button logbo;

    @FXML
    protected void onHelloButtonClick() {
        try {
            UsuarioBd usuarioBd = new UsuarioBd();
            Usuario usuario1= usuarioBd.buscarUsuario(usuario.getText(),pass.getText());
            if(usuario1==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error");
                alert.setContentText("Usuario y/o contraseña incorrectos");
                alert.showAndWait();
            } else{
                if(usuario1.getTipo()==1){
                    AdminView adminView = new AdminView();
                    adminView.mostrar(usuario1.getUsuario());
                } else if(usuario1.getTipo()==2){
                    Dao<Paciente> pacienteDaoDao = (Dao<Paciente>) DaoFactory.PACIENTE.crear(connection);
                    Paciente paciente = pacienteDaoDao.consultar(usuario1.getIdEx());
                    PacienteView pacienteView = new PacienteView();
                    pacienteView.mostrar(paciente.getNombre(),paciente.getIdPaciente());
                }


                logbo.getScene().getWindow().hide();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}