package use_case.find_potential_matches;

/**
 * Output Data for the Find Potential Matches Use Case.
 */
public class FindPotentialMatchesOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public FindPotentialMatchesOutputData(String username, boolean useCaseFailed) {
        // this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
