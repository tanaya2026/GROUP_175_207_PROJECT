package interface_adapter.find_potential_matches;

import interface_adapter.ViewManagerModel;
import interface_adapter.find_potential_matches.FindPotentialMatchesState;
import interface_adapter.find_potential_matches.FindPotentialMatchesViewModel;
import use_case.find_potential_matches.FindPotentialMatchesOutputBoundary;
import use_case.find_potential_matches.FindPotentialMatchesOutputData;

/**
 * The Presenter for the Find Potential Matches Use Case.
 */
public class FindPotentialMatchesPresenter implements FindPotentialMatchesOutputBoundary {

    private final FindPotentialMatchesViewModel findPotentialMatchesViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public FindPotentialMatchesPresenter(ViewManagerModel viewManagerModel,
                                         LoggedInViewModel loggedInViewModel,
                                         FindPotentialMatchesViewModel findPotentialMatchesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.findPotentialMatchesViewModel = findPotentialMatchesViewModel;
    }

    @Override
    public void prepareSuccessView(FindPotentialMatchesOutputData response) {
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
        final FindPotentialMatchesState findPotentialMatchesState = findPotentialMatchesViewModel.getState();
        findPotentialMatchesState.setFindPotentialMatchesError(error);
        findPotentialMatchesViewModel.firePropertyChanged();
    }
}
