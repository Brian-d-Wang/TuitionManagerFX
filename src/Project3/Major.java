package Project3;


/**
 * Major enums that holds the list of majors possible
 * @author Brian Wang, Kyle Sia
 */
public enum Major {
    /**
     * Methods that returns the corresponding majors in string form
     */
    CS {
        public String toString() {
            return "CS";
        }
    },
    IT {
        public String toString() {
            return "IT";
        }
    },
    BA {
        public String toString() {
            return "BA";
        }
    },
    EE {
        public String toString() {
            return "EE";
        }
    },
    ME {
        public String toString() {
            return "ME";
        }
    }
}
