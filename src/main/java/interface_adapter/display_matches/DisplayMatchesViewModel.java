package interface_adapter.display_matches;

import interface_adapter.ViewModel;

/**
 * The View Model for the Display Matches View.
 */
public class DisplayMatchesViewModel extends ViewModel<DisplayMatchesState> {

    public DisplayMatchesViewModel() {
        super("display matches");
        setState(new DisplayMatchesState());
    }

}
