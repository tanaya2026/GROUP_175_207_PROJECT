package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;
import entity.Course;
import entity.Timeslot;

import java.util.List;
import java.util.Map;

public class EditProfileController {
    private final EditProfileInputBoundary interactor;

    public EditProfileController(EditProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleEditProfile(String email, String password, String name, String bio,
                                  String program, List<Course> courses, Map<Timeslot, Boolean> availability,
                                  String schedulerID) {
        // Create InputData and call the interactor
        EditProfileInputData inputData = new EditProfileInputData(
                email, password, name, bio, program, courses, availability, schedulerID
        );
        interactor.editProfile(inputData);
    }
}
