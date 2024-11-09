package entity;

/**
 * Represents a course - the course code and name.
 */
public class Course {

    private String courseCode;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
    }

    /**
     * Returns the code of the course, e.g. "CSC207".
     * @return the course code.
     */
    public String getCourseCode() {
        return courseCode;
    }
}
