package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_matches.DisplayMatchesState;
import interface_adapter.display_matches.DisplayMatchesViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final DisplayMatchesViewModel displayMatchesViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          DisplayMatchesViewModel displayMatchesViewModel,
                          LoginViewModel loginViewModel) {
        this.displayMatchesViewModel = displayMatchesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final DisplayMatchesState displayMatchesState = displayMatchesViewModel.getState();
        displayMatchesState.setUsername(response.getUsername());
        this.displayMatchesViewModel.setState(displayMatchesState);
        this.displayMatchesViewModel.firePropertyChanged();

        this.viewManagerModel.setState(displayMatchesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
