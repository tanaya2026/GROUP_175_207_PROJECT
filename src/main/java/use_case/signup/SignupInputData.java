package use_case.signup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String email;
    private final String name;
    private final List<Course> courses;
    private final String program;
    private final String bio;
    private final Map<Timeslot, Boolean> avaliability;
    private final SlotifyServiceInterface slotifyService;

    public SignupInputData(String username, String password, String email, String name, List<Course> courses, String program, String bio, Map<Timeslot, Boolean> avaliability, SlotifyServiceInterface slotifyService) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.courses = courses;
        this.program = program;
        this.bio = bio;
        this.avaliability = avaliability;
        this.slotifyService = slotifyService;
    }

    /**
     * Getter function for SignUpData.
     * @return username the Username.
     */

    String getUsername() {
        return username;
    }

    /**
     * Getter function for SignUpData.
     * @return password the password.
     */

    String getPassword() {
        return password;
    }

    /**
     * Getter function for SignUpData.
     * @return email the email.
     */

    String getEmail() {
        return email;
    }

    /**
     * Getter function for SignUpData.
     * @return name the name.
     */

    String getName() {
        return name;
    }

    /**
     * Getter function for SignUpData.
     * @return courses the courses of the User.
     */

    List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (String courseCode : coursesinfo.getText().split(",")) {
            courses.add(new Course(courseCode.trim()));

            return courses;
        }
    }

    /**
     * Getter function for SignUpData.
     * @return program the name of the program the User is in.
     */

    String getProgram() {
        return program;
    }

    /**
     * Getter function for SignUpData.
     * @return bio the biography of the user.
     */

    String getBio() {
        return bio;
    }

    /**
     * Getter function for SignUpData.
     * @return avaliability the avaliability of the user.
     */

    Map<Timeslot, Boolean> getAvaliablity() {
        return avaliability;
    }

    /**
     * Getter function for SignUpData.
     * @return slotifyService an instance of the Slotify service.
     */

    SlotifyServiceInterface getSlotifyService() {
        return slotifyService;
    }
}

