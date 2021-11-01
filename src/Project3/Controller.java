package Project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Main used for launching the GUI
 * @author Brian Wang, Kyle Sia
 */

public class Controller {
    private Roster list = new Roster();

    public static final int EMPTY = 0;
    public static final int MINCRED = 3;
    public static final int NEGCRED = 0;
    public static final int MAXCRED = 24;
    public static final int INTERNATIONALCREDIT = 12;
    public static final int MISSING = -1;

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

    /**
     * clears tab1 except the students tuition display
     */
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

    /**
     * clears all inputs from tab2
     */
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

    /**
     * disables buttons if they are a tristate student
     * @param event
     */
    @FXML
    void isATriState(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(false);
        });
        international.setSelected(false);
        international.setDisable(true);
    }

    /**
     * disables buttons if they are an international student
     * @param event
     */
    @FXML
    void isInternational(ActionEvent event){
        state.getToggles().forEach(toggle -> {
            RadioButton tempButton = (RadioButton) toggle;
            tempButton.setDisable(true);
            tempButton.setSelected(false);
        });
        international.setDisable(false);


    }

    /**
     * disables buttons if they are a resident
     * @param event
     */
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

    /**
     * disables buttons if they are a nonresident
     * @param event
     */
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
        paymentDate.getEditor().setEditable(false);
        String currState;
        String selectedRes = null;
        Profile tProf = createProfile();
        boolean isAbroad = false;
        if(tProf == null)
            return;
        String stringChecker = creditHours.getText();
        if(creditHours.getText().isEmpty() || name.getText().isEmpty()){
            display.appendText("Invalid Inputs \n");
            return;
        }
        char checker = creditHours.getText().charAt(EMPTY);

        if(!Character.isDigit(checker) && checker!='-') {
            display.appendText("Invalid credit hours \n" );
            return;
        }
        int credits = Integer.parseInt(creditHours.getText());
        if(tProf == null){
            return;
        }
        Date tempDate = new Date("--/--/--");
        try{
            if(typeOfResident.getSelectedToggle() != null)
                selectedRes= ((RadioButton) typeOfResident.getSelectedToggle()).getText();
            else
                throw new Exception();
        }catch(Exception e){
            display.appendText("Missing student type.\n");
            return;
        }
        if(selectedRes.equals("Resident")) {
            if (credits < NEGCRED) {
                display.appendText("Credit hours can not be negative \n");
                return;
            } else if (credits < MINCRED) {
                display.appendText("Minimum credit hours is 3. \n");
                return;
            }
            if (credits > MAXCRED) {
                display.appendText("Credit hours exceed the maximum 24. \n");
                return;
            }
            Resident currRes = new Resident(tProf, credits, tempDate);
            if (!(this.find(list, currRes) == MISSING)){
                display.appendText("Student is already in the roster \n");
                return;
            }
            else{
                list.add(currRes);
                display.appendText("Student added \n");
            }

        }
        else if(selectedRes.equals("Non-Resident")){
            if(credits < NEGCRED) {
                display.appendText("Credit hours can not be negative\n");
                return;
            }
            else if(credits < MINCRED){
                display.appendText("Minimum credit hours is 3.\n");
                return;
            }
            if(credits > MAXCRED){
                display.appendText("Credit hours exceed the maximum 24.\n");
                return;
            }
            NonResident currRes = new NonResident(tProf,credits,tempDate);
            if (!(this.find(list,currRes) == MISSING)) {
                display.appendText("Student is already in the roster\n");
                return;
            }
            else{
                list.add(currRes);
                display.appendText("Student added\n");
            }

        }
        else if(selectedRes.equals("TriState")){

            try{
                if(state.getSelectedToggle() != null)
                    currState= ((RadioButton) state.getSelectedToggle()).getText();
                else
                    throw new Exception();
            }catch(Exception e){
                display.appendText("Missing State.\n");
                return;
            }
            if(credits < NEGCRED) {
                display.appendText("Credit hours can not be negative\n");
                return;
            }
            else if(credits < MINCRED){
                display.appendText("Minimum credit hours is 3. \n");
                return;
            }
            if(credits > MAXCRED){
                display.appendText("Credit hours exceed the maximum 24. \n");
                return;
            }

            TriState currTriState = new TriState(tProf,credits,tempDate,currState);
            if (!(this.find(list,currTriState) == MISSING)){
                display.appendText("Student is already in the roster \n");
                return;
            }
            else{
                list.add(currTriState);
                display.appendText("Student added \n");
            }
        }
        else if(selectedRes.equals("International"))
        {
            if (credits < NEGCRED) {
                display.appendText("Credit hours can not be negative. \n");
                return;
            }else if (credits < MINCRED) {
                display.appendText("Minimum credit hours is 3. \n");
                return;
            }else if (credits < INTERNATIONALCREDIT) {
                display.appendText("International students must enroll at least 12 credits. \n");
                return;
            }
            if (credits > MAXCRED) {
                display.appendText("Credit hours exceed the maximum 24. \n");
                return;
            }

            if(international.isSelected()){
                isAbroad = true;
            }
            else{
                isAbroad = false;
            }
            International currInt = new International(tProf,credits,tempDate,isAbroad);

            if (!(this.find(list,currInt) == MISSING)) {
                display.appendText("Student is already in the roster\n");
                return;
            }
            else{
                list.add(currInt);
                display.appendText("Student added \n");
            }
        }
        else{
            display.appendText("Type of student does not exist\n");
            return;
        }
        //display.appendText(list.printStudent() + "\n");

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

    /**
     * creates a profile, taking in name and major from the inputs
     * @return
     */
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
     * Event handler for the remove student button
     * removes student based on user input
     * @param event
     */
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

    /**
     * event handler for tuition due button
     * calculates tuition for one student and updates it in roster
     */
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


    /**
     * event handler for the calculate button
     * calculates all students tuition
     * @param event
     */
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

    /**
     * Action Event handler for help->about button
     * @param event
     */
    @FXML
    void help(ActionEvent event){
        display.appendText("Tuition Manager Project With JavaFX by Brian Wang and Kyle Sia \n");
    }

    /**
     * event handler for pay button
     * Makes a payment based off user input
     * @param event
     */
    @FXML
    void pay(ActionEvent event){
        if(sName.getText().isEmpty()){
            display.appendText("Name not inputted \n");
            return;
        }
        String currName = sName.getText();
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
        if(find(list,currStudent) == MISSING){
            display.appendText("Student not in roster \n");
            return;
        }
        if(paymentDate.getValue() == null){
            display.appendText("Date not selected \n");
            return;
        }
        if(paymentAmount.getText().isEmpty()){
            display.appendText("Payment Amount is empty \n");
            return;
        }
        double pay = Double.parseDouble(paymentAmount.getText());
        if(pay < EMPTY){
            display.appendText("Can not have negative payment \n");
            return;
        }else if(pay > list.getRoster()[this.find(list,currStudent)].getTuition()){
            display.appendText("Amount is greater than amount due \n");
            return;
        }
        String date = paymentDate.getValue().toString();
        Date payDate = new Date(date);
        if(!payDate.isValid()){
            display.appendText("Payment date invalid \n");
            return;
        }
        list.getRoster()[this.find(list,currStudent)].updateTuition(pay, payDate);
        display.appendText("Payment applied. \n");
        tab2();
    }

    /**
     * Event handler for set button
     * Sets a students financial aid amount
     * @param event
     */
    @FXML
    void set(ActionEvent event){
        if(sName.getText().isEmpty()){
            display.appendText("Name not inputted \n");
            return;
        }
        String currName = sName.getText();
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
        if(find(list,currStudent) == MISSING){
            display.appendText("Student not in roster \n");
            return;
        }
        if(fAid.getText().isEmpty()){
            display.appendText("Financial Aid Amount missing \n");
            return;
        }
        double financial = Double.parseDouble(fAid.getText());
        if(financial < EMPTY){
            display.appendText("Financial aid amount can not be negative \n");
            return;
        }
        int currIndex = this.find(list,currStudent);
        if(currIndex != MISSING){
            if(list.getRoster()[currIndex].getCredits() < INTERNATIONALCREDIT){
                display.appendText("Parttime student doesn't qualify for the award \n");
                return;
            }
            if(list.getRoster()[currIndex] instanceof Resident){
                if(((Resident) list.getRoster()[currIndex]).getReceivedAid()){
                    display.appendText("Awarded once already. \n");
                    return;
                }
                ((Resident) list.getRoster()[currIndex]).setReceivedAid(financial);
                display.appendText("Tuition updated. \n");
            }
            else
            {
                display.appendText("Not a resident student. \n");
                return;
            }
        }
        else
            display.appendText("Student is not in the roster \n");
        tab2();

    }


    /**
     * event handler for print as is button
     * prints all students in roster as is
     * @param event
     */
    @FXML
    void print(ActionEvent event){
        display.appendText(list.print() +"\n");
    }

    /**
     * event handler for print by name button
     * prints all students based on name
     * @param event
     */
    @FXML
    void printName(ActionEvent event){
        display.appendText(list.printByName()+ "\n");
    }

    /**
     * event handler for print by payment button
     * prints all students based on their payment
     * @param event
     */
    @FXML
    void printPayment(ActionEvent event){
        display.appendText(list.printByPaymentDate() +"\n");
    }


}
