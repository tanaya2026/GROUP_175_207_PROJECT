package use_case.display_matches;

/**
 * The output boundary for the Display Matches Use Case.
 */
public interface DisplayMatchesOutputBoundary {
    /**
     * Prepares the success view for the Display Matches Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DisplayMatchesOutputData outputData);

    /**
     * Prepares the failure view for the Display Matches Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
