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
        if(nombre.equals("admin")){

            GenericInsert genericInsert= new GenericInsert();
            genericInsert.mostrar(nombre,usua.getText());
            regresar.getScene().getWindow().hide();
        }
        else if(nombre.equals("paciente")){

         } else if(nombre.equals("doctor")){

         } else if(nombre.equals("enfermero")){

         } else if(nombre.equals("compania")){

         } else if(nombre.equals("laboratorio")){

        } else if(nombre.equals("estudioslab")){

        } else if(nombre.equals("departamento")){

        } else if(nombre.equals("sala")){

        } else if(nombre.equals("intervenciones")){

        } else if(nombre.equals("medicamentos")){}
    }

    @FXML
    public void Buscar() throws Exception {
        if(nombre.equals("admin")){
            GenericBuscar genericLi= new GenericBuscar();
            genericLi.mostrar(nombre,usua.getText());
            regresar.getScene().getWindow().hide();
        }
        else if(nombre.equals("paciente")){

        } else if(nombre.equals("doctor")){

        } else if(nombre.equals("enfermero")){

        } else if(nombre.equals("compania")){

        } else if(nombre.equals("laboratorio")){

        } else if(nombre.equals("estudioslab")){

        } else if(nombre.equals("departamento")){

        } else if(nombre.equals("sala")){

        } else if(nombre.equals("intervenciones")){

        } else if(nombre.equals("medicamentos")){}
    }

    @FXML
    public void Listar() throws Exception {
        if(nombre.equals("admin")){
            GenericListar genericLi= new GenericListar();
            genericLi.mostrar(nombre,usua.getText());
            regresar.getScene().getWindow().hide();
        }
        else if(nombre.equals("paciente")){

        } else if(nombre.equals("doctor")){

        } else if(nombre.equals("enfermero")){

        } else if(nombre.equals("compania")){

        } else if(nombre.equals("laboratorio")){

        } else if(nombre.equals("estudioslab")){

        } else if(nombre.equals("departamento")){

        } else if(nombre.equals("sala")){

        } else if(nombre.equals("intervenciones")){

        } else if(nombre.equals("medicamentos")){}
    }

    @FXML
    public void Act() throws Exception {
        if(nombre.equals("admin")){
            GenericAct genericLi= new GenericAct();
            genericLi.mostrar(nombre,usua.getText());
            regresar.getScene().getWindow().hide();
        }
        else if(nombre.equals("paciente")){

        } else if(nombre.equals("doctor")){

        } else if(nombre.equals("enfermero")){

        } else if(nombre.equals("compania")){

        } else if(nombre.equals("laboratorio")){

        } else if(nombre.equals("estudioslab")){

        } else if(nombre.equals("departamento")){

        } else if(nombre.equals("sala")){

        } else if(nombre.equals("intervenciones")){

        } else if(nombre.equals("medicamentos")){}
    }

    @FXML
    public void Eli() throws Exception {
        if(nombre.equals("admin")){
            GenericEliminar genericLi= new GenericEliminar();
            genericLi.mostrar(nombre,usua.getText());
            regresar.getScene().getWindow().hide();
        }
        else if(nombre.equals("paciente")){

        } else if(nombre.equals("doctor")){

        } else if(nombre.equals("enfermero")){

        } else if(nombre.equals("compania")){

        } else if(nombre.equals("laboratorio")){

        } else if(nombre.equals("estudioslab")){

        } else if(nombre.equals("departamento")){

        } else if(nombre.equals("sala")){

        } else if(nombre.equals("intervenciones")){

        } else if(nombre.equals("medicamentos")){}
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
