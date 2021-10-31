package Project3;

/**
 * Roster class that holds all the students and expands, 4 at a time, if needed
 * @author Brian Wang, Kyle Sia
 */
public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster
    public static final int MISSING = -1;
    public static final int INCREASE = 4;
    public static final int LOW = 0;
    public static final int COMPAREDATE= -1;
    public static final int COMPARENAME = 0;
    public static final boolean BY_DATE = true;
    public static final boolean BY_NAME = false;

    /**
     * Creates an array of albums with size 4 and number of albums is 0
     */
    public Roster() {
        this.roster = new Student[INCREASE];
        size = 0;
    }

    /**
     * Returns the array of the list of students
     * @return an array that holds all the students
     */
    public Student[] getRoster(){return this.roster;}

    /**
     * returns the Roster's size value
     * @return an int of how many students can be held in the roster
     */
    public int getSize(){return this.size;}

    /**
     * searches to see if a student exists in the roster
     * @param student to be found
     * @return the index of the given student or -1 (MISSING) if they do not exist
     */
    private int find(Student student) {
        int studentNum = MISSING;
        for(int i = 0; i < this.size; i++){
            if(roster[i].equals(student)){
                studentNum = i;
                break;
            }
        }
        return studentNum;
    }

    /**
     * increase the capacity of the rosters array by 4
     */
    private void grow() {
        int newMax = this.size + INCREASE;
        Student[] newRoster = new Student[newMax];
        for(int i = 0; i < this.size; i++){
            newRoster[i] = roster[i];
        }
        this.roster = newRoster;
    }

    /**
     * Adds a student to the roster
     * @param student to be added
     * @return true if the student was added or false if the student failed to be added
     */
    public boolean add(Student student) {
        int currIndex = find(student);
        if(currIndex >= LOW) //means there is a duplicate student
            return false;
        if(this.roster.length == this.size)
            this.grow();
        this.roster[size] = student;
        this.size++;
        return true;
    }


    /**
     * Removes a student from the roster and shifts students accordingly
     * @param student to be removed
     * @return true if the student was removed or false if the removal failed
     */
    public boolean remove(Student student) {
        if(student == null) //if the student is empty
            return false;
        int currIndex = this.find(student); //checks to see if the student exists
        if(currIndex == MISSING) //returns false if the student is missing
            return false;
        this.roster[currIndex] = null;
        //loops through the roster to shift all the students to fill in the missing removed student
        for(int i = currIndex; i < this.size - 1; i++){
            this.roster[i] = this.roster[i+1];
            currIndex++;
        }
        //sets the last album to null in case the collection was full but does not change the max capacity
        this.roster[this.size - 1] = null;
        this.size--;
        return true;

    }

    /**
     * prints out the roster with no specific order
     */
    public String print() {
        if(this.size == LOW){
            return "Student roster is empty!";
        }
        String ret = "";
        ret = ret + "* list of students in the roster ** \n";
        for(int i = 0; i < this.size; i++)
            ret = ret + this.roster[i].toString() + "\n";
        ret = ret + "* end of of roster ** ";
        return ret;
    }


    /**
     * Prints the list of Students in the Roster as is
     * Used for printing by name
     */
    public String printName() {
        String ret ="";
        for(int i = 0; i < this.size; i++)
            ret = ret + this.roster[i].toString() + "\n";
        return ret;
    }

    /*
    public String printStudent(){
        return this.roster[this.size -1].toString() ;
    }
    */


    /**
     * Prints the list of Students in the Roster as is
     * Used for printing paid students by release date
     */
    public String printDate() {
        String ret ="";
        for(int i = 0; i < this.size; i++)
            if(this.roster[i].getTotalPayments()!=0)
               ret = ret + this.roster[i].toString() + "\n";
        return ret;
    }

    /**
     * Prints the list of Students in the Roster by date paid in ascending order.
     */
    public String printByPaymentDate() {
        if(this.size == 0){
            return "Student roster is empty!";
        }
        String ret ="";
        ret = ret + "* list of students made payments ordered by payment date ** \n";
        sortBoth(this.roster, LOW, this.size - 1, BY_DATE);
        ret = ret + this.printDate();
        ret = ret +"* end of roster **";
        return ret;
    }
    /**
     * Prints the list of Students in the Roster by their name in alphabetical order.
     */
    public String printByName() {
        if(this.size == 0){
            return "Student roster is empty!";
        }
        String ret ="";
        ret = ret + "* list of students ordered by name **\n";
        sortBoth(this.roster, LOW, this.size - 1, BY_NAME);
        ret = ret + this.printName() + "";
        ret = ret + "** end of roster ** ";
        return ret;
    }
    /**
     * Quick sort method that sorts the students in roster in ascending order.
     * Divide and conquer method for the array of students.
     * @param roster the collection of student to be sorted
     * @param low the beginning index of the array to be sorted
     * @param high the end index of the array to be sorted
     * @param typeSort sorting either by Name or Date
     */
    public void sortBoth(Student [] roster, int low, int high, boolean typeSort){
        if(low < high) {
            int partitionIndex = partition(roster, low, high, typeSort);
            sortBoth(roster, low, partitionIndex - 1, typeSort);
            sortBoth(roster, partitionIndex + 1, high, typeSort);
        }
    }
    /**
     * Helper method for Quick sort that partitions the array to swap elements.
     * @param roster the array to be sorted
     * @param low the beginning index of the array
     * @param high the end index of the array
     * @param typeSort sorting either by Date or by Name
     * @return int - index number of the element to be partitioned
     */
    public int partition(Student[] roster, int low, int high, boolean typeSort){
        Student pivot = roster[high];
        int small = (low - 1);

        for(int j = low; j < high; j++) {
            if(typeSort == BY_DATE) {
                if(roster[j].getLastPayment().compareTo(pivot.getLastPayment()) == COMPAREDATE) {
                    small++;
                    Student temp = roster[small];
                    roster[small] = roster[j];
                    roster[j] = temp;
                }
            } else {
                if(roster[j].getProfile().getName().compareTo(pivot.getProfile().getName()) <= COMPARENAME) {
                    small++;
                    Student temp = roster[small];
                    roster[small] = roster[j];
                    roster[j] = temp;
                }
            }
        }

        Student temp = roster[small + 1];
        roster[small + 1] = roster[high];
        roster[high] = temp;
        return small + 1;
    }

}