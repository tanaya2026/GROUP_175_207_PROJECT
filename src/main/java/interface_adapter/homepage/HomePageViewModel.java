package interface_adapter.homepage;

import interface_adapter.ViewModel;

/**
 * The View Model for HomePage View.
 */
public class HomePageViewModel extends ViewModel<HomePageViewState> {

    public static final String TITLE_LABEL = "StudyBuddyFinder App";
    public static final String USERNAME_LABEL = "Insert username";
    public static final String PASSWORD_LABEL = "Insert password";
    public static final String APP_LABEL =
            "Hello! Welcome to StudyBuddyFinder! "
                    + "Our application aims to pair students with study partners, "
                    + "who have similar courses, programs, and availability to "
                    + "enhance academic performance of our users.\n";

    public static final String SIGNUP_BUTTON_LABEL = "Create an Account";
    public static final String LOGIN_TITLE_BUTTON_LABEL = "Login";

    public HomePageViewModel() {
        super("home page");
        setState(new HomePageViewState());
    }

}
