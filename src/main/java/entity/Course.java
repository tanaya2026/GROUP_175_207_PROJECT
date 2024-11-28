package entity;

import java.util.Objects;

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

    // Override equals() to compare based on course code
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return Objects.equals(courseCode, course.getCourseCode());
    }

    // Override hashCode() to ensure consistency with equals()
    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
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
