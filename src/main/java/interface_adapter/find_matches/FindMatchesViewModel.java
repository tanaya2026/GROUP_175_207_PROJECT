package interface_adapter.find_matches;

import interface_adapter.ViewModel;

/**
 * The View Model for the Find Matches View.
 */
public class FindMatchesViewModel extends ViewModel<FindMatchesState> {

    public FindMatchesViewModel() {
        super("find matches");
        setState(new FindMatchesState());
    }

}
