package use_case;

import data_access.DataAccessObject;
import entity.User;
import org.json.JSONException;

/**
 * CreateResourceUseCase class.
 */
public final class CreateResourceUseCase {
    private final DataAccessObject dataAccessObject;

    public CreateResourceUseCase(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    /**
     * Create a resource.
     * @param user The user.
     * @throws JSONException if an error occurs.
     */
    public void createSlotifyResource(User user) throws JSONException {
        dataAccessObject.createSlotifyResource(user);
    }
}
