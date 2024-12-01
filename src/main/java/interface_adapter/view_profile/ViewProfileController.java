package interface_adapter.view_profile;

import use_case.view_profile.ViewProfileInputBoundary;
import use_case.view_profile.ViewProfileInputData;

/**
 * Controller for the ViewProfile Use Case.
 */

public class ViewProfileController {

    private final ViewProfileInputBoundary viewProfileUseCaseInteractor;

    public ViewProfileController(ViewProfileInputBoundary viewProfileUseCaseInteractor) {
        this.viewProfileUseCaseInteractor = viewProfileUseCaseInteractor;
    }

    /**
     * Executes the ViewProfile Use Case.
     */
    public void execute() {
        final ViewProfileInputData viewProfileInputData = new ViewProfileInputData();

        viewProfileUseCaseInteractor.execute(viewProfileInputData);
    }

}

