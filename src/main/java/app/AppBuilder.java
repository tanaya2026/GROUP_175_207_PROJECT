package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_matches.DisplayMatchesController;
import interface_adapter.display_matches.DisplayMatchesPresenter;
import interface_adapter.display_matches.DisplayMatchesViewModel;
import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfilePresenter;
import interface_adapter.edit_profile.EditProfileViewModel;
import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePagePresenter;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_profile.ViewProfileController;
import interface_adapter.view_profile.ViewProfilePresenter;
import interface_adapter.view_profile.ViewProfileViewModel;
import use_case.display_matches.DisplayMatchesInputBoundary;
import use_case.display_matches.DisplayMatchesInteractor;
import use_case.display_matches.DisplayMatchesOutputBoundary;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.home_page.HomepageInputBoundary;
import use_case.home_page.HomepageInteractor;
import use_case.home_page.HomepageOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.view_profile.ViewProfileInputBoundary;
import use_case.view_profile.ViewProfileInteractor;
import use_case.view_profile.ViewProfileOutputBoundry;
import view.*;
import view.HomePageView;
import view.LoginView;
import view.SignupView;

/**
 * The AppBuilder class is responsible for putting together the pieces of CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DataAccessObject dataAccessObject = new DataAccessObject();

    private EditProfileView editProfileView;
    private EditProfileViewModel editProfileViewModel;
    private DisplayMatchesView displayMatchesView;
    private DisplayMatchesViewModel displayMatchesViewModel;
    private HomePageViewModel homePageViewModel;
    private HomePageView homePageView;
    private SignupViewModel signupViewModel;
    private SignupView signupView;
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private ViewProfileViewModel viewProfileViewModel;
    private ViewProfileView viewprofileview;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }


    /**
     * Adds the HomePageView to the application.
     * @return this builder
     */
    public AppBuilder addHomePageView() {
        homePageViewModel = new HomePageViewModel();
        homePageView = new HomePageView(homePageViewModel);
        cardPanel.add(homePageView, homePageView.getViewName());
        return this;
    }

    /**
     * Adds the HomePage Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomePageUseCase() {
        final HomepageOutputBoundary homepageOutputBoundary = new HomePagePresenter(viewManagerModel,
                signupViewModel, homePageViewModel);
        final HomepageInputBoundary userHomepageInteractor = new HomepageInteractor(homepageOutputBoundary);

        final HomePageController controller = new HomePageController(userHomepageInteractor);
        return this;
    }

    /**
     * Adds the ViewProfilesView to the application.
     * @return this builder
     */
    public AppBuilder addViewProfileView() {
        viewProfileViewModel = new ViewProfileViewModel();
        viewprofileview = new ViewProfileView(viewProfileViewModel);
        cardPanel.add(viewprofileview, viewprofileview.getViewName());
        return this;
    }

    /**
     * Adds the ViewProfile Use Case to the application.
     * @return this builder
     */
    public AppBuilder addViewProfileUseCase() {
        final ViewProfileOutputBoundry viewProfileOutputBoundry = new ViewProfilePresenter(viewManagerModel,
                viewProfileViewModel);
        final ViewProfileInputBoundary viewProfileInteractor = new ViewProfileInteractor(
                viewProfileOutputBoundry);

        final ViewProfileController controller = new ViewProfileController(viewProfileInteractor);
        viewprofileview.setSignupController(controller);
        return this;
    }

    /**
     * Adds the DisplayMatchesView to the application.
     * @return this builder
     */
    public AppBuilder addDisplayMatchesView() {
        displayMatchesViewModel = new DisplayMatchesViewModel();
        displayMatchesView = new DisplayMatchesView(displayMatchesViewModel);
        cardPanel.add(displayMatchesView, displayMatchesView.getViewName());
        return this;
    }

    /**
     * Adds the SignupView to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the LoginView to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the DisplayMatches Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayMatchesUseCase() {
        final DisplayMatchesOutputBoundary displayMatchesOutputBoundary = new DisplayMatchesPresenter(displayMatchesViewModel);
        final DisplayMatchesInputBoundary displayMatchesInteractor = new DisplayMatchesInteractor(
                dataAccessObject, displayMatchesOutputBoundary);

        final DisplayMatchesController controller = new DisplayMatchesController(displayMatchesInteractor);
        displayMatchesView.setDisplayMatchesController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, displayMatchesViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                dataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                displayMatchesViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                dataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(dataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(dataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addEditProfileView() {
        editProfileViewModel = new EditProfileViewModel();
        editProfileView = new EditProfileView(editProfileViewModel);  // Pass ViewModel instead of controller
        cardPanel.add(editProfileView, editProfileView.getViewName());
        return this;
    }

    public AppBuilder addEditProfileUseCase() {
        EditProfileOutputBoundary editProfileOutputBoundary = new EditProfilePresenter(viewManagerModel, editProfileViewModel);
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(dataAccessObject, editProfileOutputBoundary);
        EditProfileController controller = new EditProfileController(editProfileInteractor);
        editProfileView.setController(controller);  // Set controller after creation
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Home Page");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(HomePageView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
