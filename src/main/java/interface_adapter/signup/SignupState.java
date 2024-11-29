package interface_adapter.signup;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
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
     * Getter function for SignUpData.
     *
     * @return bio the biography of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter function for SignUpData.
     *
     * @return avaliability the avaliability of the user.
     */

    public Map<Timeslot, Boolean> getAvaliability() {
        return avaliability;
    }

    /**
     * Getter function for SignUpData.
     *
     * @return slotifyService an instance of the Slotify service.
     */

    public SlotifyServiceInterface getSlotifyService() {
        return slotifyService;
    }

    // write all the setter methods!

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setUsernameError(String usernameError) {
//        this.usernameError = usernameError;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setPasswordError(String passwordError) {
//        this.passwordError = passwordError;
//    }
//
//    public void setRepeatPassword(String repeatPassword) {
//        this.repeatPassword = repeatPassword;
//    }
//
//    public void setRepeatPasswordError(String repeatPasswordError) {
//        this.repeatPasswordError = repeatPasswordError;
//    }
//
//    @Override
//    public String toString() {
//        return "SignupState{"
//                + "username='" + username + '\''
//                + ", password='" + password + '\''
//                + ", repeatPassword='" + repeatPassword + '\''
//                + '}';
//     }
}
