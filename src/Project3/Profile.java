package Project3;



/**
 * Profile class that holds a student's name and major
 * @author Brian Wang, Kyle Sia
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Creates a profile object of a student
     * @param name is the name of the student
     * @param major is the major of the student
     */
    public Profile(String name,Major major){
        this.name = name;
        this.major = major;
    }

    /**
     * Returns the student objects name value
     * @return the Student's name
     */
    public String getName(){return this.name;}

    /**
     * Returns the student objects major value
     * @return the student's major
     */
    public Major getMajor(){return this.major;}

    /**
     * Compares to see if two objects are equal
     * @param obj to be compared
     * @return true if the name and major of two students match and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profile){
            Profile comparison = (Profile) obj;
            return comparison.getName().equals(this.name) && comparison.getMajor().equals(this.major);
        }
        return false;

    }

    /**
     * toString method that prints out profile values
     * @return String in the format of "name:major"
     */
    @Override
    public String toString() {
        return this.name + ":" + this.major;
    }


}
