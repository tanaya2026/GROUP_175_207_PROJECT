package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Timeslot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * UserDB class.
 */
public class SlotifyDataBase {
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
    private static final String START_AT_DATE = "2024-11-17";

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
     * Convert the availabilityMap to the required JSONArray format for Slotify.
     * @param availabilityMap the user's availability in Map format.
     * @return the converted availabilityMap in JSONArray format.
     */
    private static JSONArray ruleBuilder(Map<Timeslot, Boolean> availabilityMap) {
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
    private static JSONObject blockBuilder(Map.Entry<Timeslot, Boolean> timeslot) {
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
    private static JSONObject eventBuilder() {
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

}
