package Project3;


/**
 * Student class that has profile, credits, totalPayment, lastPayment, and tuition variables
 * @author Brian Wang, Kyle Sia
 */
public class Student {

    private Profile profile; //profile of student i.e. name and major
    private int credits; //the amount of credits student is taking
    private double totalPayment;
    private Date lastPayment; //last payment made by student
    private double tuition; //tuition of student;


    /**
     * Creates a Student object that has profile,credits, and lastPayment
     * @param profile is the profile of the student
     * @param credits is the number of credit hours of the student
     * @param lastPayment is the last time the student paid
     */
    public Student(Profile profile, int credits, Date lastPayment){
        this.profile = profile;
        this.credits = credits;
        this.totalPayment = 0;
        this.lastPayment = lastPayment;
        this.tuition = 0;
    }

    /**
     * Creates a student object that has profile
     * @param profile is the profile of the student
     */
    public Student(Profile profile){
        this.profile = profile;
        this.credits = 0;
        this.totalPayment = 0;
        this.lastPayment = new Date("--/--/--");
        this.tuition = 0;
    }

//getter methods

    /**
     * returns the students profile values
     * @return profile of the student
     */
    public Profile getProfile(){return this.profile;}

    /**
     * returns the students credits value
     * @return int of how many credit hours the student is taking
     */
    public int getCredits(){return this.credits;}

    /**
     * returns the students totalPayment value
     * @return double of how much the student has paid so far
     */
    public double getTotalPayments(){return this.totalPayment;}

    /**
     * returns the date of the last time the student paid
     * @return Date object of when the student paid last
     */
    public Date getLastPayment(){return(this.lastPayment);}

    /**
     * returns the tuition value of the student
     * @return a double of how much a students tuition is
     */
    public double getTuition(){return this.tuition;}


//setter methods

    /**
     * sets a students tuition value
     * @param newTuition is a students tuition amount
     */
    public void setTuition(double newTuition){
        this.tuition = newTuition;
    }

    /**
     * sets a students credit value
     * @param credits is a students credit amount
     */
    public void setCredits(int credits){
        this.credits = credits;
    }

    /**
     * sets a students tuition amount given a payment
     * @param payment is a double of how much a student has paid
     * @param paymentDate is a Date object of when the student paid last
     */
    public void updateTuition(double payment, Date paymentDate)
    {
        this.totalPayment = this.totalPayment + payment;
        this.lastPayment = paymentDate;
        this.tuition = this.tuition - payment;
    }

    /**
     * Compares an object to see if it is a student object
     * @param obj to be compared
     * @return returns true if the obj is a student, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student comparison = (Student) obj;
            return comparison.getProfile().equals(this.profile);
        }
        return false;

    }

    /**
     * doNothing method that subclasses will overide
     */
    public void tuitionDue(){

    }

    public String toString(){
        return(this.profile.toString() + ":" + this.credits + " credit hours" );
    }

}