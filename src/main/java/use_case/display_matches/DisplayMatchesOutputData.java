package use_case.display_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

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

    public Map<User, List<Timeslot>> getMatches() {
        return matches;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
