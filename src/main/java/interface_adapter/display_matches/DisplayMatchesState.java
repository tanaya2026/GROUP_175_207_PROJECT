package interface_adapter.display_matches;

/**
 * The state for the Display Matches View Model.
 */
public class DisplayMatchesState {
    private String username = "";
    private String password = "";

    public DisplayMatchesState(DisplayMatchesState copy) {
        username = copy.username;
        password = copy.password;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public DisplayMatchesState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
