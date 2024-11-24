package use_case.find_matches;

import entity.Timeslot;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * The Find Matches Interactor.
 */
public class FindMatchesInteractor implements FindMatchesInputBoundary {
    private final FindMatchesDataAccessInterface userDataAccessObject;
    private final FindMatchesOutputBoundary userPresenter;

    public FindMatchesInteractor(
            FindMatchesDataAccessInterface findMatchesDataAccessInterface,
            FindMatchesOutputBoundary findMatchesOutputBoundary) {
        this.userDataAccessObject = findMatchesDataAccessInterface;
        this.userPresenter = findMatchesOutputBoundary;
    }

    @Override
    public void execute(FindMatchesInputData findMatchesInputData) {
        Map<User, List<Timeslot>> matches = userDataAccessObject.findMatches(
                findMatchesInputData.getUser(),
                findMatchesInputData.getExpand());

        final FindMatchesOutputData findMatchesOutputData = new FindMatchesOutputData(matches, false);
        userPresenter.prepareSuccessView(findMatchesOutputData);
    }
}
