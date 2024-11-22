package use_case;

import data_access.DataAccessObject;
import entity.Timeslot;
import org.json.JSONException;

import java.util.Map;

/**
 * CreateSchedulerUseCase class.
 */
public final class CreateSchedulerUseCase {
    private final DataAccessObject dataAccessObject;

    public CreateSchedulerUseCase(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    /**
     * Create a scheduler.
     * @param availabilityMap The Map of the user's specified availability.
     * @throws JSONException if an error occurs.
     */
    public void createSlotifyScheduler(Map<Timeslot, Boolean> availabilityMap) throws JSONException {
        dataAccessObject.createSlotifyScheduler(availabilityMap);
    }
}
