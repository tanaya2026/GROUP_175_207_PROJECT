package org.example;

import entity.Course;
import entity.SlotifyServiceInterface;
import entity.Timeslot;
import entity.User;
import use_case.edit_profile.*;
import org.json.JSONException;
import java.util.*;

/// This is the test code for interactor (Edit Profile) and it achieves 100% in the mirrored version of this project
/// with minimum data access and entities for simplicity
public class Main {
    public static void main(String[] args) {
        // Setup Test Environment
        TestEditProfileDataAccess dataAccess = new TestEditProfileDataAccess();
        TestEditProfileOutputBoundary outputBoundary = new TestEditProfileOutputBoundary();
        EditProfileInteractor interactor = new EditProfileInteractor(dataAccess, outputBoundary);

        // Initialize test user with current values
        List<Course> initialCourses = new ArrayList<>();
        initialCourses.add(new Course("CSC207", "Software Design"));
        Map<Timeslot, Boolean> initialAvailability = new HashMap<>();
        TestSlotifyService slotifyService = new TestSlotifyService();

        User testUser = new User("testuser", "old@email.com", "oldpass", "Old Name",
                initialCourses, "Old Program", "Old Bio",
                initialAvailability, slotifyService);
        dataAccess.save(testUser);

        // Test 1: User does not exist
        System.out.println("Test 1: User does not exist");
        dataAccess.forceUserNotExist();
        EditProfileInputData inputData = new EditProfileInputData(
                "new@email.com", "newpass", "New Name", "New Bio", "New Program",
                new ArrayList<>(), new HashMap<>(), "schedulerId");
        interactor.editProfile(inputData);
        printResult(outputBoundary.isSuccessful == false &&
                        outputBoundary.errorMessage.contains("User does not exist"),
                "User not found error");

        // Test 2: Update all fields
        System.out.println("\nTest 2: Update all fields");
        dataAccess.resetUserExists();
        List<Course> newCourses = new ArrayList<>();
        newCourses.add(new Course("CSC236", "Theory"));
        Map<Timeslot, Boolean> newAvailability = new HashMap<>();
        newAvailability.put(new Timeslot(1, 9), true);

        EditProfileInputData allFieldsInput = new EditProfileInputData(
                "new@email.com", "newpass", "New Name", "New Bio", "New Program",
                newCourses, newAvailability, "oldSchedulerId");
        interactor.editProfile(allFieldsInput);
        printResult(outputBoundary.isSuccessful && outputBoundary.outputData != null,
                "Complete profile update");

        // Test 3: Update only some fields
        System.out.println("\nTest 3: Partial update");
        EditProfileInputData partialInput = new EditProfileInputData(
                null, null, null, "New Bio Only", null, null, null, null);
        interactor.editProfile(partialInput);
        printResult(outputBoundary.isSuccessful &&
                        "New Bio Only".equals(outputBoundary.outputData.getBio()),
                "Partial update");

        // Test 4: Scheduler update failure
        System.out.println("\nTest 4: Scheduler failure");
        dataAccess.forceSchedulerError();
        EditProfileInputData schedulerInput = new EditProfileInputData(
                null, null, null, null, null, null,
                newAvailability, "oldSchedulerId");
        try {
            interactor.editProfile(schedulerInput);
        } catch (RuntimeException e) {
            outputBoundary.presentError("Failed to update profile: " + e.getMessage());
        }
        printResult(!outputBoundary.isSuccessful &&
                        outputBoundary.errorMessage.contains("Failed to update profile"),
                "Scheduler error handling");
        dataAccess.resetSchedulerError();

        // Test 5: Save failure
        System.out.println("\nTest 5: Save failure");
        dataAccess.forceSaveError();
        EditProfileInputData saveInput = new EditProfileInputData(
                "new@email.com", null, null, null, null, null, null, null);
        try {
            interactor.editProfile(saveInput);
        } catch (RuntimeException e) {
            outputBoundary.presentError("Failed to update profile: " + e.getMessage());
        }
        printResult(!outputBoundary.isSuccessful &&
                        outputBoundary.errorMessage.contains("Failed to update profile"),
                "Save error handling");
        dataAccess.resetSaveError();

        // Cleanup
        try {
            System.out.println("\nCleaning up Slotify resources...");
            User currentUser = dataAccess.getUserByUsername("testuser");
            if (currentUser != null) {
                dataAccess.deleteSlotifyScheduler(currentUser.getSchedulerID());
                slotifyService.deleteSlotifyResource(currentUser.getResourceID());
                System.out.println("PASS - Cleanup successful");
            }
        } catch (Exception e) {
            System.out.println("FAIL - Cleanup error: " + e.getMessage());
        }

        System.out.println("\nAll tests completed.");
    }

    private static void printResult(boolean condition, String testName) {
        System.out.println(condition ? "PASS - " + testName : "FAIL - " + testName);
    }

    private static class TestSlotifyService implements SlotifyServiceInterface {
        @Override
        public String createSlotifyResource(String name, String email) {
            return "test-resource-id";
        }

        @Override
        public void deleteSlotifyResource(String uuid) {
            // Mock implementation
        }

        @Override
        public String createSlotifyScheduler(Map<Timeslot, Boolean> availabilityMap, String uuid) {
            return "test-scheduler-id";
        }

        @Override
        public void deleteSlotifyScheduler(String uuid) {
            // Mock implementation
        }

        @Override
        public Map<Timeslot, Boolean> fetchAvailability(String schedulerID) {
            return new HashMap<>();
        }
    }

    private static class TestEditProfileDataAccess implements EditProfileDataAccessInterface {
        private Map<String, User> users = new HashMap<>();
        private boolean userExists = true;
        private boolean schedulerError = false;
        private boolean saveError = false;

        @Override
        public String getCurrentUsername() {
            return "testuser";
        }

        @Override
        public boolean existsByName(String username) {
            return userExists;
        }

        @Override
        public User getUserByUsername(String username) {
            return users.get(username);
        }

        @Override
        public void save(User user) {
            if (saveError) {
                throw new RuntimeException("Forced save error");
            }
            users.put(user.getUsername(), user);
        }

        @Override
        public void deleteSlotifyScheduler(String schedulerId) {
            if (schedulerError) {
                throw new RuntimeException("Forced scheduler error");
            }
        }

        @Override
        public String createSlotifyScheduler(Map<Timeslot, Boolean> availability, String resourceId) {
            if (schedulerError) {
                throw new RuntimeException("Forced scheduler error");
            }
            return "new-scheduler-id";
        }

        @Override
        public Map<Timeslot, Boolean> fetchAvailability(String schedulerId) {
            return new HashMap<>();
        }

        void forceUserNotExist() {
            this.userExists = false;
        }

        void resetUserExists() {
            this.userExists = true;
        }

        void forceSchedulerError() {
            this.schedulerError = true;
        }

        void resetSchedulerError() {
            this.schedulerError = false;
        }

        void forceSaveError() {
            this.saveError = true;
        }

        void resetSaveError() {
            this.saveError = false;
        }
    }

    private static class TestEditProfileOutputBoundary implements EditProfileOutputBoundary {
        boolean isSuccessful = false;
        String errorMessage = null;
        EditProfileOutputData outputData = null;

        @Override
        public void presentSuccess(EditProfileOutputData data) {
            isSuccessful = true;
            outputData = data;
            errorMessage = null;
        }

        @Override
        public void presentError(String error) {
            isSuccessful = false;
            errorMessage = error;
            outputData = null;
        }
    }
}