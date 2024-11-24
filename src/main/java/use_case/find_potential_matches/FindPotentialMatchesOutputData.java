package use_case.find_potential_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * Output Data for the Find Potential Matches Use Case.
 */
public class FindPotentialMatchesOutputData {

    private final Map<User, List<Timeslot>> potentialMatches;

    private final boolean useCaseFailed;

    public FindPotentialMatchesOutputData(Map<User, List<Timeslot>> potentialMatches, boolean useCaseFailed) {
        this.potentialMatches = potentialMatches;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
