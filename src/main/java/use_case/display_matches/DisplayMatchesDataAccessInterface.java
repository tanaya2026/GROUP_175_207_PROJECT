package use_case.display_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * The interface of the DAO for the Display Matches Use Case.
 */
public interface DisplayMatchesDataAccessInterface {
    /**
     * Returns the Users who are matches and the Timeslots for which they share availability.
     * Matches are determined by shared availability and either courses or program
     * @param user The User who is seeking study buddy matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     * @return a Map of the Users who are matches and a list of Timeslots for which they share availability.
     */
    Map<User, List<Timeslot>> findMatches(User user, boolean expand);
}
