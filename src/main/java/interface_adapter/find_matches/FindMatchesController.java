package interface_adapter.find_matches;

import entity.User;
import use_case.find_matches.FindMatchesInputBoundary;
import use_case.find_matches.FindMatchesInputData;

/**
 * The controller for the Find Matches Use Case.
 */
public class FindMatchesController {

    private final FindMatchesInputBoundary findMatchesUseCaseInteractor;

    public FindMatchesController(FindMatchesInputBoundary findMatchesUseCaseInteractor) {
        this.findMatchesUseCaseInteractor = findMatchesUseCaseInteractor;
    }

    /**
     * Executes the Find Matches Use Case.
     * @param user The user seeking matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     */
    public void execute(User user, boolean expand) {
        final FindMatchesInputData findMatchesInputData = new FindMatchesInputData(
                user, expand);

        findMatchesUseCaseInteractor.execute(findMatchesInputData);
    }
}
