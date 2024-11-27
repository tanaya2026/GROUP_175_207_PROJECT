package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_matches.DisplayMatchesState;
import interface_adapter.display_matches.DisplayMatchesViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.home_page.HomepageOutputBoundary;
import use_case.home_page.HomepageOutputData;
import use_case.signup.SignupOutputData;


/**
 * The Presenter for the HomePage Use Case.
 */
public class HomePagePresenter implements HomepageOutputBoundary {

    private final HomePageViewModel homePageViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomePagePresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           HomePageViewModel homePageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.homePageViewModel = homePageViewModel;

    }

    @Override
    public void prepareSuccessView(HomepageOutputData response) {
        // On success, switch to SignUpView

        final SignupState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        viewManagerModel.setState(SignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
