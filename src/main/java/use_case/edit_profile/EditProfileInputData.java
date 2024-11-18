package use_case.edit_profile;

import entity.Timeslot;
import java.util.Map;

public class EditProfileInputData {
    private final String name;
    private final String bio;
    private final Map<Timeslot, Boolean> availability;

    public EditProfileInputData(String name, String bio, Map<Timeslot, Boolean> availability) {
        this.name = name;
        this.bio = bio;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Map<Timeslot, Boolean> getAvailability() {
        return availability;
    }
}
