package com.example.hospital.Controller.Admin;

import com.example.hospital.Model.ConexioBD;
import com.example.hospital.Vista.Admin.GenericView;
import com.example.hospital.Vista.Admin.GenericView;
import com.example.hospital.Vista.login;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class AdminController {

    @FXML
    private Button pac;

    @FXML
    private Label usua;

    public AdminController() throws Exception {
    }

    public void setText(String text){
        usua.setText(text);
    }

    @FXML
    protected void irAdmin() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("admin-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected  void irPaciente() {
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("paciente-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void irDoctor(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("doctor-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irEnfermero(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("enfermero-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irCompania(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("compania-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irLabo(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("laboratorio-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irEst(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("estudio-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irDepa(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("departamento-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irSala(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("sala-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irInter(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("intervenciones-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irMedi(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("medicamentos-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irPrese(){
        try{
            GenericView paciente= new GenericView();
            paciente.mostrar("presentacion-view.fxml",usua.getText());

            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void cerrar(){
        try{
            login log= new login();
            log.start(new javafx.stage.Stage());
            pac.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
