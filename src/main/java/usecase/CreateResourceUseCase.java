package usecase;

import entity.User;
import org.json.JSONException;

import api.SlotifyDataBase;

/**
 * LogGradeUseCase class.
 */
public final class CreateResourceUseCase {
    private final SlotifyDataBase slotifyDataBase;

    public CreateResourceUseCase(SlotifyDataBase slotifyDataBase) {
        this.slotifyDataBase = slotifyDataBase;
    }

    /**
     * Create a resource.
     * @param user The user.
     * @throws JSONException if an error occurs.
     */
    public void createSlotifyResource(User user) throws JSONException {
        slotifyDataBase.createSlotifyResource(user);
    }
}
