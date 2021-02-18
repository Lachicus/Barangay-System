package brgySys;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ResidentProfileFunctions implements Initializable {

    //label
    @FXML
    private Label lblstatus;
    //Buttons
    @FXML
    private Button btnCancelResident;
    @FXML
    private Button btnAdd;

    //TextFields
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField areaofBirth;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private TextField residencyYrs;

    //BirthDay
    @FXML
    private TextField txt_month;
    @FXML
    private TextField txt_day;
    @FXML
    private TextField txt_year;


    // Choice Box

    @FXML
    private ChoiceBox box_gender;
    @FXML
    private ChoiceBox box_voter;
    @FXML
    private ChoiceBox box_civil;

    ObservableList<String> gender = FXCollections.observableArrayList("Male","Female");
    ObservableList<String> voter  = FXCollections.observableArrayList("Yes","No");
    ObservableList<String> civil  = FXCollections.observableArrayList("Single","Married","Divorced","Widowed","Separated");

    // Database

    Connection conn = null;
    PreparedStatement statement = null;
    DatabaseConnection Dbs = new DatabaseConnection();



    public void addResident(javafx.event.ActionEvent actionEvent) throws IOException {
        if (firstName.getText().isBlank() == false && lastName.getText().isBlank() == false && middleName.getText().isBlank() == false && middleName.getText().isBlank() == false && txt_month.getText().isBlank() ==false && txt_day.getText().isBlank() == false
        && txt_year.getText().isBlank() == false && areaofBirth.getText().isBlank() == false && phoneNumber.getText().isBlank() == false && box_gender.getValue().equals(null) == false && box_civil.getValue().equals(null) == false && box_voter.getValue().equals(null) == false &&
        address.getText().isBlank() == false && residencyYrs.getText().isBlank() == false){
            proceedResident();
            Stage stage = (Stage) btnCancelResident.getScene().getWindow();
            stage.close();

        }else {
            lblstatus.setText("Input is Empty");
        }
    }

    public void proceedResident(){
        conn = Dbs.getConnection();
        String query = "INSERT INTO barangaysys.brgy_residents (firstName, lastName, middleName, birthDate, placeofBirth, phoneNumber, gender, address, voterStatus, civilStatus, residency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String birthday = txt_month.getText() + "/" + txt_day.getText() + "/" + txt_month.getText();

        try {
            statement = conn.prepareStatement(query);
            statement.setString(1,firstName.getText());
            statement.setString(2,lastName.getText());
            statement.setString(3,middleName.getText());
            statement.setString(4,birthday);
            statement.setString(5,areaofBirth.getText());
            statement.setString(6,phoneNumber.getText());
            statement.setString(7,box_gender.getValue().toString());
            statement.setString(8,address.getText());
            statement.setString(9,box_voter.getValue().toString());
            statement.setString(10,box_civil.getValue().toString());
            statement.setString(11,residencyYrs.getText());
            statement.execute();
        }catch (Exception e){

            e.printStackTrace();
            e.getCause();
        }
    }



    public void cancelEditEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCancelResident.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        box_gender.setItems(gender);
        box_civil.setItems(civil);
        box_voter.setItems(voter);
    }
}
