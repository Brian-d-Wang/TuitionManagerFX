package Project3;

/**
 * NonResident class that extends Student class
 * @author Brian Wang, Kyle Sia
 */
public class NonResident extends Student{
    public static final int NOEXTRAFEECAP = 16;
    public static final int MINFULLTIME = 12;
    public static final int NONRESIDENTCREDITHOUR = 966;
    public static final int INTERTUITION = 29737;
    public static final int FULLTIMEUNIFEE = 3268;
    public static final double PARTTIMEUNIFEE = 2614.4;


    /**
     * Creates a NonResident object that has profile,credits, and lastPayment
     * @param profile is the profile of the nonResident
     * @param credits is the number of credit hours of the nonResident
     * @param lastPayment is the last date the nonResident paid
     */
    public NonResident(Profile profile, int credits, Date lastPayment){
        super(profile,credits,lastPayment);
    }

    /**
     * Overides the student's tuitionDue method to calculate a nonResident's tuition
     */
    @Override
    public void tuitionDue() {
        if (this.getCredits() > NOEXTRAFEECAP)  //case for 17-24 credits
            this.setTuition(INTERTUITION + FULLTIMEUNIFEE + (this.getCredits() - NOEXTRAFEECAP)
                    * NONRESIDENTCREDITHOUR);
        else if (this.getCredits() < MINFULLTIME) //case for 3-11 credits
            this.setTuition(PARTTIMEUNIFEE + this.getCredits() * NONRESIDENTCREDITHOUR);
        else //case for 12-16 credits
            this.setTuition(INTERTUITION + FULLTIMEUNIFEE);
    }

    /**
     * toString method that prints out the nonResident's info
     * @return String that prints out the information of a nonResident.
     */
    @Override
    public String toString() {

        return super.toString() + ":tuition due:" + String.format("%.2f",this.getTuition()) + ":total payment:" +
                String.format("%.2f",this.getTotalPayments()) +
                ":last payment date:" + this.getLastPayment().toString() + ":non-resident";
    }


}
