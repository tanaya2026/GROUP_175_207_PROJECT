package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String email;
    private final String name;
    private final String courses;
    private final String program;
    private final String bio;
    private final String avaliablity;


    public SignupInputData(String username, String password, String repeatPassword, String email, String name, String courses, String program, String bio, String avaliablity) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.courses = courses;
        this.program = program;
        this.bio = bio;
        this.avaliablity = avaliablity;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getEmail() {
        return email;
    }

    String getName() {
        return name;
    }

    String getCourses() {
        return courses;
    }

    String getProgram() {
        return program;
    }

    String getBio() {
        return bio;
    }

    String getAvaliablity() {
        return avaliablity;
    }
}

