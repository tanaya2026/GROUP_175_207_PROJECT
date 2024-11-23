package interface_adapter.find_potential_matches;

/**
 * The state for the Find Potential Matches View Model.
 */
public class FindPotentialMatchesState {
    private String username = "";
    private String findPotentialMatchesError;
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getFindPotentialMatchesError() {
        return findPotentialMatchesError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFindPotentialMatchesError(String usernameError) {
        this.findPotentialMatchesError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
