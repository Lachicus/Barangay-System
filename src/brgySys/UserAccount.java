package brgySys;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;

public class UserAccount {
    @FXML
    private Button btnExitMenu;


    public void viewDefaultAccount(javafx.event.ActionEvent actionEvent)throws IOException {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM barangaysys.user_account;");
            if (result.next()) {
                String username = result.getString("username").toString();
                String password = result.getString("password").toString();
                JOptionPane.showMessageDialog(null, "<Username: " + username + "> " +"<password: " + password + ">");

            }

        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }


    }

    public void changeAccount(javafx.event.ActionEvent actionEvent)throws IOException {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();

        try {

            Statement statement = conn.createStatement();
            String useraccount = JOptionPane.showInputDialog(null, "Enter the new username");
            String userpassword = JOptionPane.showInputDialog(null, "Enter the new password");

            if(useraccount.isBlank() == false && userpassword.isBlank() == false) {
                String query = "UPDATE barangaysys.user_account SET username ='" + useraccount + "', password = '" + userpassword + "' WHERE(account_id = '1');";

                int resultP = JOptionPane.showConfirmDialog(null, "Are you sure you want to change the default username and password into <UserName: " + useraccount + "> " +
                        "<Password: " + userpassword + ">", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                if (resultP == JOptionPane.YES_OPTION) {
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Success");
                } else if (resultP == JOptionPane.NO_OPTION) {

                }
            } else {
                JOptionPane.showMessageDialog(null," Username or Password is cannot be blank");
            }

        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }
    // Exit window and return to menu

    public void exit(javafx.event.ActionEvent actionEvent)throws IOException {
        returntoMenu();
        Stage stage = (Stage) btnExitMenu.getScene().getWindow();
        stage.close();
    }

    private void returntoMenu()throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/mainapp/MainMenu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 506, 657));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
