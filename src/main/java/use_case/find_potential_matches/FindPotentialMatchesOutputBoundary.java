package use_case.find_potential_matches;

/**
 * The output boundary for the Find Potential Matches Use Case.
 */
public interface FindPotentialMatchesOutputBoundary {
    /**
     * Prepares the success view for the Find Potential Matches Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FindPotentialMatchesOutputData outputData);

    /**
     * Prepares the failure view for the Find Potential Matches Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
