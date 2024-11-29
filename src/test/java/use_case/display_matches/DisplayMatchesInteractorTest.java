package use_case.display_matches;

import data_access.DataAccessObject;
import entity.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DisplayMatchesInteractorTest {

    private DataAccessObject dataAccessObject;
    private List<Course> userCourses;
    private Map<Timeslot, Boolean> userAvailability;
    private User currentUser;

    @BeforeEach
    void setUp() {
        dataAccessObject = new DataAccessObject();

        List<Course> availableCourses = dataAccessObject.getCourses();
        userCourses = new ArrayList<>();
        for (Course course : availableCourses) {
            // To verify if matching by course aspect works
            // Only users taking either CSC207 or CSC236 should be returned as matches
            if (course.getCourseCode().equals("CSC207") || course.getCourseCode().equals("CSC236")) {
                userCourses.add(course);
            }
        }

        userAvailability = new HashMap<>();

        // To verify 09:00 special case works
        Timeslot timeslot1 = new Timeslot(1, 9);
        userAvailability.put(timeslot1, false);

        // To verify multiple Timeslots blocked within the same day works
        Timeslot timeslot2 = new Timeslot(1, 14);
        Timeslot timeslot3 = new Timeslot(1, 15);
        userAvailability.put(timeslot2, false);
        userAvailability.put(timeslot3, false);

        // To verify Timeslots blocked across many days works
        Timeslot timeslot4 = new Timeslot(2, 10);
        Timeslot timeslot5 = new Timeslot(4, 12);
        Timeslot timeslot6 = new Timeslot(5, 16);
        userAvailability.put(timeslot4, false);
        userAvailability.put(timeslot5, false);
        userAvailability.put(timeslot6, false);

        // To verify blocking off an entire day works
        for (int hour = 9; hour <= 16; hour++) {
            Timeslot timeslot = new Timeslot(6, hour);
            userAvailability.put(timeslot, false);
        }

        // For the success test, we need to add the user to the data access repository before we catch find matches.
        SlotifyServiceInterface slotifyServiceInterface = new DataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("smarsh", "smarsh@gmail.com", "wendy", "Stan Marsh", userCourses,
                "Computer Science", "Hello", userAvailability, slotifyServiceInterface);
        dataAccessObject.save(user);
        currentUser = user;
    }

    @Test
    void successMatchByCourseTest() {
        DisplayMatchesDataAccessInterface userRepository = new DataAccessObject();
        DisplayMatchesInputData inputData = new DisplayMatchesInputData(currentUser, false);

        // Building the expected Map<User, List<Timeslot>> matches result.
        // However, the returned List of Timeslots for each match is not guaranteed to be in the same order each time.
        // The order of Timeslots does not matter, and all Timeslots in the list will be unique.
        // As such, for the purposes of testing, a Set of Timeslots is suitable to verify its contents are as expected.
        Set<Timeslot> djExpectedTimeslots = Set.of(
                new Timeslot(1, 11), new Timeslot(2, 15), new Timeslot(3, 13),
                new Timeslot(3, 14), new Timeslot(4, 11), new Timeslot(4, 13),
                new Timeslot(4, 14), new Timeslot(4, 15), new Timeslot(5, 11),
                new Timeslot(5, 14), new Timeslot(7, 11), new Timeslot(7, 13)
        );

        Set<Timeslot> csExpectedTimeslots = Set.of(
                new Timeslot(1, 16), new Timeslot(2, 14), new Timeslot(2, 16),
                new Timeslot(3, 11), new Timeslot(4, 9), new Timeslot(4, 10),
                new Timeslot(4, 16), new Timeslot(5, 14), new Timeslot(7, 9),
                new Timeslot(7, 15), new Timeslot(7, 16)
        );

        // This creates a successPresenter that tests whether the test case is as expected.
        DisplayMatchesOutputBoundary successPresenter = new DisplayMatchesOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayMatchesOutputData user) {
                Map<User, List<Timeslot>> matches = user.getMatches();
                assertEquals(2, matches.size(), "The matches map should have exactly 2 entries.");

                Set<String> expectedUsernames = Set.of("djackson", "csinger");
                Set<String> actualUsernames = matches.keySet().stream()
                        .map(User::getUsername)
                        .collect(Collectors.toSet());
                assertEquals(expectedUsernames, actualUsernames, "The usernames in the map do not match the expected usernames.");

                // Verify timeslots for djackson
                Set<Timeslot> djActualTimeslots = matches.entrySet().stream()
                        .filter(entry -> "djackson".equals(entry.getKey().getUsername()))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("djackson not found in matches"))
                        .getValue()
                        .stream()
                        .collect(Collectors.toSet());
                assertEquals(djExpectedTimeslots, djActualTimeslots, "The timeslots for djackson do not match the expected set.");

                // Verify timeslots for csinger
                Set<Timeslot> csActualTimeslots = matches.entrySet().stream()
                        .filter(entry -> "csinger".equals(entry.getKey().getUsername()))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("csinger not found in matches"))
                        .getValue()
                        .stream()
                        .collect(Collectors.toSet());
                assertEquals(csExpectedTimeslots, csActualTimeslots, "The timeslots for csinger do not match the expected set.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        DisplayMatchesInputBoundary interactor = new DisplayMatchesInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @AfterEach
    void tearDown() {
        dataAccessObject.deleteSlotifyScheduler(currentUser.getSchedulerID());
        dataAccessObject.deleteSlotifyResource(currentUser.getResourceID());
    }
}
