package use_case.no_matches_found;

import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoMatchesFoundInteractor implements NoMatchesFoundInputBoundary {
    private final NoMatchesFoundDataAccessInterface dataAccess;
    private final NoMatchesFoundOutputBoundary presenter;

    public NoMatchesFoundInteractor(NoMatchesFoundDataAccessInterface dataAccess, NoMatchesFoundOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void findMatches(NoMatchesFoundInputData inputData) {
        String program = inputData.getProgram();
        Map<User, String> users = dataAccess.findUsersByProgram(program);

        if (users.isEmpty()) {
            presenter.presentMatches(new NoMatchesFoundOutputData(new HashMap<>(), "No matches found for your program."));
        } else {
            // Convert the data into the required output format
            Map<User, List<String>> matches = new HashMap<>();
            for (Map.Entry<User, String> entry : users.entrySet()) {
                matches.put(entry.getKey(), List.of(entry.getValue()));
            }
            presenter.presentMatches(new NoMatchesFoundOutputData(matches, "Matches found!"));
        }
    }
}
