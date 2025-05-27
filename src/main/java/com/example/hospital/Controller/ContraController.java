package com.example.hospital.Controller;

import com.example.hospital.Model.Tablas.Doctor;
import com.example.hospital.Model.Tablas.Paciente;
import com.example.hospital.Model.Tablas.Usuario;
import com.example.hospital.Model.UsuarioBd;
import com.example.hospital.Vista.Doctor.DoctorView;
import com.example.hospital.Vista.Paciente.PacienteView;
import com.example.hospital.Vista.login;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.print.Doc;

public class ContraController {
    private String nombredoc;
    private String iddoc;
    private Object idPaciente;

    @FXML
    private Button reg;
    @FXML
    private TextField nombre;
    @FXML
    private TextField curp;
    @FXML
    private TextField confi;

    UsuarioBd usuarioBd= new UsuarioBd();

    public ContraController() throws Exception {
    }

    @FXML
    private void agregar(){
        if(nombre.getText().isEmpty()||curp.getText().isEmpty()||confi.getText().isEmpty()){
            mostrarAlerta(Alert.AlertType.ERROR,"Error","Debe de llenar todos los campos");
            return;
        }
        if(!curp.getText().equals(confi.getText())){
            mostrarAlerta(Alert.AlertType.ERROR,"Error","Verifique que la contraseña nueva y su verificación sean iguales");
            return;
        }

        Usuario u= usuarioBd.buscarId(iddoc,nombre.getText());
        if (u==null){
            mostrarAlerta(Alert.AlertType.ERROR,"Error","Contraseña incorrecta");

        } else{
            boolean ex= usuarioBd.cambiarContraseña(iddoc,curp.getText());
            if(ex){
                mostrarAlerta(Alert.AlertType.INFORMATION,"Exito","Se cambio la contraseña exitosamente");
                try{
                    login log= new login();
                    log.start(new javafx.stage.Stage());
                    reg.getScene().getWindow().hide();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                mostrarAlerta(Alert.AlertType.ERROR,"Error","Hubo un error al cambiar la contraseña");
                return;
            }
        }



    }

    public void setText(String nombre, String id, Object idpaciente) {
        this.nombredoc = nombre;
        this.iddoc = id;
        this.idPaciente = idpaciente;
    }

    @FXML
    private void regresar() throws Exception {
        if (idPaciente instanceof Paciente){
            PacienteView v = new PacienteView();
            v.mostrar(nombredoc,iddoc);
            reg.getScene().getWindow().hide();
        } else if (idPaciente instanceof Doctor){
            DoctorView view = new DoctorView();
            view.mostrar(nombredoc, iddoc);
            reg.getScene().getWindow().hide();
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
