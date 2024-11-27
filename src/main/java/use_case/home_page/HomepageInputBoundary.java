package use_case.home_page;

import use_case.home_page.HomepageInputData;

/**
 * Input Boundary for actions which are related to home page.
 */

public interface HomepageInputBoundary {
    /**
     * Executes the homepage use case.
     * @param homepageInputData the home page input data
     */
    void execute(HomepageInputData homepageInputData)
    ;

}
