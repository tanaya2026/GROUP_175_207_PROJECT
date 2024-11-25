package use_case.edit_profile;

import data_access.DataAccessObject;
import entity.User;
import entity.Timeslot;

import java.util.Map;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final DataAccessObject dataAccessObject;
    private final EditProfileOutputBoundary outputBoundary;

    public EditProfileInteractor(DataAccessObject dataAccessObject, EditProfileOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void editProfile(String username, EditProfileInputData inputData) {
        try {
            // Check if user exists
            if (!dataAccessObject.existsByName(username)) {
                throw new IllegalArgumentException("User does not exist.");
            }

            // Fetch the user
            User user = dataAccessObject.getUserByUsername(username);

            boolean nameOrEmailUpdated = false;

            // Check and update personal details
            if (inputData.getEmail() != null && !inputData.getEmail().equals(user.getEmail())) {
                user.setEmail(inputData.getEmail());
                nameOrEmailUpdated = true;
            }
            if (inputData.getName() != null && !inputData.getName().equals(user.getName())) {
                user.setName(inputData.getName());
                nameOrEmailUpdated = true;
            }
            if (inputData.getPassword() != null && !inputData.getPassword().equals(user.getPassword())) {
                user.setPassword(inputData.getPassword());
            }
            if (inputData.getBio() != null) user.setBio(inputData.getBio());
            if (inputData.getProgram() != null) user.setProgram(inputData.getProgram());
            if (inputData.getCourses() != null) {
                user.getCourses().clear();
                user.addCourses(inputData.getCourses());
            }

            // Handle name/email update that affects resourceID
            if (nameOrEmailUpdated) {
                // Fetch existing availability as a fallback
                Map<Timeslot, Boolean> currentAvailability = dataAccessObject.fetchAvailability(user.getSchedulerID());

                // Delete the old resource
                dataAccessObject.deleteSlotifyResource(user.getResourceID());

                // Create a new resource and update resourceID
                String newResourceID = dataAccessObject.createSlotifyResource(user.getName(), user.getEmail());
                user.setResourceID(newResourceID);

                // Create a new schedule
                Map<Timeslot, Boolean> newAvailability;
                if (inputData.getAvailability() != null) {
                    // Use the provided new availability
                    newAvailability = inputData.getAvailability();
                } else {
                    // Fallback to the current availability
                    newAvailability = currentAvailability;
                }

                String newSchedulerID = dataAccessObject.createSlotifyScheduler(newAvailability, newResourceID);
                user.setSchedulerID(newSchedulerID);
            } else if (inputData.getAvailability() != null) {
                // Handle schedule updates independently if name/email is unchanged
                dataAccessObject.deleteSlotifyScheduler(user.getSchedulerID());

                // Create a new schedule
                String newSchedulerID = dataAccessObject.createSlotifyScheduler(inputData.getAvailability(), user.getResourceID());
                user.setSchedulerID(newSchedulerID);
            }

            // Save the updated user
            dataAccessObject.save(user);

            // Create output data and present success
            // Create output data and present success
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
