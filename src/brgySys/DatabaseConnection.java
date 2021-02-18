package brgySys;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import brgySys.obj.*;
import java.sql.*;
public class DatabaseConnection {
    public Connection databaseLink;


    public Connection getConnection(){
        String databaseName = "barangaysys";
        String databaseuser = "root";
        String databasepassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseuser,databasepassword);
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

    public ObservableList<BrgyOfficials> getOfficialList(){
        ObservableList<BrgyOfficials> officialList = FXCollections.observableArrayList();
        Connection connectDB = getConnection();

        String query = "SELECT * FROM barangaysys.brgy_officials";
        try {
            PreparedStatement ps = connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                officialList.add(new BrgyOfficials(Integer.parseInt(rs.getString("brgy_id")),rs.getString("firstName"), rs.getString("lastName"), rs.getString("position")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return officialList;
    }

    public ObservableList<BrgyResident> getResidentList(){
        ObservableList<BrgyResident> residentList = FXCollections.observableArrayList();
        Connection connectDB = getConnection();

        String query = "SELECT * FROM barangaysys.brgy_residents";

        try{
            PreparedStatement ps = connectDB.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

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
}
