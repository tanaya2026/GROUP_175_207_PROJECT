package interface_adapter.homepage;



import use_case.home_page.HomepageInputBoundary;
import use_case.home_page.HomepageInputData;
import use_case.signup.SignupInputData;


/**
 * Controller for the HomePage Use Case.
 */

public class HomePageController {
    private final HomepageInputBoundary homePageUseCaseInteractor;

    public HomePageController(HomepageInputBoundary homePageUseCaseInteractor) {
        this.homePageUseCaseInteractor = homePageUseCaseInteractor;
    }

    /**
     * Executes the HomePage Use Case.
     */
    public void execute() {
        final HomepageInputData homepageInputData = new HomepageInputData();

        homePageUseCaseInteractor.execute(homepageInputData);
    }
}
