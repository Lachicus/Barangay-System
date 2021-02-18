package brgySys.obj;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BrgyOfficials{

    private SimpleIntegerProperty brgy_id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty position;

    public  BrgyOfficials(Integer brgy_id, String firstName, String lastName, String position){
        this.brgy_id = new SimpleIntegerProperty(brgy_id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.position = new SimpleStringProperty(position);
    }

    public IntegerProperty brgy_idProperty(){
        return brgy_id;
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    public StringProperty lastNameProperty(){
        return lastName;
    }

    public StringProperty positionProperty(){
        return position;
    }


}
