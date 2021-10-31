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
    private TextField name, creditHours, payment, sName,paymentAmount,fAid;

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
     */
    @FXML
    void tab1(){
        if(!name.getText().isEmpty())
            name.clear();
        if(!creditHours.getText().isEmpty())
            creditHours.clear();
        if(!payment.getText().isEmpty())
            payment.clear();
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
    void tab1Tuition(){
        if(!name.getText().isEmpty())
            name.clear();
        if(!creditHours.getText().isEmpty())
            creditHours.clear();
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
    void tab2(){
        if(!sName.getText().isEmpty())
            sName.clear();
        if(!paymentAmount.getText().isEmpty())
            paymentAmount.clear();
        if(!fAid.getText().isEmpty())
            fAid.clear();
        paymentDate.setValue(null);
        major.getToggles().forEach(toggle -> {
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

    @FXML
    void remove(ActionEvent event){
        if(name.getText().isEmpty()){
            display.appendText("Name not inputted \n");
            return;
        }
        String currName = name.getText();
        Major tempMajor = null;
        if(major.getSelectedToggle() != null)
        {
            tempMajor = Major.valueOf(((RadioButton) major.getSelectedToggle()).getText());
        }
        else
        {
            display.appendText("Missing student major \n");
            return;
        }
        Profile tempProf = new Profile(currName,tempMajor);
        Student currStudent = new Student(tempProf);
        if(list.remove(currStudent))
            display.appendText("Student removed from roster \n");
        else
            display.appendText("Student is not in the roster \n");

        typeOfResident.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });

        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });

        major.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setSelected(false);
        });
        this.tab1();
    }

    @FXML
    void calcStudent(){
        if(!payment.getText().isEmpty())
            payment.clear();
        if(name.getText().isEmpty()){
            display.appendText("Name not inputted \n");
            return;
        }
        String currName = name.getText();
        Major tempMajor = null;
        if(major.getSelectedToggle() != null)
        {
            tempMajor = Major.valueOf(((RadioButton) major.getSelectedToggle()).getText());
        }
        else
        {
            display.appendText("Missing student major \n");
            return;
        }
        Profile tempProf = new Profile(currName,tempMajor);
        Student currStudent = new Student(tempProf);
        int index = find(list,currStudent);
        if(index == MISSING){
            display.appendText("Student not in roster \n");
            return;
        }
        if (list.getRoster()[index].getTotalPayments() == 0 && list.getRoster()[index].getTuition() == 0)
            list.getRoster()[index].tuitionDue();
        payment.appendText(list.getRoster()[index].getTuition() + "");
        display.appendText("Tuition displayed next to Tuition Due and tuition updated in roster \n");
        tab1Tuition();
    }


    @FXML
    void calcTuition(ActionEvent event){
        if(list.getSize() == EMPTY){
            display.appendText("No Students to compute \n");
            return;
        }
        for(int i = 0; i < list.getSize(); i++) {
            if (list.getRoster()[i].getTotalPayments() == 0 && list.getRoster()[i].getTuition() == 0)
                list.getRoster()[i].tuitionDue();
        }
        display.appendText("Calculation completed \n");
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
