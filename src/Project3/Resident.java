package Project3;


/**
 * Resident class that extends the Student class
 * Has variables for receivedAid and aidReceived
 * @author Brian Wang, Kyle Sia
 */

public class Resident extends Student{

    public static final int NOEXTRAFEECAP = 16;
    public static final int MINFULLTIME = 12;
    public static final int RESIDENTCREDITHOUR = 404;
    public static final int RESTUITION = 12536;
    public static final int FULLTIMEUNIFEE = 3268;
    public static final double PARTTIMEUNIFEE = 2614.4;
    private boolean receivedAid;
    private double aidReceived;

    /**
     * Creates a resident object that has a profile, credit, and last payment
     * @param profile of the resident
     * @param credits of the resident
     * @param lastPayment of the resident
     */
    public Resident(Profile profile, int credits, Date lastPayment){
        super(profile, credits, lastPayment);
        this.receivedAid = false;
        this.aidReceived = 0;
    }

    /**
     * sets if a student received aid
     * @param aid is the amount of financial aid the resident got
     * @return true if the student has not received any aid before or false if the resident already got aid
     */
    public boolean setReceivedAid(double aid){
        if(this.receivedAid == true)
            return false;
        this.receivedAid = true;
        this.setTuition(this.getTuition() - aid);
        this.aidReceived = aid;
        return true;
    }

    /**
     * returns the Resident objects receivedAid value
     * @return boolean depending on if the student has received aid before or not
     */
    public boolean getReceivedAid(){
        return this.receivedAid;
    }


    /**
     * Overides the student's tuitionDue method to calculate a resident's tuition
     */
    @Override
    public void tuitionDue() {
        if (this.getCredits() > NOEXTRAFEECAP)  //case for 24-17 credits
            this.setTuition(RESTUITION + FULLTIMEUNIFEE + (this.getCredits() - NOEXTRAFEECAP)
                    * RESIDENTCREDITHOUR);
        else if (this.getCredits() < MINFULLTIME) //case for 3-11 credits
            this.setTuition(PARTTIMEUNIFEE + this.getCredits() * RESIDENTCREDITHOUR);
        else //case for 12-16 credits
            this.setTuition(RESTUITION + PARTTIMEUNIFEE);

    }

    /**
     * toString method that prints out the resident's info
     * @return String that prints out the information of a Resident.
     */
    @Override
    public String toString() {

        if(this.receivedAid)
        {
            return super.toString() + ":tuition due:" + String.format("%.2f",this.getTuition()) + ":total payment:" +
                    String.format("%.2f",this.getTotalPayments()) +
                    ":last payment date:" + this.getLastPayment().toString() + ":resident:financial aid " +
                    this.aidReceived;
        }
        return super.toString() + ":tuition due:" + String.format("%.2f",this.getTuition()) + ":total payment:" +
                String.format("%.2f",this.getTotalPayments())
                + ":last payment date:" + this.getLastPayment().toString() + ":resident";
    }

}

