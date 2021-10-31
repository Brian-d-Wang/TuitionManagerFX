package Project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {
    private Roster list = new Roster();

    public static final int EMPTY = 0;

    @FXML
    private TextField name, creditHours, payment, paymentDue, tuitionDue,fAid;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private TextArea display;

    @FXML
    private ToggleGroup major, typeOfResident, state;

    @FXML
    private CheckBox international;


    /**
     * Clears student info in tab1
     * @param event
     */
    @FXML
    void tab1(ActionEvent event){
        name.clear();
        creditHours.clear();
        tuitionDue.clear();
        major.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });
        typeOfResident.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });
    }

    @FXML
    void isATriState(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(false);
        });
        international.setSelected(false);
        international.setDisable(true);
    }

    @FXML
    void isInternational(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(true);
            tempButton.setSelected(false);
        });
        international.setDisable(false);


    }

    @FXML
    void isAResident(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(true);
            tempButton.setSelected(false);
        });
        international.setDisable(true);
        international.setSelected(false);
    }

    @FXML
    void isNonResident(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(true);
            tempButton.setSelected(false);
        });
        international.setSelected(false);
    }

    /**
     * Disable radio buttons if they are a resident
     * @param event
     */
    @FXML
    void residentToggle(ActionEvent event) {
        typeOfResident.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(true);
        });
        //international.setSelected(false);
    }

    /**
     * Event Handler for the Add Student button
     * Adds Student using user input to roster
     * @param event
     */
    @FXML
    void add(ActionEvent event) {
        String currName = null;
        Student currStudent = null;
        String selectedRes = null;
        try{
            if(typeOfResident.getSelectedToggle() != null)
                selectedRes= ((RadioButton) typeOfResident.getSelectedToggle()).getText();
            else
                throw new Exception();
        }catch(Exception e){
            display.appendText("Missing student type.\n");
            return;
        }
    }

    Profile createProfile(){
        String specialChar = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String studentName = name.getText();
        String[] arrName = studentName.split("");
        Major tempMajor = null;

        for(int i = 0; i < studentName.length(); i++){
            if(specialChar.contains(arrName[i])){
                display.appendText("Invalid Name. No Special Characters\n");
                return null;
            }
        }

        if(major.getSelectedToggle() != null)
        {
            tempMajor = Major.valueOf(((RadioButton) major.getSelectedToggle()).getText());
        }
        else
        {
            display.appendText("Missing student major\n");
            return(null);
        }


        return(new Profile(studentName, tempMajor));
    }

    /**
     * helper method to find a student in our roster
     * @param database is the roster
     * @param currStudent is the current student to be found
     * @return int of the index of the student or -1 (MISSING) if the student is not in the list
     */
    int find(Roster database, Student currStudent){
        int studentNum = MISSING;
        for(int i = 0; i < database.getSize(); i++){
            if(database.getRoster()[i].getProfile().equals(currStudent.getProfile())){
                studentNum = i;
                break;
            }
        }
        return studentNum;
    }
    @FXML
    void remove(ActionEvent event){
        String name;
        try{





}
