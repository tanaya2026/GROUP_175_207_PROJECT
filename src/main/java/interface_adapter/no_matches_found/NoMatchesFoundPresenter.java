package interface_adapter.no_matches_found;

import use_case.no_matches_found.NoMatchesFoundOutputBoundary;
import use_case.no_matches_found.NoMatchesFoundOutputData;

public class NoMatchesFoundPresenter implements NoMatchesFoundOutputBoundary {
    private NoMatchesFoundState state;

    public NoMatchesFoundPresenter(NoMatchesFoundState state) {
        this.state = state;
    }

    @Override
    public void presentMatches(NoMatchesFoundOutputData outputData) {
        // Update the state with the output data
        state.setMatches(outputData.getMatches());
        state.setMessage(outputData.getMessage());
    }
}
