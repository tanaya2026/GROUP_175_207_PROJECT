package use_case.edit_profile;

import entity.User;
import java.util.Map;

public interface EditProfileDataAccessInterface {
    User getUserById(String userId);

    void updateUser(User user);

    void updateAvailability(String schedulerId, Map<Timeslot, Boolean> availability);
}
