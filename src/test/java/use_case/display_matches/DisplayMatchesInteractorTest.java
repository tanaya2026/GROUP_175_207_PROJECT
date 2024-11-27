package use_case.display_matches;

import data_access.DataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayMatchesInteractorTest {

    @BeforeAll
    void setUp() {
        DataAccessObject dao = new DataAccessObject();

    }

    @Test
    void successTest() {
        DisplayMatchesDataAccessInterface userRepository = new DataAccessObject();


        // For the success test, we need to add the user to the data access repository before we catch find matches.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("smarsh", "smarsh@gmail.com", "wendy", "Stan Marsh", List< Course > courses,
                "Computer Science", "Hello", Map< Timeslot, Boolean> availability, userRepository);
        userRepository.save(user);

        DisplayMatchesInputData inputData = new DisplayMatchesInputData("Paul", "password");

        // This creates a successPresenter that tests whether the test case is as we expect.
        DisplayMatchesOutputBoundary successPresenter = new DisplayMatchesOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayMatchesOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        DisplayMatchesInputBoundary interactor = new DisplayMatchesInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @AfterAll
    void tearDown() {

    }
}
