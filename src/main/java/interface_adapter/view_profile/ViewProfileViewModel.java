package interface_adapter.view_profile;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

/**
 * The ViewModel for the ViewProfile View.
 */

public class ViewProfileViewModel extends ViewModel {

    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String TITLE_LABEL = "ViewProfile View";


    public ViewProfileViewModel() {
        super("viewProfile");
        setState(new SignupState());
    }

}
