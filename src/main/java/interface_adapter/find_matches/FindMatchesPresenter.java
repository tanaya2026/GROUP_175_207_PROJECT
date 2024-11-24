package interface_adapter.find_matches;

import interface_adapter.ViewManagerModel;
import use_case.find_matches.FindMatchesOutputBoundary;
import use_case.find_matches.FindMatchesOutputData;

/**
 * The Presenter for the Find Matches Use Case.
 */
public class FindMatchesPresenter implements FindMatchesOutputBoundary {

    private final FindMatchesViewModel findMatchesViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public FindMatchesPresenter(ViewManagerModel viewManagerModel,
                                LoggedInViewModel loggedInViewModel,
                                FindMatchesViewModel findMatchesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.findMatchesViewModel = findMatchesViewModel;
    }

    @Override
    public void prepareSuccessView(FindMatchesOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final FindMatchesState findMatchesState = findMatchesViewModel.getState();
        findMatchesState.setFindMatchesError(error);
        findMatchesViewModel.firePropertyChanged();
    }
}
