package interface_adapter.signup;


import java.util.List;
import java.util.Map;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password the password
     * @param email the email to sign up
     * @param name the user's name to sign up
     * @param courses the courses to sign up
     * @param program the program to sign up
     * @param bio the biography to sign up
     * @param avaliablity the avaliablity to sign up
     * @param slotifyService the slotify service - API.
     */
    public void execute(String username, String password, String email, String name, List<Course> courses, String program, String bio, Map<Timeslot, Boolean> avaliablity, SlotifyServiceInterface slotifyService) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password, email, name, courses, program, bio, avaliablity, slotifyService);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
