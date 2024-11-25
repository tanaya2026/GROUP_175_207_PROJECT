package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String EMAIL_LABEL = "Email";
    public static final String NAME_LABEL = "Name";
    public static final String COURSES_LABEL = "Courses (comma-separated):";
    public static final String PROGRAM_LABEL = "Program";
    public static final String BIO_LABEL = "Bio";
    public static final String AVAIL_LABEL = "Bio";

    public static final String SIGNUP_BUTTON_LABEL = "Create Account";

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }

}
