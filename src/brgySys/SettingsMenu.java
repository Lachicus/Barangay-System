package brgySys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsMenu {

    @FXML
    private Button btnReturnMenu;





    // EXIT & RETURN TO MAIN MENU FUNCTIONS

    public void exportResident(javafx.event.ActionEvent actionEvent)throws IOException{
        try {
            PrintWriter pw = new PrintWriter(new File("C:\\ExportedData\\ResidentData.csv"));
            StringBuilder sb = new StringBuilder();

            DatabaseConnection conn = new DatabaseConnection();
            Connection connect = conn.getConnection();

            String query = "SELECT * FROM barangaysys.brgy_residents;";
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet result = ps.executeQuery();

            sb.append("First Name");
            sb.append(",");
            sb.append("Last Name");
            sb.append(",");
            sb.append("Middle Name");
            sb.append(",");
            sb.append("BirthDay");
            sb.append(",");
            sb.append("BirthPlace");
            sb.append(",");
            sb.append("Contact Number");
            sb.append(",");
            sb.append("Gender");
            sb.append(",");
            sb.append("Address");
            sb.append(",");
            sb.append("Voter Status");
            sb.append(",");
            sb.append("Civil Status");
            sb.append(",");
            sb.append("Years of Residency");
            sb.append("\r\n");

            while(result.next()){
                sb.append(result.getString("firstName"));
                sb.append(",");
                sb.append(result.getString("lastName"));
                sb.append(",");
                sb.append(result.getString("middleName"));
                sb.append(",");
                sb.append(result.getString("birthDate"));
                sb.append(",");
                sb.append(result.getString("placeofBirth"));
                sb.append(",");
                sb.append(result.getString("phoneNumber"));
                sb.append(",");
                sb.append(result.getString("gender"));
                sb.append(",");
                sb.append(result.getString("address"));
                sb.append(",");
                sb.append(result.getString("voterStatus"));
                sb.append(",");
                sb.append(result.getString("civilStatus"));
                sb.append(",");
                sb.append(result.getString("residency"));
                sb.append("\r\n");
            }

            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"The File has been Successfully Exported in C:/ExportedData/ResidentData");

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void exportBrgyOfficials(javafx.event.ActionEvent actionEvent)throws IOException{
        try {
            PrintWriter pw = new PrintWriter(new File("C:\\ExportedData\\BrgyOfficialsData.csv"));
            StringBuilder sb = new StringBuilder();

            DatabaseConnection conn = new DatabaseConnection();
            Connection connect = conn.getConnection();

            String query = "SELECT * FROM barangaysys.brgy_officials;";
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet result = ps.executeQuery();

            sb.append("First Name");
            sb.append(",");
            sb.append("Last Name");
            sb.append(",");
            sb.append("Position");
            sb.append("\r\n");

            while(result.next()){
                sb.append(result.getString("firstName"));
                sb.append(",");
                sb.append(result.getString("lastName"));
                sb.append(",");
                sb.append(result.getString("Position"));
                sb.append("\r\n");
            }

            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"The File has been Successfully Exported in C:/ExportedData/BrgyOfficialsData");

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void help(javafx.event.ActionEvent actionEvent)throws IOException{
        JOptionPane.showMessageDialog(null,"For Questions, Inquiry or Problem Occurs Please Contact" + "\n <EMAIL: Sebastianlachica05@Gmail.com>" + "\n <CONTACT#: 09274865304>");
    }

    public void exitSettings(javafx.event.ActionEvent actionEvent)throws IOException{
        Stage stage = (Stage) btnReturnMenu.getScene().getWindow();
        stage.close();
        returnToMenu();
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
