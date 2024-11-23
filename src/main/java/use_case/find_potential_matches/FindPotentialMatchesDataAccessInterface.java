package use_case.find_potential_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * The interface of the DAO for the Find Potential Matches Use Case.
 */
public interface FindPotentialMatchesDataAccessInterface {

    /**
     * Returns the Users who are potential matches and the Timeslots for which they share availability.
     * Potential matches are determined by shared availability and courses.
     * @param user The User who is seeking study buddy matches.
     * @return a Map of the Users who are potential matches and a list of Timeslots for which they share availability.
     */
    Map<User, List<Timeslot>> findMatches(User user);

    /**
     * Returns the Users who are potential matches and the Timeslots for which they share availability.
     * Potential matches are determined by shared availability and either courses or program
     * @param user The User who is seeking study buddy matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     * @return a Map of the Users who are potential matches and a list of Timeslots for which they share availability.
     */
    Map<User, List<Timeslot>> findMatches(User user, boolean expand);
}
