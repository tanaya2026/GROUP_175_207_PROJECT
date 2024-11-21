package use_case.edit_profile;

import api.SlotifyDataBase;
import api.UserDataBase;
import entity.User;

import java.util.Map;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final UserDataBase userDataBase;
    private final SlotifyDataBase slotifyDataBase;
    private final EditProfileOutputBoundary outputBoundary;

    public EditProfileInteractor(UserDataBase userDataBase, SlotifyDataBase slotifyDataBase, EditProfileOutputBoundary outputBoundary) {
        this.userDataBase = userDataBase;
        this.slotifyDataBase = slotifyDataBase;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void editProfile(EditProfileInputData inputData) {
        try {
            // Fetch user from database
            User user = userDataBase.getUserById(inputData.getUserId());

            // Update user information
            user.setName(inputData.getName());
            user.setBio(inputData.getBio());

            // Update availability in Slotify
            slotifyDataBase.updateAvailability(user.getSchedulerID(), inputData.getAvailability());

            // Save changes back to the database
            userDataBase.updateUser(user);

            // Create output data and present success
            EditProfileOutputData outputData = new EditProfileOutputData(user.getName(), user.getBio());
            outputBoundary.presentSuccess(outputData);

        } catch (Exception e) {
            // Present error message to the output boundary
            outputBoundary.presentError("Failed to update profile: " + e.getMessage());
        }
    }
}
