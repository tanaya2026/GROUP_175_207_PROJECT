package use_case.edit_profile;

import data_access.DataAccessObject;
import entity.User;
import entity.Timeslot;

import java.util.Map;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final EditProfileDataAccessInterface dataAccessObject;
    private final EditProfileOutputBoundary outputBoundary;

    public EditProfileInteractor(EditProfileDataAccessInterface dataAccessObject, EditProfileOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void editProfile(EditProfileInputData inputData) {
        try {
            String currentUsername = dataAccessObject.getCurrentUsername();
            // Check if user exists
            if (!dataAccessObject.existsByName(currentUsername)) {
                throw new IllegalArgumentException("User does not exist.");
            }

            // Fetch the user
            User user = dataAccessObject.getUserByUsername(currentUsername);

            // Check and update personal details
            // Email
            if (inputData.getEmail() != null && !inputData.getEmail().equals(user.getEmail())) {
                user.setEmail(inputData.getEmail());
            }
            // Name
            if (inputData.getName() != null && !inputData.getName().equals(user.getName())) {
                user.setName(inputData.getName());
            }
            // Password
            if (inputData.getPassword() != null && !inputData.getPassword().equals(user.getPassword())) {
                user.setPassword(inputData.getPassword());
            }
            // Bio
            if (inputData.getBio() != null) user.setBio(inputData.getBio());
            // Program of study
            if (inputData.getProgram() != null) user.setProgram(inputData.getProgram());
            // Courses
            if (inputData.getCourses() != null) {
                user.getCourses().clear();
                user.addCourses(inputData.getCourses());
            }

            // Time availability for match
            if (inputData.getAvailability() != null) {
                // Delete an existing schedule since we cannot directly edit schedule so we need to create another relevant one
                dataAccessObject.deleteSlotifyScheduler(user.getSchedulerID());

                // Create a new schedule
                String newSchedulerID = dataAccessObject.createSlotifyScheduler(inputData.getAvailability(), user.getResourceID());
                user.setSchedulerID(newSchedulerID);
            }

            // Save the updated user
            dataAccessObject.save(user);

            // Create output data and present success
            EditProfileOutputData outputData = new EditProfileOutputData(
                    user.getName(),
                    user.getPassword(),
                    user.getBio(),
                    user.getEmail(),
                    user.getProgram(),
                    user.getCourses(), // Return the full Course objects for now
                    dataAccessObject.fetchAvailability(user.getSchedulerID()) // Fetch updated availability
            );
            outputBoundary.presentSuccess(outputData);

        } catch (Exception e) {
            // Handle errors
            outputBoundary.presentError("Failed to update profile: " + e.getMessage());
        }
    }
}
