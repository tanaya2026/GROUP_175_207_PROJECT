package use_case.view_profile;


import use_case.home_page.HomepageOutputData;

/**
 * The output boundary for the ViewProfile Use Case.
 */

public interface ViewProfileOutputBoundry {
    /**
     * Prepares the success view for the HomePage Use Case.
     * @param viewprofileoutputdata the output data.
     */
    void prepareSuccessView(ViewprofileOutputData viewprofileoutputdata);
}
