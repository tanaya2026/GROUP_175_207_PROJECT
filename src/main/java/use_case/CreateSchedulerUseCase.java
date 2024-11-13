package use_case;

import entity.Timeslot;
import org.json.JSONException;

import api.SlotifyDataBase;

import java.util.Map;

/**
 * CreateSchedulerUseCase class.
 */
public final class CreateSchedulerUseCase {
    private final SlotifyDataBase slotifyDataBase;

    public CreateSchedulerUseCase(SlotifyDataBase slotifyDataBase) {
        this.slotifyDataBase = slotifyDataBase;
    }

    /**
     * Create a scheduler.
     * @param availabilityMap The Map of the user's specified availability.
     * @throws JSONException if an error occurs.
     */
    public void createSlotifyScheduler(Map<Timeslot, Boolean> availabilityMap) throws JSONException {
        slotifyDataBase.createSlotifyScheduler(availabilityMap);
    }
}
