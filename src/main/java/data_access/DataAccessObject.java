package data_access;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

import entity.Course;
import entity.Matcher;
import entity.Timeslot;
import entity.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.find_matches.FindMatchesDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * UserDB class.
 */
public class DataAccessObject implements EditProfileDataAccessInterface,
        FindMatchesDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SignupUserDataAccessInterface {
    // Defining some constants.
    private static final String API_URL = "https://api.slotify.ca/v1";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT = "Accept";
    private static final String SUCCESS = "success";
    private static final String ERRORS = "errors";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String ROLE_VALUE = "member";
    private static final String TIMEZONE = "timezone";
    private static final String TIMEZONE_VALUE = "America/Toronto";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String API_TOKEN = "Bearer ODE4NzViZWMtYWZhOS00ZGY4LWEwOTktZjNkYmZlNjFlNWZi";
    private static final String UUID = "uuid";
    private static final int SUCCESSFUL_DELETION_CODE = 204;

    private static final String GRAPH = "graph";
    private static final String GRAPH_TYPE = "instant";
    private static final String COLOR = "color";
    private static final String HEX_CODE = "#9e0fd8";
    private static final String SLOT_CAPACITY = "slot_capacity";
    private static final int CAPACITY_VALUE = 8;
    private static final String MODE = "mode";
    private static final String MODE_TYPE = "mutual";
    private static final String MIN_NOTICE = "min_notice";
    private static final String NOTICE_DURATION = "1 days";
    private static final String DURATION = "duration";
    private static final String DURATION_TIME = "1 hours";
    private static final String BUFFER_TIME = "buffer_time";
    private static final String BUFFER_DURATION = "0 minutes";
    private static final String BOOKING_WINDOW = "booking_window";
    private static final String WINDOW_DURATION = "4 weeks";
    private static final String MIN_CANCELLATION = "min_cancellation";
    private static final String CANCELLATION_DURATION = "1 days";
    private static final String SCHEDULER_NAME = "Scheduler" + timedate();
    private static final String SLUG = "slug";
    private static final String SLUG_NAME = "newScheduler" + timedate();
    private static final String UNIT_PRICE = "unit_price";
    private static final int PRICE_VALUE = 0;
    private static final String PERDAY_CAPACITY = "perday_capacity";
    private static final int CAPACITY_PER_DAY = 500;

    private static final String RULES = "rules";
    private static final String RULE = "rule";
    private static final String EVERYDAY = "hours";
    private static final String TYPE = "type";
    private static final String ALLOWED = "allowed";
    private static final String TIMES = "times";
    private static final String START = "start";
    private static final String END = "end";
    private static final String DAY = "day";
    private static final String DAYTIME = "daytime";
    private static final String BLOCKED = "blocked";
    private static final String TIME_SUFFIX = ":00";
    private static final int SINGLE_DIGIT_9 = 9;

    private static final String RESOURCES = "resources";

    private static final String EVENT = "event";
    private static final String WHAT = "what";
    private static final String WHAT_VALUE = "Meeting with study buddy";
    private static final String LOCATION = "location";
    private static final String MEETING_TYPE = "google_meet";
    private static final String DESCRIPTION = "description";
    private static final String MEETING_DESCRIPTION = "Let's do some studying!";
    private static final String START_AT = "start_at";
    private static final String START_AT_DATE = "2024-12-08";

    private static final String SCHEDULER_ID = "scheduler_id";
    private static final String OUTPUT_TIMEZONE = "output_timezone";
    private static final String END_AT_DATE = "2024-12-14";

    private final Map<String, User> users = new HashMap<>();
    private final List<Course> courses = loadCourses();

    /**
     * Create a date time string to better identify the scheduler name.
     * @return string value of today's date and time in the specified format.
     */
    private static String timedate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Load in the courses that students can be enrolled in.
     * @return a list of the courses that students can be enrolled in.
     */
    private static List<Course> loadCourses() {
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
    private static Map<String, User> loadUsers() {
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
        String programCS = "Computer Science";
        String ajohnsonBio = "I study best in the mornings!";
        String jsmithBio = "To be totally honest, I've fallen behind in calc and could use some help explaining concepts.";
        String csingerBio = "I love studying with a good snack, but sometimes end up eating more than studying... :(";
        String djacksonBio = "Looking to pursue a career in AI";
        String ajohnsonSchedulerID = "";
        String jsmithSchedulerID = "";
        String csingerSchedulerID = "";
        String djacksonSchedulerID = "";
        List<User> userList = new ArrayList<>();
        userList.add(new User("ajohnson", "ajohn@gmail.com", "Andrew Johnson", ajohnsonCourses, programCS, ajohnsonBio, ajohnsonSchedulerID));
        userList.add(new User("jsmith", "jess03ica@hotmail.com", "Jessica Smith", jsmithCourses, "Mathematics", jsmithBio, jsmithSchedulerID));
        userList.add(new User("csinger", "camsing5090@gmail.com", "Cameron Singer", csingerCourses, programCS, csingerBio, csingerSchedulerID));
        userList.add(new User("djackson", "dennisjackson17@outlook.com", "Dennis Jackson", djacksonCourses, programCS, djacksonBio, djacksonSchedulerID));

        Map<String, User> users = new HashMap<>();
        for (User user : userList) {
            users.put(user.getUsername(), user);
        }
        return users;
    }

    /**
     * Convert the availabilityMap to the required JSONArray format for Slotify.
     * @param availabilityMap the user's availability in Map format.
     * @return the converted availabilityMap in JSONArray format.
     */
    private JSONArray ruleBuilder(Map<Timeslot, Boolean> availabilityMap) {
        JSONObject allowed = new JSONObject();
        allowed.put(RULE, EVERYDAY);
        allowed.put(TYPE, ALLOWED);
        JSONArray allowedTimes = new JSONArray();
        JSONObject allowedTime = new JSONObject();
        allowedTime.put(START, "09:00");
        allowedTime.put(END, "17:00");
        allowedTimes.put(allowedTime);
        allowed.put(TIMES, allowedTimes);

        JSONArray rules = new JSONArray();
        rules.put(allowed);
        for (Map.Entry<Timeslot, Boolean> timeslot : availabilityMap.entrySet()) {
            if (!timeslot.getValue()) {
                JSONObject rule = blockBuilder(timeslot);
                rules.put(rule);
            }
        }
        return rules;
    }

    /**
     * Build the required JSONArray format for Slotify for a blocked off timeslot.
     * @param timeslot the Map.Entry variable for the blocked timeslot.
     * @return the blocked timeslot in JSONArray format.
     */
    private JSONObject blockBuilder(Map.Entry<Timeslot, Boolean> timeslot) {
        JSONObject blockedTimeslot = new JSONObject();
        blockedTimeslot.put(DAY, timeslot.getKey().dayName().toLowerCase());
        blockedTimeslot.put(TYPE, BLOCKED);
        blockedTimeslot.put(RULE, DAYTIME);

        JSONArray blockedTimes = new JSONArray();
        JSONObject blockedTime = new JSONObject();
        if (timeslot.getKey().getDay() == SINGLE_DIGIT_9) {
            // Requires leading zero
            blockedTime.put(START, "09:00");
        }
        else {
            blockedTime.put(START, timeslot.getKey().getTime() + TIME_SUFFIX);
        }
        blockedTime.put(END, (timeslot.getKey().getTime() + 1) + TIME_SUFFIX);
        blockedTimes.put(blockedTime);
        blockedTimeslot.put(TIMES, blockedTimes);

        return blockedTimeslot;
    }

    /**
     * Creates the "event" parameter object in the required JSONArray format for Slotify.
     * @return the event in JSONObject format (same for all schedulers).
     */
    private JSONObject eventBuilder() {
        JSONObject event = new JSONObject();
        event.put(WHAT, WHAT_VALUE);
        JSONObject location = new JSONObject();
        location.put(TYPE, MEETING_TYPE);
        event.put(LOCATION, location);
        event.put(DESCRIPTION, MEETING_DESCRIPTION);
        event.put(START_AT, START_AT_DATE);
        return event;
    }

    /**
     * Creates the "event" parameter object in the required JSONArray format for Slotify.
     * @param jsonAvailability the availability in JSONArray format from Slotify.
     * @return the converted availability in Map format.
     */
    private Map<Timeslot, Boolean> convertAvailability(JSONArray jsonAvailability) {
        Map<Timeslot, Boolean> availabilityMap = new HashMap<>();
        for (int i = 0; i < jsonAvailability.length(); i++) {
            JSONObject timeslotJson = jsonAvailability.getJSONObject(i);
            // Parse the "start" times
            String start = timeslotJson.getString(START);
            // Convert the start time to a ZonedDateTime
            ZonedDateTime startTime = ZonedDateTime.parse(start);
            // Extract the weekday (1 = Monday, ..., 7 = Sunday)
            int day = startTime.getDayOfWeek().getValue();
            // Extract the hour in 24-hour format
            int hour = startTime.getHour();
            // Create a Timeslot object
            Timeslot timeslot = new Timeslot(day, hour);
            // Add the Timeslot to the map with a value of true (indicating availability)
            availabilityMap.put(timeslot, true);
        }
        return availabilityMap;
    }

    /**
     * Create a resource.
     * @param name the name of the user.
     * @param email the email of the user.
     * @return the resourceID (String) of the created resource.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public String createSlotifyResource(String name, String email) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(NAME, name);
        requestBody.put(EMAIL, email);
        requestBody.put(ROLE, ROLE_VALUE);
        requestBody.put(TIMEZONE, TIMEZONE_VALUE);
        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        final Request request = new Request.Builder()
                .url(String.format("%s/resources", API_URL))
                .method("POST", body)
                .addHeader(AUTHORIZATION_HEADER, API_TOKEN)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (!responseBody.getBoolean(SUCCESS)) {
                throw new RuntimeException(responseBody.getJSONObject(ERRORS).toString());
            }

            return responseBody.getJSONObject("data").getString(UUID);
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Delete a resource.
     * @param uuid the uuid of the resource to be deleted.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public void deleteSlotifyResource(String uuid) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/resources/%s", API_URL, uuid))
                .method("DELETE", null)
                .addHeader(AUTHORIZATION_HEADER, API_TOKEN)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (response.code() != SUCCESSFUL_DELETION_CODE) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getJSONObject(ERRORS).toString());
            }

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Create a scheduler.
     * @param availabilityMap the user's specified availability.
     * @param uuid the resourceID of the user whose availability is to be represented with this scheduler.
     * @return the schedulerID (String) of the created scheduler.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public String createSlotifyScheduler(Map<Timeslot, Boolean> availabilityMap, String uuid) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(GRAPH, GRAPH_TYPE);
        requestBody.put(COLOR, HEX_CODE);
        requestBody.put(SLOT_CAPACITY, CAPACITY_VALUE);
        requestBody.put(MODE, MODE_TYPE);
        requestBody.put(MIN_NOTICE, NOTICE_DURATION);
        requestBody.put(DURATION, DURATION_TIME);
        requestBody.put(BUFFER_TIME, BUFFER_DURATION);
        requestBody.put(BOOKING_WINDOW, WINDOW_DURATION);
        requestBody.put(MIN_CANCELLATION, CANCELLATION_DURATION);
        requestBody.put(NAME, SCHEDULER_NAME);
        requestBody.put(SLUG, SLUG_NAME);
        requestBody.put(UNIT_PRICE, PRICE_VALUE);
        requestBody.put(PERDAY_CAPACITY, CAPACITY_PER_DAY);
        requestBody.put(TIMEZONE, TIMEZONE_VALUE);

        requestBody.put(RULES, ruleBuilder(availabilityMap));

        JSONArray resources = new JSONArray();
        resources.put(uuid);
        requestBody.put(RESOURCES, resources);

        requestBody.put(EVENT, eventBuilder());

        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        final Request request = new Request.Builder()
                .url(String.format("%s/schedulers", API_URL))
                .method("POST", body)
                .addHeader(AUTHORIZATION_HEADER, API_TOKEN)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (!responseBody.getBoolean(SUCCESS)) {
                throw new RuntimeException(responseBody.getJSONObject(ERRORS).toString());
            }

            return responseBody.getJSONObject("data").getString(UUID);
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Delete a scheduler.
     * @param uuid the uuid of the scheduler to be deleted.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public void deleteSlotifyScheduler(String uuid) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/schedulers/%s", API_URL, uuid))
                .method("DELETE", null)
                .addHeader(AUTHORIZATION_HEADER, API_TOKEN)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (response.code() != SUCCESSFUL_DELETION_CODE) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getJSONObject(ERRORS).toString());
            }

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Fetch a user's availability based on their scheduler.
     * @param schedulerID the schedulerID for the scheduler representing the user's availability.
     * @return the resourceID (String) of the created resource.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public Map<Timeslot, Boolean> fetchAvailability(String schedulerID) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(SCHEDULER_ID, schedulerID);
        requestBody.put(OUTPUT_TIMEZONE, TIMEZONE_VALUE);
        requestBody.put(START, START_AT_DATE + "T09:00:00-04:00");
        requestBody.put(END, END_AT_DATE + "T17:00:00-04:00");
        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        final Request request = new Request.Builder()
                .url(String.format("%s/slots/available", API_URL))
                .method("POST", body)
                .addHeader(AUTHORIZATION_HEADER, API_TOKEN)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (!responseBody.getBoolean(SUCCESS)) {
                throw new RuntimeException(responseBody.getJSONObject(ERRORS).toString());
            }

            JSONArray jsonAvailability = responseBody.getJSONArray("data");
            return convertAvailability(jsonAvailability);
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public Map<User, List<Timeslot>> findMatches(User user, boolean expand) {
        Map<Timeslot, Boolean> availabilityMap = fetchAvailability(user.getSchedulerID());
        Map<User, List<Timeslot>> matchesByAvailability = new HashMap<>();
        // Get a list of all users (excluding the current user)
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User otherUser = entry.getValue();

            // Skip matching with the current user
            if (!otherUser.equals(user)) {
                Map<Timeslot, Boolean> otherUserAvailabilityMap = fetchAvailability(otherUser.getSchedulerID());
                List<Timeslot> sharedTimeslots = new ArrayList<>();

                // Compare the availability of the current user with this other user
                for (Map.Entry<Timeslot, Boolean> timeEntry : availabilityMap.entrySet()) {
                    Timeslot timeslot = timeEntry.getKey();
                    Boolean isAvailable = timeEntry.getValue();

                    // If both users are available at the same time for this Timeslot, add it to the match list
                    if (isAvailable && otherUserAvailabilityMap.getOrDefault(timeslot, false)) {
                        sharedTimeslots.add(timeslot);
                    }
                }

                // If there are any shared Timeslots, add them to the matches map
                if (!sharedTimeslots.isEmpty()) {
                    matchesByAvailability.put(otherUser, sharedTimeslots);
                }
            }
        }

        Map<User, List<Timeslot>> matches = new HashMap<>();
        for (Map.Entry<User, List<Timeslot>> entry : matchesByAvailability.entrySet()) {
            User otherUser = entry.getKey();
            List<Timeslot> timeslots = entry.getValue();
            if (expand) {
                // Match by program
                // TODO: PART OF ALEX'S USER STORY
            }
            else {
                // Match by courses
                // Convert the course lists to sets
                Set<Course> userCourseSet = new HashSet<>(user.getCourses());
                Set<Course> otherUserCourseSet = new HashSet<>(otherUser.getCourses());

                // Retain only the courses that are common to both sets
                userCourseSet.retainAll(otherUserCourseSet);

                if (!userCourseSet.isEmpty()) {
                    matches.put(otherUser, timeslots);
                }
            }
        }

        // Set the user's matches as per above criteria
        user.setMatches(matches);
        return matches;
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    public User getUserByUsername(String username) {
        if (!existsByName(username)) {
            throw new IllegalArgumentException("User not found.");
        }
        return users.get(username);
    }


}
