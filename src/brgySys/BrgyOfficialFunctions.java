package brgySys;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BrgyOfficialFunctions {




    // Buttons
    @FXML
    private Button btnCancelEmployee;
    @FXML
    private Button btnAdd;

    //TextFields
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField userposition;



    //Labels
    @FXML
    private Label lblstatus;

    //Database and Update Management
    Connection conn = null;
    PreparedStatement statement = null;
    DatabaseConnection Dbs = new DatabaseConnection();



    public void validateAddEmployee(javafx.event.ActionEvent actionEvent)throws IOException {
        if (firstname.getText().isBlank() == false && lastname.getText().isBlank() == false && userposition.getText().isBlank() == false){
            AddEmployee();
            Stage stage = (Stage) btnCancelEmployee.getScene().getWindow();
            stage.close();
        }else {
            lblstatus.setText("Input is Empty");
        }

    }

    public void AddEmployee(){
        conn = Dbs.getConnection();
        String query = "INSERT INTO barangaysys.brgy_officials (firstName, lastName, position) VALUES (?,?,?);";

        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, firstname.getText());
            statement.setString(2, lastname.getText());
            statement.setString(3, userposition.getText());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    public void cancelAddEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCancelEmployee.getScene().getWindow();
        stage.close();
    }




}
