package use_case.find_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * Output Data for the Find Matches Use Case.
 */
public class FindMatchesOutputData {

    private final Map<User, List<Timeslot>> matches;

    private final boolean useCaseFailed;

    public FindMatchesOutputData(Map<User, List<Timeslot>> matches, boolean useCaseFailed) {
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
