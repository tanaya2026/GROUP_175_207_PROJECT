package use_case.find_potential_matches;

import entity.User;
import entity.UserFactory;

/**
 * The Find Potential Matches Interactor.
 */
public class FindPotentialMatchesInteractor implements FindPotentialMatchesInputBoundary {
    private final FindPotentialMatchesDataAccessInterface userDataAccessObject;
    private final FindPotentialMatchesOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public FindPotentialMatchesInteractor(
            FindPotentialMatchesDataAccessInterface findPotentialMatchesDataAccessInterface,
            FindPotentialMatchesOutputBoundary findPotentialMatchesOutputBoundary,
            UserFactory userFactory) {
        this.userDataAccessObject = findPotentialMatchesDataAccessInterface;
        this.userPresenter = findPotentialMatchesOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(FindPotentialMatchesInputData findPotentialMatchesInputData) {
        final User user = userFactory.create(
                findPotentialMatchesInputData.getUsername(),
                findPotentialMatchesInputData.getPassword());
        userDataAccessObject.findMatches(user);

        final FindPotentialMatchesOutputData findPotentialMatchesOutputData =
                new FindPotentialMatchesOutputData(user.getName(), false);
        userPresenter.prepareSuccessView(findPotentialMatchesOutputData);
    }
}
