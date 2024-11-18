package interface_adapter;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

import java.util.Map;

public class EditProfileController {
    private final EditProfileInputBoundary interactor;

    public EditProfileController(EditProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleEditProfile(String name, String bio, Map<Timeslot, Boolean> availability) {
        // Create InputData and call the interactor
        EditProfileInputData inputData = new EditProfileInputData(name, bio, availability);
        interactor.editProfile(inputData);
    }
}
