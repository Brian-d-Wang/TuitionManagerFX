package Project3;


/**
 * International class that extends the NonResident class
 * Has variables for isStudyAbroad
 * @author Brian Wang, Kyle Sia
 */

public class International extends NonResident {
    public static final int NOEXTRAFEECAP = 16;
    public static final int NONRESIDENTCREDITHOUR = 966;
    public static final int MINFULLTIME = 12;
    public static final int NONRESTUITION = 29737;
    public static final int FULLTIMEUNIFEE = 3268;
    public static final int ADDFEE = 2650;
    public static final Date BLANKDATE = new Date("--/--/--");
    private boolean isStudyAbroad; //boolean if the student is studying abroad.

    /**
     * Creates an International object that has profile, credits, lastPayment, and isStudyAbroad
     *
     * @param profile       is the profile of international student
     * @param credits       is the number of credit hours of international student
     * @param lastPayment   is the last date the international student made a payment
     * @param isStudyAbroad is if the international student is studying abroad or not
     */
    public International(Profile profile, int credits, Date lastPayment, boolean isStudyAbroad) {
        super(profile, credits, lastPayment);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Creates an International object that has a profile
     *
     * @param profile is the profile of the international student
     */
    public International(Profile profile) {
        super(profile, 0, BLANKDATE);
        this.isStudyAbroad = true;
    }

    /**
     * Returns the International student's isStudyAbroad value
     *
     * @return the boolean value of if the international student is studying abroad or not
     */
    public boolean getIsStudyAbroad() {
        return this.isStudyAbroad;
    }

    /**
     * Overides the student's tuitionDue method to calculate an International's tuition
     */
    @Override
    public void tuitionDue() {
        if (this.getCredits() > NOEXTRAFEECAP)  //case for 17-24 credits
            this.setTuition(ADDFEE + NONRESTUITION + FULLTIMEUNIFEE + (this.getCredits() - NOEXTRAFEECAP)
                    * NONRESIDENTCREDITHOUR);
        else if (this.getCredits() < MINFULLTIME) //case for 3-11
            this.setTuition(ADDFEE + NONRESTUITION + FULLTIMEUNIFEE);
        else //case for 12-16 credits
            this.setTuition(ADDFEE + NONRESTUITION + FULLTIMEUNIFEE);
        if (isStudyAbroad) //study abroad students do not pay tuition fee
            this.setTuition(this.getTuition() - NONRESTUITION);
    }

    /**
     * sets an international students studyAbroad status
     *
     * @param abroad is true or false depending on if the international student is studying abroad
     * @return true if the student is studying abroad or false if the student is already studying abroad or if the
     * student is not studying abroad
     */
    public boolean setStudyAbroad(boolean abroad) {
        if (abroad && this.isStudyAbroad) {
            return false;
        }
        if (!abroad) {
            this.isStudyAbroad = false;
            return false;
        }
        this.isStudyAbroad = true;
        return true;
    }

    /**
     * toString method that prints out the international's info
     *
     * @return String that prints out the information of an international.
     */
    @Override
    public String toString() {

        if (this.isStudyAbroad) {
            return super.toString() + ":international:study abroad";
        }
        return super.toString() + ":international";
    }

}