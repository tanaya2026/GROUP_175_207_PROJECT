package interface_adapter.view_profile;


import interface_adapter.ViewManagerModel;
import interface_adapter.display_matches.DisplayMatchesState;
import interface_adapter.display_matches.DisplayMatchesViewModel;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.home_page.HomepageOutputBoundary;
import use_case.home_page.HomepageOutputData;
import use_case.view_profile.ViewProfileOutputBoundry;
import use_case.view_profile.ViewprofileOutputData;

/**
 * The Presenter for the View Profile Use Case.
 */

public class ViewProfilePresenter implements ViewProfileOutputBoundry {

    private final ViewProfileViewModel viewProfileViewModel;
    private final DisplayMatchesViewModel displayMatchesViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewProfilePresenter(ViewManagerModel viewManagerModel,
                             DisplayMatchesViewModel displayMatchesViewModel,
                             ViewProfileViewModel viewProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayMatchesViewModel = displayMatchesViewModel;
        this.viewProfileViewModel = viewProfileViewModel;

    }

    @Override
    public void prepareSuccessView(ViewprofileOutputData response) {
        // On success, switch to DisplayMatchesView

        final DisplayMatchesState displayMatchesState = displayMatchesViewModel.getState();
        this.displayMatchesViewModel.setState(displayMatchesState);
        displayMatchesViewModel.firePropertyChanged();

        viewManagerModel.setState(DisplayMatchesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
