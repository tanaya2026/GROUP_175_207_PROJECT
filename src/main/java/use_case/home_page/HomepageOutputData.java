package use_case.home_page;

/**
 * The output boundary for the HomePage Use Case.
 */
public interface HomepageOutputData {
    /**
     * Prepares the success view for the HomePage Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(HomepageOutputData outputData);
}
