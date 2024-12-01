package use_case.view_profile;


import use_case.home_page.HomepageInputData;

/**
 * Input Boundary for actions which are related to view profile.
 */

public interface ViewProfileInputBoundary {
    /**
     * Executes the viewprofile use case.
     * @param viewProfileInputData the viewprofile data
     */
    void execute(ViewProfileInputData viewProfileInputData);
    ;
}
