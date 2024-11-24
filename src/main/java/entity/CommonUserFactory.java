package entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String email, String password, String name, List<Course> courses,
                       String program, String bio, Map<Timeslot, Boolean> availability) {
        return new User(username, email, password, name, courses, program, bio, availability);
    }
}
