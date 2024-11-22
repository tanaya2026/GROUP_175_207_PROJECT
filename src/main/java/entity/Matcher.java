package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is a utility class which performs the study buddy matching algorithm.
 */
public final class Matcher {

    private Matcher() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Returns the Users who are potential matches and the Timeslots for which they share availability.
     * Potential matches are determined by shared availability and courses.
     * @param user The User who is seeking study buddy matches.
     * @return a Map of the Users who are potential matches and a list of Timeslots for which they share availability.
     */
    public static Map<User, List<Timeslot>> findMatches(User user) {
        return findMatches(user, false);
    }

    /**
     * Returns the Users who are potential matches and the Timeslots for which they share availability.
     * Potential matches are determined by shared availability and either courses or program
     * @param user The User who is seeking study buddy matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     * @return a Map of the Users who are potential matches and a list of Timeslots for which they share availability.
     */
    public static Map<User, List<Timeslot>> findMatches(User user, boolean expand) {
        return null;
    }

}
