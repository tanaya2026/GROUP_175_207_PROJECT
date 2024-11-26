package interface_adapter.display_matches;

import entity.User;
import use_case.display_matches.DisplayMatchesInputBoundary;
import use_case.display_matches.DisplayMatchesInputData;

/**
 * The controller for the Display Matches Use Case.
 */
public class DisplayMatchesController {

    private final DisplayMatchesInputBoundary displayMatchesUseCaseInteractor;

    public DisplayMatchesController(DisplayMatchesInputBoundary displayMatchesUseCaseInteractor) {
        this.displayMatchesUseCaseInteractor = displayMatchesUseCaseInteractor;
    }

    /**
     * Executes the Display Matches Use Case.
     * @param user The user seeking matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     */
    public void execute(User user, boolean expand) {
        final DisplayMatchesInputData displayMatchesInputData = new DisplayMatchesInputData(
                user, expand);

        displayMatchesUseCaseInteractor.execute(displayMatchesInputData);
    }
}
