package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String username = "";
    private String password = "";
    private String name = "";
    private String email = "";
    private String courses = "";
    private String program = "";
    private String bio = "";
    private String avaliability = "";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getCourses() {
        return courses;
    }
    public String getProgram() {
        return program;
    }
    public String getBio() {
        return bio;
    }
    public String getAvaliability() {
        return avaliability;
    }

    // write all the set methods!

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

//    @Override
//    public String toString() {
//        return "SignupState{"
//                + "username='" + username + '\''
//                + ", password='" + password + '\''
//                + ", repeatPassword='" + repeatPassword + '\''
                // + '}';

}
