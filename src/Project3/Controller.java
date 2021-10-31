package Project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
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





}
