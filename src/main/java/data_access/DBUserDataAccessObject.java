package data_access;

import entity.Availability;
import entity.Course;
import entity.Timeslot;
import entity.User;
import entity.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import usecase.change_password.ChangePasswordUserDataAccessInterface;
import usecase.login.LoginUserDataAccessInterface;
import usecase.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String COURSES = "courses";
    private static final String PROGRAM = "program";
    private static final String BIO = "bio";
    private static final String AVAILABILITY = "availability";
    private static final String DAY = "day";
    private static final String TIME = "time";
    private static final String IS_AVAILABLE = "isAvailable";
    private static final String MESSAGE = "message";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);
                final String email = userJSONObject.getString(EMAIL);
                final String program = userJSONObject.getString(PROGRAM);
                final String bio = userJSONObject.getString(BIO);

                // Parse courses array
                final JSONArray coursesArray = userJSONObject.getJSONArray(COURSES);
                List<Course> courses = parseCourses(coursesArray);

                // Parse availability JSON
                final JSONArray availabilityArray = userJSONObject.getJSONArray(AVAILABILITY);
                Map<Timeslot, Boolean> availabilityMap = parseAvailabilityJSON(availabilityArray);
                Availability availability = new Availability(availabilityMap);

                return userFactory.create(name, email, password, courses, program, bio, availability);
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        requestBody.put(EMAIL, user.getEmail());
        requestBody.put(PROGRAM, user.getProgram());
        requestBody.put(BIO, user.getBio());

        // Convert courses to JSON array
        JSONArray coursesArray = new JSONArray();
        for (Course course : user.getCourses()) {
            JSONObject courseJSON = new JSONObject();
            courseJSON.put("courseCode", course.getCourseCode());
            courseJSON.put("courseName", course.getCourseName());
            coursesArray.put(courseJSON);
        }
        requestBody.put(COURSES, coursesArray);

        // Convert availability to JSON array
        JSONArray availabilityArray = new JSONArray();
        for (Map.Entry<Timeslot, Boolean> entry : user.getAvailability().getAvailability().entrySet()) {
            JSONObject timeslotJSON = new JSONObject();
            Timeslot timeslot = entry.getKey();
            timeslotJSON.put(DAY, timeslot.getDay()); // Get the day from Timeslot
            timeslotJSON.put(TIME, timeslot.getTime()); // Get the time from Timeslot
            timeslotJSON.put(IS_AVAILABLE, entry.getValue());
            availabilityArray.put(timeslotJSON);
        }
        requestBody.put(AVAILABILITY, availabilityArray);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Success handling
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Helper method to parse courses JSON array into a List of Course objects.
     */
    private List<Course> parseCourses(JSONArray coursesArray) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJSON = coursesArray.getJSONObject(i);
            String courseCode = courseJSON.getString("courseCode");
            String courseName = courseJSON.getString("courseName");
            courses.add(new Course(courseCode, courseName));
        }
        return courses;
    }

    /**
     * Helper method to parse availability JSON array into a Map of Timeslot and Boolean.
     */
    private Map<Timeslot, Boolean> parseAvailabilityJSON(JSONArray availabilityArray) {
        Map<Timeslot, Boolean> availabilityMap = new HashMap<>();
        for (int i = 0; i < availabilityArray.length(); i++) {
            JSONObject timeslotJSON = availabilityArray.getJSONObject(i);
            int day = timeslotJSON.getInt(DAY);
            int time = timeslotJSON.getInt(TIME);
            boolean isAvailable = timeslotJSON.getBoolean(IS_AVAILABLE);

            Timeslot timeslot = new Timeslot(day, time);
            availabilityMap.put(timeslot, isAvailable);
        }
        return availabilityMap;
    }
}
