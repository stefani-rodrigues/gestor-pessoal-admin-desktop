package com.senac.gestorpessoaladmimconfig.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    public void sair (){

        System.exit(0);
    }

    public void abrirMenuApiBanco (ActionEvent event ) throws Exception{

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/com/senac/gestorpessoaladmimconfig/teste-api-banco.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    public void abrirConfiguracaoAdim(ActionEvent event)throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senac/gestorpessoaladmimconfig/admin-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
