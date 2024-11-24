package interface_adapter.find_matches;

/**
 * The state for the Find Matches View Model.
 */
public class FindMatchesState {
    private String username = "";
    private String findMatchesError;
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getFindMatchesError() {
        return findMatchesError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFindMatchesError(String usernameError) {
        this.findMatchesError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
