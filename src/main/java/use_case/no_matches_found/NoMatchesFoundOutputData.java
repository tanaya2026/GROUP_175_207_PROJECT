package use_case.no_matches_found;

import entity.User;
import java.util.List;
import java.util.Map;

public class NoMatchesFoundOutputData {
    private final Map<User, List<String>> matches;
    private final String message;

    public NoMatchesFoundOutputData(Map<User, List<String>> matches, String message) {
        this.matches = matches;
        this.message = message;
    }

    public Map<User, List<String>> getMatches() {
        return matches;
    }

    public String getMessage() {
        return message;
    }
}
