package entity;

import java.util.List;

// import javax.management.relation.Role;

/**
 * Represents a user - a student looking for study buddies.
 */
public class User {

    // Refer to the API documentation for the meaning of these fields.
    private final String username;
    private String email;
    private String password;
    private List<Course> courses;
    private String program;
    private String bio;
    private Availability availability;

    public User(String username, String email, String password, List<Course> courses,
                String program, String bio, Availability availability) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.courses = courses;
        this.program = program;
        this.bio = bio;
        this.availability = availability;
    }

    @Override
    public String toString() {
        String lstCourses;
        if (courses.isEmpty()) {
            lstCourses = "No courses added";
        }
        else {
            StringBuilder sb = new StringBuilder();
            String prefix = "";
            for (Course course : courses) {
                sb.append(prefix);
                prefix = ", ";
                sb.append(course.getCourseCode());
            }
            lstCourses = sb.toString();
        }

        final String newLine = "," + System.lineSeparator();

        return "User {" + System.lineSeparator()
                + "  username: " + username + newLine
                + "  email: " + email + newLine
                + "  courses: " + lstCourses + newLine
                + "  program: " + program + newLine
                + "  bio: " + bio + newLine
                + "  availability: " + availability.toString() + '}';
    }

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // TO BE REMOVED AFTER USER DB IMPLEMENTED
    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the email of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the courses the user is enrolled in.
     * @return the list of Courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Adds the course to the user's courses if it is not already added.
     * @param course the course being added to the user.
     */
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    /**
     * Adds these courses to the user's courses if they are not already added.
     * @param coursesToAdd the list of courses being added to the user.
     */
    public void addCourses(List<Course> coursesToAdd) {
        for (Course course : coursesToAdd) {
            addCourse(course);
        }
    }

    /**
     * Removes the course from the user's courses if the user has this course.
     * @param course the course being added to the user.
     */
    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
        }
    }

    /**
     * Removes these courses from the user's courses if the user has these courses.
     * @param coursesToRemove the list of courses being added to the user.
     */
    public void removeCourses(List<Course> coursesToRemove) {
        for (Course course : coursesToRemove) {
            removeCourse(course);
        }
    }

    /**
     * Returns the program of the user.
     * @return the program of the user.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Sets the program of the user.
     * @param program the bio of the user.
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * Returns the bio of the user.
     * @return the bio of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the bio of the user.
     * @param bio the bio of the user.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

}
