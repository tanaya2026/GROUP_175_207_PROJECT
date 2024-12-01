package interface_adapter.no_matches_found;

import entity.User;
import use_case.no_matches_found.NoMatchesFoundInputBoundary;
import use_case.no_matches_found.NoMatchesFoundInputData;

public class NoMatchesFoundController {
    private final NoMatchesFoundInputBoundary interactor;

    public NoMatchesFoundController(NoMatchesFoundInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleMatchBasedOnProgramAction(User currentUser) {
        // Create input data and delegate to the interactor
        NoMatchesFoundInputData inputData = new NoMatchesFoundInputData(currentUser.getUsername(),
                currentUser.getProgram());
        interactor.findMatches(inputData);
    }
}
