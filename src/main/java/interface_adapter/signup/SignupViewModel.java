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
    public static final String AVAIL_LABEL = "Availability";

    public static final String SIGNUP_BUTTON_LABEL = "Create Account";
    public static final int AVAIL_ROWS = 7;
    public static final int AVAIL_COLS = 4;

    public static final int CHECK_BOX_COLS = 8;

    public static final int TIME_9 = 9;
    public static final int TIME_10 = 10;
    public static final int TIME_11 = 11;
    public static final int TIME_12 = 12;
    public static final int TIME_13 = 13;
    public static final int TIME_14 = 14;
    public static final int TIME_15 = 15;
    public static final int TIME_16 = 16;

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }

}
