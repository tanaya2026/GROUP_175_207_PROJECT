package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        // Create a User from the SignUpInput Data
        final User user = userFactory.create(signupInputData.getUsername(),
                signupInputData.getEmail(),
                signupInputData.getPassword(),
                signupInputData.getName(),
                signupInputData.getCourses(),
                signupInputData.getProgram(),
                signupInputData.getBio(),
                signupInputData.getAvaliablity(),
                signupInputData.getSlotifyService());
        // Save the User's information/ User Object
        userDataAccessObject.save(user);

        // Create a SignupOutputData object, and pass in the User's Name.
        final SignupOutputData signupOutputData = new SignupOutputData(user.getName());
        // Inject that Dats into the Presenter.
        userPresenter.prepareSuccessView(signupOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}

