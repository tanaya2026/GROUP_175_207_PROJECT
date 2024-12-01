package use_case.signup;

import data_access.DataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {

    @Test
    void successTest() {
        // Creating testing variables
        SlotifyServiceInterface slotifyService = null;
        Course course = new Course("CSC207","Software Design");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        SignupInputData inputData = new SignupInputData("Paul", "password", "paul.timms@gmail.com", "Paul Timms", courseList, "Bioinformatics", "I love CS", new HashMap<>(), slotifyService);
        SignupUserDataAccessInterface userRepository = new DataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {

        // Creating testing variables
        SlotifyServiceInterface slotifyService = null;
        Course course = new Course("CSC207","Software Design");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        SignupInputData inputData = new SignupInputData("Paul", "password", "paul.timms@gmail.com", "Paul Timms", courseList, "Bioinformatics", "I love CS", new HashMap<>(), slotifyService);
        SignupUserDataAccessInterface userRepository = new DataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {

        // Creating testing variables
        SlotifyServiceInterface slotifyService = null;
        Course course = new Course("CSC207","Software Design");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        SignupInputData inputData = new SignupInputData("Paul", "password", "paul.timms@gmail.com", "Paul Timms", courseList, "Bioinformatics", "I love CS", new HashMap<>(), slotifyService);
        SignupUserDataAccessInterface userRepository = new DataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", "paul.timms@gmail.com", "Paul Timms", courseList, "Bioinformatics", "I love CS", new HashMap<>(), slotifyService);
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

}
