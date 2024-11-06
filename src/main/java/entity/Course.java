package entity;

/**
 * Represents a course - the course code and name.
 */
public class Course {

    private String courseCode;
    private String courseName;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    /**
     * Returns the code of the course, e.g. "CSC207".
     * @return the course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns the name of the course, e.g. "Software Design".
     * @return the course name.
     */
    public String getCourseName() {
        return courseName;
    }
}
