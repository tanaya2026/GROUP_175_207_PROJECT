package use_case.find_matches;

/**
 * The output boundary for the Find Matches Use Case.
 */
public interface FindMatchesOutputBoundary {
    /**
     * Prepares the success view for the Find Matches Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FindMatchesOutputData outputData);

    /**
     * Prepares the failure view for the Find Matches Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
