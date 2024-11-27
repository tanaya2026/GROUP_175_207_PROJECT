package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Course;
import entity.User;

/**
 * Utility class to load in the courses and default users to the in-memory DB.
 */
public class DBBuilder {

    /**
     * Load in the courses that students can be enrolled in.
     * @return a list of the courses that students can be enrolled in.
     */
    protected static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSC207", "Software Design"));
        courses.add(new Course("CSC110", "Foundations of Computer Science I"));
        courses.add(new Course("CSC111", "Foundations of Computer Science II"));
        courses.add(new Course("CSC148", "Introduction to Computer Science"));
        courses.add(new Course("CSC165", "Mathematical Expression and Reasoning for Computer Science"));
        courses.add(new Course("CSC240", "Enriched Introduction to the Theory of Computation"));
        courses.add(new Course("MAT137", "Calculus with Proofs"));
        courses.add(new Course("CSC236", "Introduction to the Theory of Computation"));
        courses.add(new Course("AST251", "Life on Other Worlds"));
        // Add more?
        return courses;
    }

    /**
     * Load in some hypothetical users who have signed up.
     * @return a map of the usernames and User objects of these hypothetical users.
     */
    protected static Map<String, User> loadUsers() {
        List<Course> courses = loadCourses();
        // CSC148, CSC165
        List<Course> ajohnsonCourses = new ArrayList<>();
        // CSC110, MAT137, AST251
        List<Course> jsmithCourses = new ArrayList<>();
        // CSC207, CSC236
        List<Course> csingerCourses = new ArrayList<>();
        // CSC207, CSC236, AST251
        List<Course> djacksonCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseCode().equals("CSC148")) {
                ajohnsonCourses.add(course);
            }
            if (course.getCourseCode().equals("CSC165")) {
                ajohnsonCourses.add(course);
            }
            if (course.getCourseCode().equals("CSC110")) {
                jsmithCourses.add(course);
            }
            if (course.getCourseCode().equals("MAT137")) {
                jsmithCourses.add(course);
            }
            if (course.getCourseCode().equals("AST251")) {
                jsmithCourses.add(course);
                djacksonCourses.add(course);
            }
            if (course.getCourseCode().equals("CSC207")) {
                csingerCourses.add(course);
                djacksonCourses.add(course);
            }
            if (course.getCourseCode().equals("CSC236")) {
                csingerCourses.add(course);
                djacksonCourses.add(course);
            }
        }
        String programComputerScience = "Computer Science";
        String ajohnsonBio = "I study best in the mornings!";
        String jsmithBio = "To be totally honest, I've fallen behind in calc and could use some help explaining concepts.";
        String csingerBio = "I love studying with a good snack, but sometimes end up eating more than studying... :(";
        String djacksonBio = "Looking to pursue a career in AI";
        String ajohnsonSchedulerID = "217e62a0-3725-4173-99c3-86dd92f7fb9e";
        String jsmithSchedulerID = "aaa7c176-9e23-4368-abc6-94665d8cf822";
        String csingerSchedulerID = "e44bc9da-f5a7-4fcc-a0d9-3c7b31ec1bb3";
        String djacksonSchedulerID = "55120d37-50e2-469e-8632-ff1d0ee7db09";
        List<User> userList = new ArrayList<>();
        userList.add(new User("ajohnson", "ajohn@gmail.com", "Andrew Johnson", ajohnsonCourses, programComputerScience, ajohnsonBio, ajohnsonSchedulerID));
        userList.add(new User("jsmith", "jess03ica@hotmail.com", "Jessica Smith", jsmithCourses, "Mathematics", jsmithBio, jsmithSchedulerID));
        userList.add(new User("csinger", "camsing5090@gmail.com", "Cameron Singer", csingerCourses, programComputerScience, csingerBio, csingerSchedulerID));
        userList.add(new User("djackson", "dennisjackson17@outlook.com", "Dennis Jackson", djacksonCourses, programComputerScience, djacksonBio, djacksonSchedulerID));

        Map<String, User> users = new HashMap<>();
        for (User user : userList) {
            users.put(user.getUsername(), user);
        }
        return users;
    }
}
