package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;

    public SignupOutputData(String username) {
        this.username = username;
    }

    /**
     * Getter function for SignUpData.
     *
     * @return username the Username.
     */

    public String getUsername() {
        return username;
    }
}
