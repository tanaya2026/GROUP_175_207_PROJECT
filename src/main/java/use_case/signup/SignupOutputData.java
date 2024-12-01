package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Getter function for SignUpData.
     * @return username the Username.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Getter function for SignUpData.
     * @return password the password.
     */

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
