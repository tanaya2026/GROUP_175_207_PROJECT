package use_case.edit_profile;

import entity.User;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final EditProfileDataAccessInterface dataAccessObject;
    private final EditProfileOutputBoundary outputBoundary;

    public EditProfileInteractor(EditProfileDataAccessInterface dataAccessObject, EditProfileOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @SuppressWarnings({"checkstyle:RightCurly", "checkstyle:CatchParameterName", "checkstyle:SuppressWarnings", "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity"})
    @Override
    public void editProfile(EditProfileInputData inputData) {
        try {
            // Test 1: User does not exist
            String currentUsername = dataAccessObject.getCurrentUsername();
            // Check if user exists
            if (!dataAccessObject.existsByName(currentUsername)) {
                throw new IllegalArgumentException("User does not exist.");
            }
            // Test 2: Update all fields
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
            // Test 3: Partial update
            if (inputData.getBio() != null && !inputData.getBio().equals(user.getBio())) {
                user.setBio(inputData.getBio());
            }
            // Program of study
            if (inputData.getProgram() != null && !inputData.getProgram().equals(user.getProgram())) {
                user.setProgram(inputData.getProgram());
            }
            // Courses
            if (inputData.getCourses() != null && !inputData.getCourses().equals(user.getCourses())) {
                user.getCourses().clear();
                user.addCourses(inputData.getCourses());
            }
            // Test 4: Scheduler update failure
            // Time availability for match
            if (inputData.getAvailability() != null) {
                // Delete an existing schedule since we cannot directly edit schedule so we need to create another relevant one
                dataAccessObject.deleteSlotifyScheduler(user.getSchedulerID());

                // Create a new schedule
                String newSchedulerID = dataAccessObject.createSlotifyScheduler(inputData.getAvailability(), user.getResourceID());
                user.setSchedulerID(newSchedulerID);
            }
            // Test 5: Save failure
            // Save the updated user
            dataAccessObject.save(user);

            // Create output data and present success
            EditProfileOutputData outputData = new EditProfileOutputData(
                    user.getName(),
                    user.getPassword(),
                    user.getBio(),
                    user.getEmail(),
                    user.getProgram(),
                    user.getCourses(),
                    dataAccessObject.fetchAvailability(user.getSchedulerID())
            );
            outputBoundary.presentSuccess(outputData);

        } catch (IllegalArgumentException e) {
            // Handle errors
            outputBoundary.presentError("Failed to update profile: " + e.getMessage());
        }
    }
}
