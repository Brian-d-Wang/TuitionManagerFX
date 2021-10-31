package Project3;


import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Date class created from a String of a date
 * Has variables of Year, Month, and day
 * @author Brian Wang, Kyle Sia
 */

public class Date implements Comparable<Date>{
    private int year; //year
    private int month; //month
    private int day; //day
    //constants given for leap years
    public static final int STARTING = 0;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int TWOTHOUSANDONE = 2021;
    //constants for months
    public static final int FEBRUARY = 2;
    public static final int APRIL = 4;
    public static final int JUNE = 6;
    public static final int SEPTEMBER = 9;
    public static final int NOVEMBER = 11;

    //Variables to test if day is valid
    public static final int longerMonth = 31;
    public static final int shorterMonth = 30;
    public static final int nonLeapYear = 28;
    public static final int leapYear = 29;

    public static boolean isLeapYear = false;

    /**
     * creates a date object given a string
     * @param date in the string format of mm/dd/yyyy
     */
    public Date(String date){
        StringTokenizer album_date = new StringTokenizer(date);
        if(date.substring(0,2).equals("--")){
            month = STARTING;
            day = STARTING;
            year = STARTING;
        }
        else {
            month = Integer.parseInt(album_date.nextToken("/"));
            day = Integer.parseInt(album_date.nextToken("/"));
            year = Integer.parseInt(album_date.nextToken());
        }
    }

    /**
     * //create an object with today’s date (see Calendar class)
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DATE);
    }

    /**
     * Checks to see if the Date object is a valid date
     * Date is valid if the year is in 2021 but before todays date
     * Date verification also checks if it is a leap year or not
     * @return true if the date is valid, false if the date is invalid
     */
    public boolean isValid() {
        this.isLeapYear = false;//resets boolean flag for leap year
        Calendar curr_date = Calendar.getInstance();
        int cMonth = curr_date.get(Calendar.MONTH) + 1;

        if(this.year < TWOTHOUSANDONE)
            return false;

        //checking to see if date comes before our current date
        if(this.year == curr_date.get(Calendar.YEAR)){ //same year
            if(this.month > cMonth){ //month comes after current month
                return false;
            }
            else if(this.month == cMonth){ //same month
                if(this.day > curr_date.get(Calendar.DATE)){//day comes after current day
                    return false;
                }
            }
        }

        //checks to see if the dates are the same
        if(this.year == curr_date.get(Calendar.YEAR)){
            if(this.month == cMonth){
                if(this.day == curr_date.get(Calendar.DATE)){
                    return true;
                }
            }
        }

        if(this.month == FEBRUARY){
            if (this.year % QUADRENNIAL == 0){
                if(this.year % CENTENNIAL == 0){
                    if(this.year % QUATERCENTENNIAL == 0){

                        this.isLeapYear = true;
                    }
                }
                else{
                    this.isLeapYear = true;
                }
            }
            if(this.isLeapYear && this.day > leapYear){
                return false;
            }
            else if((!this.isLeapYear) && this.day > nonLeapYear){
                return false;
            }
        }
        else if((this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER || this.month == NOVEMBER)
                && this.day > shorterMonth){
            return false;
        }
        else if(this.day > longerMonth){
            return false;
        }

        return true;
    }

    //Access Methods

    /**
     * Returns the Date object's year
     * @return int that symbolizes the year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Returns the Date object's month
     * @return int that symbolizes the month
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Returns the Date object's day
     * @return int that symbolizes the day
     */
    public int getDay(){
        return this.day;
    }


    /**
     * Compares two dates to see which one comes first
     * @param date that holds the year, month, and day
     * @return 0 if both dates are the same, -1 if the object being invoked comes before
     * the object being passed in, 1 if the object being invoked comes after the object
     * being passed in.
     */
    @Override
    public int compareTo(Date date) {
        if(date.getYear() < this.year ){
            return 1;
        }
        else if(date.getYear() > this.year){
            return -1;
        }
        else{
            if(date.getMonth() < this.month ){
                return 1;
            }
            else if(date.getMonth() > this.month) {
                return -1;
            }
            else{
                if(date.getDay() < this.day ){
                    return 1;
                }
                else if(date.getDay() > this.day) {
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }

    }

    /**
     * ToString method to print out date
     * @return String of date in mm/dd/yyyy format
     */
    public String toString(){
        if(this.month == STARTING || this.day == STARTING || this.year == STARTING)
        {
            return "--/--/--";
        }
        else
            return this.month + "/" + this.day + "/" + this.year;
    }

}
