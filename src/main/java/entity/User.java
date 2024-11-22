package entity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import api.SlotifyDataBase;

import entity.Availability;

/**
 * Represents a user - a student looking for study buddies.
 */
public class User {

    // Refer to the API documentation for the meaning of these fields.
    private final String username;
    private String email;
    private String password;
    private String name;
    private List<Course> courses;
    private String program;
    private String bio;
    private String resourceID;
    private String schedulerID;

    public User(String username, String email, String password, String name, List<Course> courses,
                String program, String bio, Map<Timeslot, Boolean> availability) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.courses = courses;
        this.program = program;
        this.bio = bio;

        SlotifyDataBase db = new SlotifyDataBase();
        this.resourceID = db.createSlotifyResource(name, email);
        this.schedulerID = db.createSlotifyScheduler(availability, resourceID);
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
     * Returns the name of the user.
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the name of the user.
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * Returns the schedulerID of the user's availability.
     * @return the schedulerID of the user's availability.
     */
    public String getSchedulerID() {
        return schedulerID;
    }

    /**
     * Sets the schedulerID of the user's availability.
     * @param schedulerID the schedulerID of the user's availability.
     */
    public void setSchedulerID(String schedulerID) {
        this.schedulerID = schedulerID;
    }

    /**
     * Returns the availability of the user based on the user's schedulerID.
     * @return the Availability object representing the user's availability.
     */
    public Map<Timeslot, Boolean> getAvailability() {
        return Availability.getAvailability(schedulerID);
    }

}
