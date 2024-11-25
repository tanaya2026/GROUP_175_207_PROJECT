package use_case.edit_profile;

import entity.Course;
import entity.Timeslot;

import java.util.List;
import java.util.Map;

public class EditProfileOutputData {
    private final String name;
    private final String password;
    private final String bio;
    private final String email;
    private final String program;
    private final List<Course> courses; // Store Course objects directly
    private final Map<Timeslot, Boolean> availability;

    public EditProfileOutputData(String name, String password, String bio, String email, String program,
                                 List<Course> courses, Map<Timeslot, Boolean> availability) {
        this.name = name;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.program = program;
        this.courses = courses;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
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
}

