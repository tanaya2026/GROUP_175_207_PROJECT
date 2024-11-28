package use_case.display_matches;

import java.util.List;
import java.util.Map;

import entity.Timeslot;
import entity.User;

/**
 * Output Data for the Display Matches Use Case.
 */
public class DisplayMatchesOutputData {

    private final Map<User, List<Timeslot>> matches;

    private final boolean useCaseFailed;

    public DisplayMatchesOutputData(Map<User, List<Timeslot>> matches, boolean useCaseFailed) {
        this.matches = matches;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns a map of the current user's matches and the timeslots for which they share availability.
     * @return this User, List[Timeslot] Map.
     */
    public Map<User, List<Timeslot>> getMatches() {
        return matches;
    }

    /**
     * Returns the boolean indicating if this use case failed.
     * Note that this use case cannot fail, and will instead direct to the NoMatchesView if no matches are found.
     * @return this boolean.
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
