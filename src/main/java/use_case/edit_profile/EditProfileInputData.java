package use_case.edit_profile;

import entity.Course;
import entity.Timeslot;
import java.util.List;
import java.util.Map;

public class EditProfileInputData {
    private final String email;
    private final String password;
    private final String name;
    private final String bio;
    private final String program;
    private final List<Course> courses;
    private final Map<Timeslot, Boolean> availability; // Updated availability
    private final String schedulerID; // Needed for deleting the current schedule

    public EditProfileInputData(String email, String password, String name, String bio, String program,
                                List<Course> courses, Map<Timeslot, Boolean> availability, String schedulerID) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.program = program;
        this.courses = courses;
        this.availability = availability;
        this.schedulerID = schedulerID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getProgram() {
        return program;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Map<Timeslot, Boolean> getAvailability() {
        return availability;
    }

    public String getSchedulerID() {
        return schedulerID;
    }
}
