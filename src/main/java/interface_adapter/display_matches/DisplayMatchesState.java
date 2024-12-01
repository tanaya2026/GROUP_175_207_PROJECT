package interface_adapter.display_matches;

import java.util.List;
import java.util.Map;

import entity.Timeslot;
import entity.User;

/**
 * The state for the Display Matches View Model.
 */
public class DisplayMatchesState {
    private String username = "";
    private String password = "";
    private Map<User, List<Timeslot>> matches;

    public DisplayMatchesState(DisplayMatchesState copy) {
        username = copy.username;
        password = copy.password;
        matches = copy.matches;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public DisplayMatchesState() {

    }

    /**
     * Returns the current user's username.
     * @return this username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the current user's username.
     * @param username this username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the current user's password.
     * @param password this password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the current user's password.
     * @return this password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the matches of the user.
     * @return the Map of the Users who are matches and a list of Timeslots for which they share availability.
     */
    public Map<User, List<Timeslot>> getMatches() {
        return matches;
    }

    /**
     * Sets the matches of the user.
     * @param matches the Map of the Users who are matches and a list of Timeslots for which they share availability.
     */
    public void setMatches(Map<User, List<Timeslot>> matches) {
        this.matches = matches;
    }

}
