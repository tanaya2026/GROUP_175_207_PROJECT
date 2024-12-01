package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {
    public static final String TITLE_LABEL = "Login Page";
    public static final String USERNAME_LABEL = "Insert username";
    public static final String PASSWORD_LABEL = "Insert password";
    public static final String LOGIN_TITLE_BUTTON_LABEL = "Login";

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

}
