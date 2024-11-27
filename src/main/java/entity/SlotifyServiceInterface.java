package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.JSONException;

/**
 * Interface for calling the Slotify API.
 */
public interface SlotifyServiceInterface {

    // Constants

    // General Slotify constants
    String API_URL = "https://api.slotify.ca/v1";
    String CONTENT_TYPE = "Content-Type";
    String APPLICATION_JSON = "application/json";
    String ACCEPT = "Accept";
    String SUCCESS = "success";
    String ERRORS = "errors";
    String NAME = "name";
    String EMAIL = "email";
    String ROLE = "role";
    String ROLE_VALUE = "member";
    String TIMEZONE = "timezone";
    String TIMEZONE_VALUE = "America/Toronto";
    String AUTHORIZATION_HEADER = "Authorization";
    String API_TOKEN = "Bearer ODE4NzViZWMtYWZhOS00ZGY4LWEwOTktZjNkYmZlNjFlNWZi";
    String UUID = "uuid";
    String POST = "POST";
    String GET = "GET";
    String PUT = "PUT";
    String DELETE = "DELETE";
    String DATA = "data";
    int SUCCESSFUL_DELETION_CODE = 204;

    // Scheduler creation constants
    String GRAPH = "graph";
    String GRAPH_TYPE = "instant";
    String COLOR = "color";
    String HEX_CODE = "#9e0fd8";
    String SLOT_CAPACITY = "slot_capacity";
    int CAPACITY_VALUE = 8;
    String MODE = "mode";
    String MODE_TYPE = "mutual";
    String MIN_NOTICE = "min_notice";
    String NOTICE_DURATION = "1 days";
    String DURATION = "duration";
    String DURATION_TIME = "1 hours";
    String BUFFER_TIME = "buffer_time";
    String BUFFER_DURATION = "0 minutes";
    String BOOKING_WINDOW = "booking_window";
    String WINDOW_DURATION = "4 weeks";
    String MIN_CANCELLATION = "min_cancellation";
    String CANCELLATION_DURATION = "1 days";
    String SCHEDULER_NAME = "Scheduler" + timedate();
    String SLUG = "slug";
    String SLUG_NAME = "newScheduler" + timedate();
    String UNIT_PRICE = "unit_price";
    int PRICE_VALUE = 0;
    String PERDAY_CAPACITY = "perday_capacity";
    int CAPACITY_PER_DAY = 500;

    // Rule constants (used in scheduler creation to define availability)
    String RULES = "rules";
    String RULE = "rule";
    String EVERYDAY = "hours";
    String TYPE = "type";
    String ALLOWED = "allowed";
    String TIMES = "times";
    String START = "start";
    String END = "end";
    String DAY = "day";
    String DAYTIME = "daytime";
    String BLOCKED = "blocked";
    String TIME_SUFFIX = ":00";
    int SINGLE_DIGIT_9 = 9;

    String RESOURCES = "resources";

    // Event constants (used in scheduler creation - not used but required by API)
    String EVENT = "event";
    String WHAT = "what";
    String WHAT_VALUE = "Meeting with study buddy";
    String LOCATION = "location";
    String MEETING_TYPE = "google_meet";
    String DESCRIPTION = "description";
    String MEETING_DESCRIPTION = "Let's do some studying!";
    String START_AT = "start_at";
    String START_AT_DATE = "2024-12-08";

    String SCHEDULER_ID = "scheduler_id";
    String OUTPUT_TIMEZONE = "output_timezone";
    String END_AT_DATE = "2024-12-14";

    /**
     * Create a date time string to better identify the scheduler name. Scheduler names must be unique.
     * @return string value of today's date and time in the specified format.
     */
    private static String timedate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Create a resource.
     * @param name the name of the user.
     * @param email the email of the user.
     * @return the resourceID (String) of the created resource.
     * @throws JSONException if there is a critical error.
     * @throws RuntimeException if call to the API is unsuccessful.
     */
    String createSlotifyResource(String name, String email) throws JSONException;

    /**
     * Delete a resource.
     * @param uuid the uuid of the resource to be deleted.
     * @throws JSONException if there is a critical error.
     * @throws RuntimeException if call to the API is unsuccessful.
     */
    void deleteSlotifyResource(String uuid) throws JSONException;

    /**
     * Create a scheduler.
     * @param availabilityMap the user's specified availability.
     * @param uuid the resourceID of the user whose availability is to be represented with this scheduler.
     * @return the schedulerID (String) of the created scheduler.
     * @throws JSONException if there is a critical error.
     * @throws RuntimeException if call to the API is unsuccessful.
     */
    String createSlotifyScheduler(Map<Timeslot, Boolean> availabilityMap, String uuid) throws JSONException;

    /**
     * Delete a scheduler.
     * @param uuid the uuid of the scheduler to be deleted.
     * @throws JSONException if there is a critical error.
     * @throws RuntimeException if call to the API is unsuccessful.
     */
    void deleteSlotifyScheduler(String uuid) throws JSONException;

    /**
     * Fetch a user's availability based on their scheduler.
     * @param schedulerID the schedulerID for the scheduler representing the user's availability.
     * @return the resourceID (String) of the created resource.
     * @throws JSONException if there is a critical error.
     * @throws RuntimeException if call to the API is unsuccessful.
     */
    Map<Timeslot, Boolean> fetchAvailability(String schedulerID) throws JSONException;
}
