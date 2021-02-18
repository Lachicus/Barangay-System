package brgySys;

import brgySys.obj.BrgyResident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ResidentProfile implements Initializable {

    //TextFields FX

    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private TextField txt_middleName;
    @FXML
    private TextField txt_birthArea;
    @FXML
    private TextField txt_phoneNum;
    @FXML
    private TextField txt_address;
    @FXML
    private TextField txt_residency;
    @FXML
    private TextField txt_search;

    // Date TextField
    @FXML
    private TextField txt_month;
    @FXML
    private TextField txt_day;
    @FXML
    private TextField txt_year;

  // C:\Program Files\Java\jdk-11.0.5

    // Choice Box FX
    @FXML
    private ChoiceBox box_gender;
    @FXML
    private ChoiceBox box_voter;
    @FXML
    private ChoiceBox box_civil;

    ObservableList<String> gender = FXCollections.observableArrayList("Male","Female");
    ObservableList<String> voter  = FXCollections.observableArrayList("Yes","No");
    ObservableList<String> civil  = FXCollections.observableArrayList("Single","Married","Divorced","Widowed","Separated");
    // Buttons FX
    @FXML
    private Button btn_exit;
    @FXML
    private Button btnEdit;

    // Columns

    @FXML
    private TableColumn<BrgyResident,Integer> col_resId;
    @FXML
    private TableColumn<BrgyResident,String> col_firstName;
    @FXML
    private TableColumn<BrgyResident,String> col_lastName;
    @FXML
    private TableColumn<BrgyResident,String> col_middleName;
    @FXML
    private TableColumn<BrgyResident,String> col_gender;
    @FXML
    private TableColumn<BrgyResident, String> col_birthday;
    @FXML
    private TableColumn<BrgyResident,String> col_contact;
    @FXML
    private TableColumn<BrgyResident,String> col_address;
    @FXML
    private TableColumn<BrgyResident,String> col_civil;
    @FXML
    private TableColumn<BrgyResident,String> col_voter;
    @FXML
    private TableColumn<BrgyResident,String> col_birthplace;
    @FXML
    private TableColumn<BrgyResident,Integer> col_residency;
    @FXML
    private TableView<BrgyResident> table_users;

    // Database
    Connection conn = null;
    PreparedStatement statement = null;
    DatabaseConnection Dbs = new DatabaseConnection();
    ObservableList<BrgyResident> listM;
    Integer index = -1;
    String Id = null;


    // Button Functions
    public void addResident(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/resProf/residentAddform.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 521, 346));
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }

    public void search(javafx.event.ActionEvent actionEvent) throws IOException {
        col_resId.setCellValueFactory(new PropertyValueFactory<BrgyResident,Integer>("resId"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("lastName"));
        col_middleName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String >("middleName"));
        col_birthday.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("birthDate"));
        col_birthplace.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("placeofBirth"));
        col_contact.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("phoneNumber"));
        col_gender.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("gender"));
        col_address.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("address"));
        col_voter.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("voterStatus"));
        col_civil.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("civilStatus"));
        col_residency.setCellValueFactory(new PropertyValueFactory<BrgyResident,Integer>("residency"));

        listM = getSearchData();
        table_users.setItems(listM);

    }

    public void editResident(javafx.event.ActionEvent actionEvent) throws IOException{
        String value1 = txt_firstName.getText();
        String value2 = txt_lastName.getText();
        String value3 = txt_middleName.getText();
        String value4 = txt_month.getText() + "/" + txt_day.getText() + "/" + txt_month.getText();
        String value5 = txt_birthArea.getText();
        String value6 = txt_phoneNum.getText();
        String value7 = box_gender.getValue().toString();
        String value8 = txt_address.getText();
        String value9 = box_voter.getValue().toString();
        String value10 = box_civil.getValue().toString();
        String value11 = txt_residency.getText();
        try {
            conn = Dbs.getConnection();
            String query = "UPDATE barangaysys.brgy_residents SET resId= '" + Id + "',firstName='" + value1 + "',lastName='" + value2 + "',middleName='" + value3 +
                    "',birthDate='" + value4 + "',placeofBirth='" + value5 + "',phoneNumber='" + value6 + "',gender='" + value7 + "',address='" + value8 + "',voterStatus='" +
                    value9 + "',civilStatus='" + value10 + "',residency='" + value11 + "' WHERE resId='" + Id + "';";

                statement = conn.prepareStatement(query);
                statement.execute();
                displayData();

        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void deleteResident(javafx.event.ActionEvent actionEvent) throws IOException{
        conn = Dbs.getConnection();
        String query = "DELETE from barangaysys.brgy_residents WHERE resId = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1,Id);
            statement.execute();
            displayData();

            // reset
            txt_firstName.setText("");
            txt_lastName.setText("");
            txt_middleName.setText("");
            txt_month.setText("");
            txt_day.setText("");
            txt_year.setText("");
            txt_phoneNum.setText("");
            txt_address.setText("");
            txt_birthArea.setText("");
            txt_residency.setText("");
            box_gender.setValue(null);
            box_civil.setValue(null);
            box_voter.setValue(null);


        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }


    // Utilities

    public ObservableList<BrgyResident> getSearchData(){
        ObservableList<BrgyResident> residentList = FXCollections.observableArrayList();
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDb = conn.getConnection();

        try {
            Statement statement = connectDb.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM barangaysys.brgy_residents WHERE firstName = '" + txt_search.getText() +  "' OR lastName ='" + txt_search.getText() + "' OR middleName = '" + txt_search.getText() + "';");

            while (rs.next()){
                residentList.add(new BrgyResident(Integer.parseInt(rs.getString("resId")),rs.getString("firstName"),rs.getString("lastName"),
                        rs.getString("middleName"),rs.getString("birthDate"),rs.getString("placeofBirth"),rs.getString("phoneNumber"),
                        rs.getString("gender"),rs.getString("address"),rs.getString("voterStatus"),rs.getString("civilStatus"),Integer.parseInt(rs.getString("residency"))));

            }
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        return residentList;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayData();
        box_gender.setItems(gender);
        box_civil.setItems(civil);
        box_voter.setItems(voter);
        btnEdit.setVisible(false);

    }



    public void displayData(){
        col_resId.setCellValueFactory(new PropertyValueFactory<BrgyResident,Integer>("resId"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("lastName"));
        col_middleName.setCellValueFactory(new PropertyValueFactory<BrgyResident,String >("middleName"));
        col_birthday.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("birthDate"));
        col_birthplace.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("placeofBirth"));
        col_contact.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("phoneNumber"));
        col_gender.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("gender"));
        col_address.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("address"));
        col_voter.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("voterStatus"));
        col_civil.setCellValueFactory(new PropertyValueFactory<BrgyResident,String>("civilStatus"));
        col_residency.setCellValueFactory(new PropertyValueFactory<BrgyResident,Integer>("residency"));
        DatabaseConnection data = new DatabaseConnection();
        listM = data.getResidentList();
        table_users.setItems(listM);

    }





    // Return to Main menu process

    public void close(javafx.event.ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
        returnToMenu();
    }

    private void returnToMenu() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/mainapp/MainMenu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 506, 657));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // UI Update
    public void refresh(ActionEvent actionEvent) {
        displayData();
    }

    @FXML
    void getSelected(MouseEvent event) throws IOException {
        index = table_users.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }

        txt_firstName.setText(col_firstName.getCellData(index).toString());
        txt_lastName.setText(col_lastName.getCellData(index).toString());
        txt_middleName.setText(col_middleName.getCellData(index).toString());
        String birthday = col_birthday.getCellData(index);
        String[] split = birthday.split("/",3); // index 0 = month, index 1 = day, index 3 = year
        txt_month.setText(split[0]);
        txt_day.setText(split[1]);
        txt_year.setText(split[2]);
        txt_birthArea.setText(col_birthplace.getCellData(index).toString());
        txt_phoneNum.setText(col_contact.getCellData(index).toString());
        txt_address.setText(col_address.getCellData(index).toString());
        txt_residency.setText(col_residency.getCellData(index).toString());
        box_gender.setValue(col_gender.getCellData(index));
        box_voter.setValue(col_voter.getCellData(index));
        box_civil.setValue(col_civil.getCellData(index));
        btnEdit.setVisible(true);
        Id = col_resId.getCellData(index).toString();



    }
}
