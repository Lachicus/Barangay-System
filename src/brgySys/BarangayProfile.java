package brgySys;

import brgySys.obj.BrgyResident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.util.ResourceBundle;

// Local Imports
import brgySys.obj.BrgyOfficials;

public class BarangayProfile implements Initializable{

    @FXML
    private Button btnExitMenu;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;

    // Columns
    @FXML
    private TableColumn<BrgyOfficials, Integer> col_Id;
    @FXML
    private TableColumn<BrgyOfficials,String> col_FirstName;
    @FXML
    private TableColumn<BrgyOfficials,String> col_LastName;
    @FXML
    private TableColumn<BrgyOfficials, String> col_Position;
    @FXML
    private TableView<BrgyOfficials> table_users;

    // TextFields

    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private TextField txt_Position;
    @FXML
    private TextField txt_brgyID;

    // Labels
    @FXML
    private Label EmployeesCount;
    @FXML
    private Label PopulationCount;
    @FXML
    private Label VoteesCount;

    //Observable List and database
    Connection conn = null;
    PreparedStatement statement = null;
    DatabaseConnection Dbs = new DatabaseConnection();
    ObservableList<BrgyOfficials> listM;
    Integer index = -1;

    //Methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOfficialList();
        countEmployees();
        countResidents();
        countVotees();
    }

    public void showOfficialList(){
        col_Id.setCellValueFactory(new PropertyValueFactory<BrgyOfficials,Integer>("brgy_id"));
        col_FirstName.setCellValueFactory(new PropertyValueFactory<BrgyOfficials,String>("firstName"));
        col_LastName.setCellValueFactory(new PropertyValueFactory<BrgyOfficials,String>("lastName"));
        col_Position.setCellValueFactory(new PropertyValueFactory<BrgyOfficials,String>("position"));
        DatabaseConnection data = new DatabaseConnection();
        listM = data.getOfficialList();
        table_users.setItems(listM);
    }

    public void countEmployees(){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM barangaysys.brgy_officials");
            int count = 0;
            while (result.next()){
                count++;
            }
            String rows = Integer.toString(count);
            EmployeesCount.setText(rows);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void countResidents(){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDB = conn.getConnection();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM barangaysys.brgy_residents");
            int count = 0;
            while (result.next()){
                count++;
            }
            String rows = Integer.toString(count);
            PopulationCount.setText(rows);

        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void countVotees(){
        DatabaseConnection conn = new DatabaseConnection();
        Connection connectDb = conn.getConnection();

        try {
            Statement statement = connectDb.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM barangaysys.brgy_residents WHERE voterStatus= 'Yes';");
            int count = 0;
            while (result.next()){
                count++;
            }
            String rows = Integer.toString(count);
            VoteesCount.setText(rows);
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }

    public void exitBrgyProf(javafx.event.ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) btnExitMenu.getScene().getWindow();
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

    public void showAddEmployee(javafx.event.ActionEvent actionEvent) throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("res/BrgyProf/AddEmployees.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 403, 217));
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        System.out.println("test");
    }

    public void refresh(javafx.event.ActionEvent actionEvent) throws IOException{
        showOfficialList();
        countEmployees();
        countResidents();
        countVotees();
    }

    public void editEmployee(javafx.event.ActionEvent actionEvent) throws IOException{
        try {
            conn = Dbs.getConnection();
            String value0 =txt_brgyID.getText();
            String value1 = txt_firstName.getText();
            String value2 = txt_lastName.getText();
            String value3 = txt_Position.getText();
            String query = "UPDATE barangaysys.brgy_officials SET brgy_id= '"+ value0 +"',firstName= '" + value1 + "',lastName= '" + value2 + "',position= '" + value3 +
                    "' WHERE brgy_id='" + value0 + "';" ;

            statement = conn.prepareStatement(query);
            statement.execute();
            showOfficialList();

        }catch (Exception e){
            e.getCause();
        }
    }

    public void deleteEmployee(javafx.event.ActionEvent actionEvent) throws IOException{
        conn = Dbs.getConnection();
        String query = "DELETE from barangaysys.brgy_officials WHERE brgy_id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1,txt_brgyID.getText());
            statement.execute();
            showOfficialList();
            txt_brgyID.setText("");
            txt_firstName.setText("");
            txt_lastName.setText("");
            txt_Position.setText("");
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    @FXML
    void getSelected(MouseEvent event) throws IOException {
        index = table_users.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }
        txt_firstName.setText(col_FirstName.getCellData(index).toString());
        txt_lastName.setText(col_LastName.getCellData(index).toString());
        txt_Position.setText(col_Position.getCellData(index).toString());
        txt_brgyID.setText(col_Id.getCellData(index).toString());

    }










}
