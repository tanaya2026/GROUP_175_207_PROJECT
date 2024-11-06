package entity;

import javax.management.relation.Role;
import java.util.List;

/**
 * Represents a user - a student looking for study buddies.
 */
public class User {

    // Refer to the API documentation for the meaning of these fields.
    private final String username;
    private final String email;
    private final String password;
    private final List<String> courses;
    private final String program;
    private final String bio;


    public User(String username, String email, String password, List<String> courses, String program, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.courses = courses;
        this.program = program;
        this.bio = bio;
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
            for (String course : courses) {
                sb.append(prefix);
                prefix = ", ";
                sb.append(course);
            }
            lstCourses = sb.toString();
        }

        return "User {\n"
                + "  username: " + username + ",\n"
                + "  email: " + email + ",\n"
                + "  courses: " + lstCourses + ",\n"
                + "  program: " + program + ",\n"
                + "  bio: " + bio + '}';
    }

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the course of the grade.
     * @return the course of the grade.
     */
    public String getCourse() {
        return course;
    }


    /**
     * Returns a new GradeBuilder instance.
     * @return a new GradeBuilder instance.
     */
    public static GradeBuilder builder() {
        return new GradeBuilder();
    }

    /**
     * Represents a builder for creating instances of a Grade.
     */
    public static class GradeBuilder {
        private String username;
        private String course;
        private int grade;

        GradeBuilder() {
        }

        /**
         * Sets the username of the student.
         * @param usernameInput the username of the student.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder username(String usernameInput) {
            this.username = usernameInput;
            return this;
        }

        /**
         * Sets the course of the grade.
         * @param courseInput the course of the grade.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder course(String courseInput) {
            this.course = courseInput;
            return this;
        }

        /**
         * Sets the grade of the student.
         * @param gradeInput the grade of the student.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder grade(int gradeInput) {
            this.grade = gradeInput;
            return this;
        }

        /**
         * Builds a new Grade instance.
         * @return a new Grade instance.
         */
        public Grade build() {
            return new Grade(username, course, grade);
        }
    }

}
