package interface_adapter.no_matches_found;

import entity.User;
import java.util.List;
import java.util.Map;

public class NoMatchesFoundState {
    private Map<User, List<String>> matches; // Match users and their available times
    private String message;

    public Map<User, List<String>> getMatches() {
        return matches;
    }

    public void setMatches(Map<User, List<String>> matches) {
        this.matches = matches;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
