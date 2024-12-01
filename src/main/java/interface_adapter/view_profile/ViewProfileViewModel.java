package interface_adapter.view_profile;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the ViewProfile View.
 */

public class ViewProfileViewModel extends ViewModel<ViewProfileState> {

    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String TITLE_LABEL = "ViewProfile View";
    public static final String VIEW_PROFILE_LABEL = "ViewProfile";

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public ViewProfileViewModel() {
        super("viewProfile");
        setState(new ViewProfileState());
    }

}
