package use_case.view_profile;

import java.util.List;
import java.util.Map;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;
import entity.User;

/**
 * Output Data for the ViewProfile Use Case.
 */

public class ViewprofileOutputData {
    private String username;
    private String password;
    private String name;
    private String email;
    private List<Course> courses;
    private String program;
    private String bio;
    private Map<Timeslot, Boolean> avaliability;
    private SlotifyServiceInterface slotify;

    public ViewprofileOutputData(List<User> users) {
        for (User user: users) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.email = user.getEmail();
            this.courses = user.getCourses();
            this.program = user.getProgram();
            this.bio = user.getBio();
            this.avaliability = user.getAvailability(slotify);
        }
    }

    /**
     * Getter function for SignUpState.
     *
     * @return username the Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return password the password.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return name the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return email the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return courses the courses of the User.
     */

    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return program the name of the program the User is in.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return bio the biography of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter function for SignUpState.
     *
     * @return avaliability the avaliability of the user.
     */

    public Map<Timeslot, Boolean> getAvaliability() {
        return avaliability;
    }

}
