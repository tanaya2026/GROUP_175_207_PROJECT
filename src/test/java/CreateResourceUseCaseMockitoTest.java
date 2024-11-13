import api.SlotifyDataBase;
import entity.Course;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.CreateResourceUseCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing get average grade across your team.
 * The idea is that we don't want to wait for the specific API call to be implemented.
 * <p>
 * This demonstrates using the Mockito library for "mocking" the GradeDataBase.
 * <p>
 * This should pass if the "to do" Task 3a is correctly implemented.
 */
public class CreateResourceUseCaseMockitoTest {
    @Mock
    private SlotifyDataBase slotifyDataBase;

    private CreateResourceUseCase createResourceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Use openMocks() instead of initMocks() if using Mockito 3.2+
        createResourceUseCase = new CreateResourceUseCase(slotifyDataBase);
    }

    @Test
    void testCreateResource() {

        Course testCourse = new Course("CSC207", "Software Design");
        List<Course> testCourses = new ArrayList<>();
        testCourses.add(testCourse);

        User testUser = new User("abc", "abc@gmail.com", "123"
        , "Jamie Jolly", testCourses, "Biochem", "hello!");

        assertDoesNotThrow(() -> createResourceUseCase.createSlotifyResource(testUser));

    }
}
