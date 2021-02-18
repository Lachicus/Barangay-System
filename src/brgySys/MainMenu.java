package brgySys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainMenu {

    // MAIN MENU WINDOW
    @FXML
    private Button btnExitMenu;
    @FXML
    private Button btnSettings;
    @FXML
    private Button logout;
    @FXML
    private Button logout2;



    // SETTINGS WINDOW
    @FXML
    private Button btnReturnMenu;



    // SETTINGS WINDOW <FXML>

    public void appSettings(javafx.event.ActionEvent actionEvent)throws IOException{
        Return();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/settings/settings.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(root, 506, 605));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Barangay Profile
    public void appBrgyProfile(javafx.event.ActionEvent actionEvent)throws IOException{
        Return();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/BrgyProf/BrgyProfile.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(root, 800, 711));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void appResidentProfile(javafx.event.ActionEvent actionEvent)throws IOException{
        Return();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/resProf/residentProfile.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(root, 1100, 711));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void appUserAccount(javafx.event.ActionEvent actionEvent)throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connecDB = connectNow.getConnection();
        String userName = JOptionPane.showInputDialog(null,"Enter Username");
        String passWord = JOptionPane.showInputDialog(null,"Enter Password");

        String query = "SELECT count(1) FROM user_account WHERE username = '" + userName + "' AND  password ='" + passWord + "'";
        try {

            Statement statement = connecDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    ProceedUserAcc();
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect Email or Password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    private void ProceedUserAcc() throws IOException {
        Return();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/userAcc/userAccount.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(root, 387, 334));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    // LOGOUT FUNCTIONS FOR MAIN MENU
    // Confirmation as well

    public void LogOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Return();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/warnings/Logout.fxml"));
        primaryStage.setScene(new Scene(root, 346, 141));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void logoutOption1(javafx.event.ActionEvent actionEvent) throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/login/LoginWindow.fxml"));
        primaryStage.setTitle("Barangay System - Admin Log In");
        primaryStage.setScene(new Scene(root, 513, 501));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        quitWarning();

    }

    public void logoutOption2() throws IOException{
        quitWarning();
        returnToMenu();
    }



    // UTILITIES
    public void quitWarning(){
        Stage stage = (Stage) logout2.getScene().getWindow();
        stage.close();
    }

    public void Return(){
        Stage stage = (Stage) btnExitMenu.getScene().getWindow();
        stage.close();
    }

    public void returnToMenu() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/mainapp/MainMenu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 506, 657));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
