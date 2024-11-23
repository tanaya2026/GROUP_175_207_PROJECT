package interface_adapter.find_potential_matches;

import entity.User;
import use_case.find_potential_matches.FindPotentialMatchesInputBoundary;
import use_case.find_potential_matches.FindPotentialMatchesInputData;

/**
 * The controller for the Find Potential Matches Use Case.
 */
public class FindPotentialMatchesController {

    private final FindPotentialMatchesInputBoundary findPotentialMatchesUseCaseInteractor;

    public FindPotentialMatchesController(FindPotentialMatchesInputBoundary findPotentialMatchesUseCaseInteractor) {
        this.findPotentialMatchesUseCaseInteractor = findPotentialMatchesUseCaseInteractor;
    }

    /**
     * Executes the Find Potential Matches Use Case.
     * @param user The user seeking matches.
     * @param expand A boolean: false = match by courses, true = match by program.
     */
    public void execute(User user, boolean expand) {
        final FindPotentialMatchesInputData findPotentialMatchesInputData = new FindPotentialMatchesInputData(
                user, expand);

        findPotentialMatchesUseCaseInteractor.execute(findPotentialMatchesInputData);
    }
}
