package brgySys;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LogIn {
    @FXML
    private TextField username;
    @FXML
    private PasswordField passw;
    @FXML
    private Button btnLog;
    @FXML
    private Button btnCancel;
    @FXML
    private Label logStatus;



    public void Login(javafx.event.ActionEvent actionEvent) throws IOException {
        if (username.getText().isBlank() == false && passw.getText().isBlank() == false){
            logStatus.setText("Connecting to Database Server");
            validateLogin();
            } else {
                logStatus.setText("Enter username and password");
        }

    }

    public void Cancel(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    private void validateLogin() throws IOException {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connecDB = connectNow.getConnection();
            String userName = username.getText();
            String passWord = passw.getText();

        String query = "SELECT count(1) FROM user_account WHERE username = '" + userName + "' AND  password ='" + passWord +  "'";

        try{
            logStatus.setText("Connecting to Database Server");
            Statement statement = connecDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    logStatus.setText("Success");
                    Stage stage = (Stage) btnCancel.getScene().getWindow();
                    stage.close();
                    mainApp();
                }else {
                    logStatus.setText("Invalid Login. Please Try Again");
                }
            }
        }catch (Exception e){
            logStatus.setText("No Server Connection");
            e.printStackTrace();
            e.getCause();
        }

    }


    private void mainApp() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/mainapp/MainMenu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 506, 657));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



}

