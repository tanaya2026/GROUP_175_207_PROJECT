package use_case.edit_profile;

import api.SlotifyDataBase;
import api.UserDataBase;
import entity.User;

import java.util.Map;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final UserDataBase userDataBase;
    private final SlotifyDataBase slotifyDataBase;

    public EditProfileInteractor(UserDataBase userDataBase, SlotifyDataBase slotifyDataBase) {
        this.userDataBase = userDataBase;
        this.slotifyDataBase = slotifyDataBase;
    }

    @Override
    public void editProfile(EditProfileInputData inputData) {
        // Fetch user from database
        User user = userDataBase.getUserById(inputData.getUserId());

        // Update user information
        user.setName(inputData.getName());
        user.setBio(inputData.getBio());

        // Update availability in Slotify
        slotifyDataBase.updateAvailability(user.getSchedulerID(), inputData.getAvailability());

        // Save changes back to the database
        userDataBase.updateUser(user);
    }
}
