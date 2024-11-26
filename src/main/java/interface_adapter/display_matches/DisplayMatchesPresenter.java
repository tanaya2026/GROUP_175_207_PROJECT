package interface_adapter.display_matches;

import use_case.display_matches.DisplayMatchesOutputBoundary;
import use_case.display_matches.DisplayMatchesOutputData;

/**
 * The Presenter for the Display Matches Use Case.
 */
public class DisplayMatchesPresenter implements DisplayMatchesOutputBoundary {

    private final DisplayMatchesViewModel displayMatchesViewModel;

    public DisplayMatchesPresenter(DisplayMatchesViewModel displayMatchesViewModel) {
        this.displayMatchesViewModel = displayMatchesViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayMatchesOutputData response) {
        // Nothing is to be changed based on the output data
        displayMatchesViewModel.firePropertyChanged("matches");
    }

    @Override
    public void prepareFailView(String error) {
        // This use case cannot fail - if no matches are found, the NoMatchesFoundView is displayed instead.
    }
}
