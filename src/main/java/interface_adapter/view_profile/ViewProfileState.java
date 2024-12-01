package interface_adapter.view_profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;

/**
 * The state for the ViewProfile View Model.
 */

public class ViewProfileState {
    private String username = "";
    private String password = "";
    private String name = "";
    private String email = "";
    private List<Course> courses = new ArrayList<>();
    private String program = "";
    private String bio = "";
    private Map<Timeslot, Boolean> avaliability = new HashMap<>();
    private SlotifyServiceInterface slotifyService;

    /**
     * Getter function for ViewProfileState.
     *
     * @return username the Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return password the password.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return name the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return email the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return courses the courses of the User.
     */

    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return program the name of the program the User is in.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return bio the biography of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return avaliability the avaliability of the user.
     */

    public Map<Timeslot, Boolean> getAvaliability() {
        return avaliability;
    }

    /**
     * Getter function for ViewProfileState.
     *
     * @return slotifyService an instance of the Slotify service.
     */

    public SlotifyServiceInterface getSlotifyService() {
        return slotifyService;
    }


    /**
     * Setter function for ViewProfileState.
     * @param  username the Username.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter function for ViewProfileState.
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter function for ViewProfileState.
     * @param  name the Username.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter function for ViewProfileState.
     * @param  email the email of the User.
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Setter function for ViewProfileState.
     *
     * @param program the Program.
     */

    public void setProgram(String program) {
        this.program = program;
    }
    /**
     * Setter function for ViewProfileState.
     *
     * @param bio the Bio of the User.
     */

    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Setter function for ViewProfileState.
     * @param courses the Courses of the User.
     */

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Setter function for ViewProfileState.
     * @param avaliability the avaliablity of the User.
     */
    public void setAvaliability(Map<Timeslot, Boolean> avaliability) {
        this.avaliability = avaliability;
    }
}
