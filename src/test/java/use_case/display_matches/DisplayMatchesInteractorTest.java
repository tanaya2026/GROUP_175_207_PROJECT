package use_case.display_matches;

import data_access.DataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayMatchesInteractorTest {

    private DataAccessObject dataAccessObject;
    private List<Course> userCourses;
    private Map<Timeslot, Boolean> userAvailability;

    @BeforeAll
    void setUp() {
        dataAccessObject = new DataAccessObject();

        List<Course> availableCourses = dataAccessObject.getCourses();
        userCourses = new ArrayList<>();
        for (Course course : availableCourses) {
            if (course.getCourseCode().equals("CSC207") || course.getCourseCode().equals("CSC236")) {
                userCourses.add(course);
            }
        }

        Timeslot timeslot1 = new Timeslot(1, 14);
        Timeslot timeslot2 = new Timeslot(1, 15);
        Timeslot timeslot3 = new Timeslot(2, 10);
        Timeslot timeslot4 = new Timeslot(4, 12);
        Timeslot timeslot5 = new Timeslot(5, 16);
        userAvailability = new HashMap<>();
        userAvailability.put(timeslot1, false);
        userAvailability.put(timeslot2, false);
        userAvailability.put(timeslot3, false);
        userAvailability.put(timeslot4, false);
        userAvailability.put(timeslot5, false);
    }

    @Test
    void successTest() {
        DisplayMatchesDataAccessInterface userRepository = new DataAccessObject();
        SlotifyServiceInterface slotifyServiceInterface = new DataAccessObject();


        // For the success test, we need to add the user to the data access repository before we catch find matches.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("smarsh", "smarsh@gmail.com", "wendy", "Stan Marsh", userCourses,
                "Computer Science", "Hello", userAvailability, slotifyServiceInterface);
        dataAccessObject.save(user);

        DisplayMatchesInputData inputData = new DisplayMatchesInputData(user, false);

        // This creates a successPresenter that tests whether the test case is as we expect.
        DisplayMatchesOutputBoundary successPresenter = new DisplayMatchesOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayMatchesOutputData user) {
                assertEquals("Paul", user.getMatches());
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
