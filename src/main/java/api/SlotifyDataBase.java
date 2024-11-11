package api;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * UserDB class.
 */
public class SlotifyDataBase {
    // Defining some constants.
    private static final String API_URL = "https://api.slotify.ca/v1";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String STATUS_CODE = "status_code";
    private static final String SUCCESS = "success";
    private static final String MESSAGE = "message";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String TIMEZONE = "timezone";
    private static final String TOKEN = "ODE4NzViZWMtYWZhOS00ZGY4LWEwOTktZjNkYmZlNjFlNWZi";
    // load getPassword() from env variable.
    private static final int SUCCESS_CODE = 200;

    public static String getAPIToken() {
        return System.getenv(TOKEN);
    }

//    @Override
//    public void createSlotifyResource(User user) {
//
//        // Build the request to create a new resource.
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final Request request = new Request.Builder()
//                .url(String.format("%s/resources", API_URL))
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        // Hint: look at the API documentation to understand what the response looks like.
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
//                final JSONObject grade = responseBody.getJSONObject(GRADE);
//                return Grade.builder()
//                        .username(grade.getString("username"))
//                        .course(grade.getString("course"))
//                        .grade(grade.getInt(GRADE))
//                        .build();
//            }
//            else {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }

//    @Override
//    public Grade[] getGrades(String username) {
//
//        // Build the request to get all grades for a user.
//        // Note: The API requires the username to be passed as a header.
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final Request request = new Request.Builder()
//                .url(String.format("%s/grade?username=%s", API_URL, username))
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        // Hint: look at the API documentation to understand what the response looks like.
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
//                final JSONArray grades = responseBody.getJSONArray("grades");
//                final Grade[] result = new Grade[grades.length()];
//                for (int i = 0; i < grades.length(); i++) {
//                    final JSONObject grade = grades.getJSONObject(i);
//                    result[i] = Grade.builder()
//                            .username(grade.getString("username"))
//                            .course(grade.getString("course"))
//                            .grade(grade.getInt(GRADE))
//                            .build();
//                }
//                return result;
//            }
//            else {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }

    // @Override
    /**
     * Create a resource.
     * @param user the user.
     * @throws JSONException if unsuccessful. ?
     * @throws RuntimeException if unsuccessful ?
     */
    public void createSlotifyResource(User user) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(NAME, user.getName());
        requestBody.put(EMAIL, user.getEmail());
        requestBody.put(ROLE, "member");
        requestBody.put(TIMEZONE, "America/Toronto");
        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        final Request request = new Request.Builder()
                .url(String.format("%s/resources", API_URL))
                .method("POST", body)
                .addHeader(TOKEN, getAPIToken())
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (!responseBody.getBoolean(SUCCESS)) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

//    @Override
//    public Availability formTeam(String name) throws JSONException {
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
//        final JSONObject requestBody = new JSONObject();
//        requestBody.put(NAME, name);
//        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        final Request request = new Request.Builder()
//                .url(String.format("%s/team", API_URL))
//                .method("POST", body)
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
//                final JSONObject team = responseBody.getJSONObject("team");
//                final JSONArray membersArray = team.getJSONArray("members");
//                final String[] members = new String[membersArray.length()];
//                for (int i = 0; i < membersArray.length(); i++) {
//                    members[i] = membersArray.getString(i);
//                }
//
//                return Availability.builder()
//                        .name(team.getString(NAME))
//                        .members(members)
//                        .build();
//            }
//            else {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }

//    @Override
//    public Availability joinTeam(String name) throws JSONException {
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
//        final JSONObject requestBody = new JSONObject();
//        requestBody.put(NAME, name);
//        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        final Request request = new Request.Builder()
//                .url(String.format("%s/team", API_URL))
//                .method("PUT", body)
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
//                return null;
//            }
//            else {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }

//    @Override
//    public void leaveTeam() throws JSONException {
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
//        final JSONObject requestBody = new JSONObject();
//        final RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        final Request request = new Request.Builder()
//                .url(String.format("%s/leaveTeam", API_URL))
//                .method("PUT", body)
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) != SUCCESS_CODE) {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }

//    @Override
//    //       Hint: Read the Grade API documentation for getMyTeam (link below) and refer to the above similar
//    //             methods to help you write this code (copy-and-paste + edit as needed).
//    //             https://www.postman.com/cloudy-astronaut-813156/csc207-grade-apis-demo/folder/isr2ymn/get-my-team
//    public Availability getMyTeam() {
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        final Request request = new Request.Builder()
//                .url(String.format("%s/team", API_URL))
//                .method("GET", null)
//                .addHeader(TOKEN, getAPIToken())
//                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
//                .build();
//
//        try {
//            final Response response = client.newCall(request).execute();
//            final JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
//                final JSONObject team = responseBody.getJSONObject("team");
//                final JSONArray membersArray = team.getJSONArray("members");
//                final String[] members = new String[membersArray.length()];
//                for (int i = 0; i < membersArray.length(); i++) {
//                    members[i] = membersArray.getString(i);
//                }
//
//                return Availability.builder()
//                        .name(team.getString(NAME))
//                        .members(members)
//                        .build();
//            }
//            else {
//                throw new RuntimeException(responseBody.getString(MESSAGE));
//            }
//        }
//        catch (IOException | JSONException event) {
//            throw new RuntimeException(event);
//        }
//    }
}
