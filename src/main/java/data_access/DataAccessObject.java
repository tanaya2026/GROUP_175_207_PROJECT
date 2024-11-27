package data_access;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;
import entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.display_matches.DisplayMatchesDataAccessInterface;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * UserDB class.
 */
public class DataAccessObject implements EditProfileDataAccessInterface,
        DisplayMatchesDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SignupUserDataAccessInterface,
        SlotifyServiceInterface {

    private final Map<String, User> users = DBBuilder.loadUsers();
    private final List<Course> courses = DBBuilder.loadCourses();

    private String currentUsername;

    @Override
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
                .method(POST, body)
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

            return responseBody.getJSONObject(DATA).getString(UUID);
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public void deleteSlotifyResource(String uuid) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/resources/%s", API_URL, uuid))
                .method(DELETE, null)
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

    @Override
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

        // Defines the user's availability
        requestBody.put(RULES, ruleBuilder(availabilityMap));

        JSONArray resources = new JSONArray();
        resources.put(uuid);
        requestBody.put(RESOURCES, resources);

        // Not used, but required by Slotify API
        requestBody.put(EVENT, eventBuilder());

        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        final Request request = new Request.Builder()
                .url(String.format("%s/schedulers", API_URL))
                .method(POST, body)
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

            return responseBody.getJSONObject(DATA).getString(UUID);
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public void deleteSlotifyScheduler(String uuid) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/schedulers/%s", API_URL, uuid))
                .method(DELETE, null)
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

    @Override
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
                .method(POST, body)
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

            JSONArray jsonAvailability = responseBody.getJSONArray(DATA);
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

    // DataAccessInterface implementations

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User getUserByUsername(String username) {
        if (!existsByName(username)) {
            throw new IllegalArgumentException("User not found.");
        }
        return users.get(username);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    // The below methods are helpers for Slotify API calls

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
        if (timeslot.getKey().getTime() == SINGLE_DIGIT_9) {
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
}
