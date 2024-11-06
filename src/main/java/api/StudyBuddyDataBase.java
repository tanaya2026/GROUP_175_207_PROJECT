package api;

import org.json.JSONException;

import entity.User;

/**
 * StudyBuddyDataBase is an interface that defines the methods that the StudyBuddyDataBase class
 * must implement.
 */
public interface StudyBuddyDataBase {
    /**
     * A method that returns the grade of a student in a course.
     * @param username is the username of the student.
     * @param course is the course that the student is taking.
     * @return the grade of the student in the course.
     */
    // User getGrade(String username, String course);

    /**
     * A method that logs the grade of a student in a course.
     * @param course is the course that the student is taking.
     * @param grade is the grade of the student in the course.
     * @return the grade of the student in the course.
     * @throws JSONException if an error occurs.
     */
    // Grade logGrade(String course, int grade) throws JSONException;

    /**
     * A method that returns the grades of a student in all courses.
     * @param username is the username of the student.
     * @return the grades of the student in all courses.
     * @throws JSONException if an error occurs.
     */
    // Grade[] getGrades(String username) throws JSONException;

}
