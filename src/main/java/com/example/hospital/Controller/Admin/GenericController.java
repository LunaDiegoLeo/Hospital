package com.example.hospital.Controller.Admin;

import com.example.hospital.Vista.Admin.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GenericController {
    @FXML
    private Button regresar;
    @FXML
    private Label usua;

    public String nombre;
    @FXML
    public void Agregar() throws Exception {
        GenericInsert genericInsert= new GenericInsert();
        genericInsert.mostrar(nombre,usua.getText());
        regresar.getScene().getWindow().hide();
    }

    @FXML
    public void Buscar() throws Exception {
        GenericBuscar genericLi= new GenericBuscar();
        genericLi.mostrar(nombre,usua.getText());
        regresar.getScene().getWindow().hide();
    }

    @FXML
    public void Listar() throws Exception {
        GenericListar genericLi= new GenericListar();
        genericLi.mostrar(nombre,usua.getText());
        regresar.getScene().getWindow().hide();
    }

    @FXML
    public void Act() throws Exception {
        GenericAct genericLi= new GenericAct();
        genericLi.mostrar(nombre,usua.getText());
        regresar.getScene().getWindow().hide();
    }

    @FXML
    public void Eli() throws Exception {
        GenericEliminar genericLi= new GenericEliminar();
        genericLi.mostrar(nombre,usua.getText());
        regresar.getScene().getWindow().hide();
    }

    @FXML
    public void regresar() {
        try{
            AdminView adminView = new AdminView();
            adminView.mostrar(usua.getText());

            regresar.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String text,String nombre){
        usua.setText(text);
        this.nombre=nombre;
    }
}
