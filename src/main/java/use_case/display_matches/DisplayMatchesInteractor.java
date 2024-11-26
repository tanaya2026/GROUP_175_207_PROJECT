package use_case.display_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * The Display Matches Interactor.
 */
public class DisplayMatchesInteractor implements DisplayMatchesInputBoundary {
    private final DisplayMatchesDataAccessInterface dataAccessObject;
    private final DisplayMatchesOutputBoundary presenter;

    public DisplayMatchesInteractor(
            DisplayMatchesDataAccessInterface displayMatchesDataAccessInterface,
            DisplayMatchesOutputBoundary displayMatchesOutputBoundary) {
        this.dataAccessObject = displayMatchesDataAccessInterface;
        this.presenter = displayMatchesOutputBoundary;
    }

    @Override
    public void execute(DisplayMatchesInputData displayMatchesInputData) {
        Map<User, List<Timeslot>> matches = dataAccessObject.findMatches(
                displayMatchesInputData.getUser(),
                displayMatchesInputData.getExpand());

        final DisplayMatchesOutputData displayMatchesOutputData = new DisplayMatchesOutputData(matches, false);
        presenter.prepareSuccessView(displayMatchesOutputData);
    }
}
