package Project3;

/**
 * TriState class that extends the NonResident class
 * Has variables for state
 * @author Brian Wang, Kyle Sia
 */

public class TriState extends NonResident{
    public static final int NOEXTRAFEECAP = 16;
    public static final int MINFULLTIME = 12;
    public static final int NONRESIDENTCREDITHOUR = 966;
    public static final int INTERTUITION = 29737;
    public static final int FULLTIMEUNIFEE = 3268;
    public static final double PARTTIMEUNIFEE = 2614.4;
    public static final int NYDISCOUNT = 4000;
    public static final int CTDISCOUNT = 5000;
    private String state;

    /**
     * creates a TriState object that has profile, credits, lastPayment, and state
     * @param profile of the Tristate student
     * @param credits of the TriState student
     * @param lastPayment of the TriState student
     * @param state of the TriState student
     */
    public TriState(Profile profile, int credits, Date lastPayment, String state) {
        super(profile, credits, lastPayment);
        this.state = state;
    }

    /**
     * returns the state value of a TriState student
     * @return a string of the TriState students state
     */
    public String getState(){return this.state;}

    /**
     * Overides the student's tuitionDue method to calculate a TriState student's tuition
     */
    @Override
    public void tuitionDue() {
        if (this.getCredits() > NOEXTRAFEECAP) {  //case for 24-17 credits
            this.setTuition(INTERTUITION + FULLTIMEUNIFEE + (this.getCredits() - NOEXTRAFEECAP)
                    * NONRESIDENTCREDITHOUR);
            if (this.state.equals("NY"))
                this.setTuition(this.getTuition() - NYDISCOUNT);
            else if (this.state.equals("CT"))
                this.setTuition(this.getTuition() - CTDISCOUNT);
        }
        else if (this.getCredits() < MINFULLTIME) //case for 3-11 credits
            this.setTuition(PARTTIMEUNIFEE + this.getCredits() * NONRESIDENTCREDITHOUR);
        else{//case for 12-16 credits
            this.setTuition(INTERTUITION + FULLTIMEUNIFEE);
            if(this.state.equals("NY"))
                this.setTuition(this.getTuition() - NYDISCOUNT);
            else if(this.state.equals("CT"))
                this.setTuition(this.getTuition() - CTDISCOUNT);
        }


    }

    /**
     * toString method that prints out the TriState students info
     * @return String that prints out the information of a TriState student.
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + this.state;
    }
}
