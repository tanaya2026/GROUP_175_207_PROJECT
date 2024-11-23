package interface_adapter.find_potential_matches;

import interface_adapter.ViewModel;

/**
 * The View Model for the Find Potential Matches View.
 */
public class FindPotentialMatchesViewModel extends ViewModel<FindPotentialMatchesState> {

    public FindPotentialMatchesViewModel() {
        super("find potential matches");
        setState(new FindPotentialMatchesState());
    }

}
