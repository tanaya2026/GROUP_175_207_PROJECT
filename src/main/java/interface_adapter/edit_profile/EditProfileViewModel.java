package interface_adapter.edit_profile;

import entity.Course;
import entity.Timeslot;
import use_case.edit_profile.EditProfileOutputData;

import java.util.List;
import java.util.Map;

public class EditProfileViewModel {
    private String name;
    private String password;
    private String bio;
    private String email;
    private String program;
    private List<Course> courses; // List of courses
    private Map<Timeslot, Boolean> availability; // Availability map
    private EditProfileState state;

    public EditProfileViewModel() {
        this.state = new EditProfileState();
    }

    /**
     * Updates the ViewModel with output data.
     *
     * @param outputData the data from the interactor.
     */
    public void update(EditProfileOutputData outputData) {
        this.name = outputData.getName();
        this.password = outputData.getPassword();
        this.bio = outputData.getBio();
        this.email = outputData.getEmail();
        this.program = outputData.getProgram();
        this.courses = outputData.getCourses();
        this.availability = outputData.getAvailability();
        this.state.setSuccessful(true);
    }

    /**
     * Sets an error message in the state.
     *
     * @param errorMessage the error message to display.
     */
    public void setError(String errorMessage) {
        this.state.setErrorMessage(errorMessage);
        this.state.setSuccessful(false);
    }

    // Getters
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

    public EditProfileState getState() {
        return state;
    }
}
