package use_case.find_potential_matches;

import entity.Timeslot;
import entity.User;
import entity.UserFactory;

import java.util.List;
import java.util.Map;

/**
 * The Find Potential Matches Interactor.
 */
public class FindPotentialMatchesInteractor implements FindPotentialMatchesInputBoundary {
    private final FindPotentialMatchesDataAccessInterface userDataAccessObject;
    private final FindPotentialMatchesOutputBoundary userPresenter;

    public FindPotentialMatchesInteractor(
            FindPotentialMatchesDataAccessInterface findPotentialMatchesDataAccessInterface,
            FindPotentialMatchesOutputBoundary findPotentialMatchesOutputBoundary) {
        this.userDataAccessObject = findPotentialMatchesDataAccessInterface;
        this.userPresenter = findPotentialMatchesOutputBoundary;
    }

    @Override
    public void execute(FindPotentialMatchesInputData findPotentialMatchesInputData) {
        Map<User, List<Timeslot>> potentialMatches = userDataAccessObject.findMatches(
                findPotentialMatchesInputData.getUser(),
                findPotentialMatchesInputData.getExpand());

        final FindPotentialMatchesOutputData findPotentialMatchesOutputData =
                new FindPotentialMatchesOutputData(potentialMatches, false);
        userPresenter.prepareSuccessView(findPotentialMatchesOutputData);
    }
}
