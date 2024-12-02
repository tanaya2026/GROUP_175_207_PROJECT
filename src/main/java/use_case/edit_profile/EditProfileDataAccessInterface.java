package use_case.edit_profile;

import entity.Timeslot;
import entity.User;
import java.util.Map;

public interface EditProfileDataAccessInterface {
    String getCurrentUsername();
    /**
     * Checks if a user exists by username.
     * @param username the username to check.
     * @return true if the user exists, false otherwise.
     */
    boolean existsByName(String username);

    /**
     * Fetches a user by their username.
     * @param username the username of the user.
     * @return the User object corresponding to the username.
     */
    User getUserByUsername(String username);

    /**
     * Saves or updates a user in the data store.
     * @param user the user to save or update.
     */
    void save(User user);

    /**
     * Deletes a scheduler by its ID.
     * @param schedulerId the ID of the scheduler to delete.
     */
    void deleteSlotifyScheduler(String schedulerId);

    /**
     * Creates a new scheduler with the provided availability and resource ID.
     * @param availability the availability map for the scheduler.
     * @param resourceId the resource ID associated with the user.
     * @return the scheduler ID of the newly created scheduler.
     */
    String createSlotifyScheduler(Map<Timeslot, Boolean> availability, String resourceId);

    /**
     * Fetches the availability associated with a scheduler ID.
     * @param schedulerId the ID of the scheduler.
     * @return the availability map for the scheduler.
     */
    Map<Timeslot, Boolean> fetchAvailability(String schedulerId);
}
