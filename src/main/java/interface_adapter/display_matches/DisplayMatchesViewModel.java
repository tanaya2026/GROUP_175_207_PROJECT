package interface_adapter.display_matches;

import interface_adapter.ViewModel;

/**
 * The View Model for the Display Matches View.
 */
public class DisplayMatchesViewModel extends ViewModel<DisplayMatchesState> {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public DisplayMatchesViewModel() {
        super("display matches");
        setState(new DisplayMatchesState());
    }

}
