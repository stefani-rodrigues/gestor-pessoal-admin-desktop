package com.senac.gestorpessoaladmimconfig.controller;

import com.senac.gestorpessoaladmimconfig.model.DAO.EnderecoDAO;
import com.senac.gestorpessoaladmimconfig.model.Endereco;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TesteApiBancoController {

    @FXML
    private TextField textEndereco;

    @FXML
    private TextField textCep;

    public void consultarCep (ActionEvent event){

        try {
            var urlEndereco = "http://viacep.com.br/ws/" +textCep.getText()+"/json";
            URL url = new URL(urlEndereco);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("Content-Type", "application/json");


//           String json = String.format("{\"email\" : \"%s\"\"senha\" : \"%s\" } ",txtCep.getText(),txtEndereco.getText());
//
//           try (OutputStream os = conn.getOutputStream()){
//               os.write(json.getBytes());
//
//           }


            int status = httpURLConnection.getResponseCode();
            if (status==200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null){
                    response.append(inputLine);
                }
                reader.close();

                textEndereco.setText(response.toString());
                salvarEndereco(response.toString(),textCep.getText());
            }
            httpURLConnection.disconnect();

        } catch (Exception e) {

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dados digitados");
        alert.setHeaderText(null);
        alert.setContentText("Cep"+textCep.getText());

        alert.showAndWait();

    }

    public void voltar(ActionEvent event) throws Exception{
        System.out.println(" Abrindo tela do professor");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senac/gestorpessoaladmimconfig/menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    private boolean salvarEndereco(String endereco, String cep) {
        try {
            EntityManager entityManager = JpaUtils.getEntityManager();

            EnderecoDAO enderecoDAO = new EnderecoDAO(entityManager);

            Endereco enderecoBanco = new Endereco();
            enderecoBanco.setEndereco(endereco);
            enderecoBanco.setCep(cep);
            enderecoDAO.salvar(enderecoBanco);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
