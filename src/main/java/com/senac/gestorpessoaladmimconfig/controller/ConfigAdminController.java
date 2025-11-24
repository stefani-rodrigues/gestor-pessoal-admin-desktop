package com.senac.gestorpessoaladmimconfig.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.gestorpessoaladmimconfig.model.DAO.UsuarioDAO;
import com.senac.gestorpessoaladmimconfig.model.Usuario;
import com.senac.gestorpessoaladmimconfig.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConfigAdminController {
    @FXML
    private TextField textNome;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textSenha;

    @FXML
    private TextField textCpf;


    public void cadastroAdim (ActionEvent event){

        try{
            var urlEndereco = "http://localhost:8080/usuarios";
            URL url = new URL(urlEndereco);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            Usuario usuario = new Usuario();
            usuario.setNome(textNome.getText());
            usuario.setEmail(textEmail.getText());
            usuario.setSenha(textSenha.getText());
            usuario.setCpf(textCpf.getText());
            usuario.setRole("ADMIN");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(usuario);
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = httpURLConnection.getResponseCode();
            if (status == 200 || status == 201) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
                reader.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Administrador cadastrado com sucesso!");
                alert.showAndWait();

            } else {
                System.out.println("Erro: " + status);
            }

            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao cadastrar o administrador");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void voltar(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senac/gestorpessoaladmimconfig/menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }
}
