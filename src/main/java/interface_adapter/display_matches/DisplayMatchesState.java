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

}
