package brgySys.obj;

import javafx.beans.property.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BrgyResident {
    private SimpleIntegerProperty resId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty middleName;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty placeofBirth;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private SimpleStringProperty gender;
    private SimpleStringProperty voterStatus;
    private SimpleStringProperty civilStatus;
    private SimpleIntegerProperty residency;


    public  BrgyResident(Integer resId, String firstName, String lastName, String middleName, String birthDate,
                         String placeofBirth, String phoneNumber, String gender, String address, String voterStatus,
                         String civilStatus, Integer residency){
        this.resId = new SimpleIntegerProperty(resId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.placeofBirth = new SimpleStringProperty(placeofBirth);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.gender = new SimpleStringProperty(gender);
        this.address = new SimpleStringProperty(address);
        this.voterStatus = new SimpleStringProperty(voterStatus);
        this.civilStatus = new SimpleStringProperty(civilStatus);
        this.residency = new SimpleIntegerProperty(residency);
    }

    public IntegerProperty resIdProperty(){
        return resId;
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    public StringProperty lastNameProperty(){
        return lastName;
    }

    public StringProperty middleNameProperty(){
        return middleName;
    }



    public StringProperty placeofBirthProperty(){
        return placeofBirth;
    }

    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }

    public StringProperty genderProperty(){
        return gender;
    }

    public StringProperty voterStatusProperty(){
        return voterStatus;
    }

    public StringProperty civilStatusProperty(){
        return civilStatus;
    }

    public StringProperty addressProperty(){
        return address;
    }

    public StringProperty birthDateProperty(){
        return birthDate;
    }

    public IntegerProperty residencyProperty() {
        return residency;
    }


}
