package entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param username the username of the new user
     * @param email the email of the new user
     * @param password the password of the new user
     * @param name the name of the new user
     * @param courses a list of courses the new user is currently taking
     * @param program the program of the new user
     * @param bio the bio of the new user
     * @param availability the availability of the new user
     * @param slotifyService the interface for making calls to the Slotify API
     * @return the new user
     */
    User create(String username, String email, String password, String name, List<Course> courses,
                String program, String bio, Map<Timeslot, Boolean> availability, SlotifyServiceInterface slotifyService);

}

