package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileOutputData;

public class EditProfileViewModel {
    private String name;
    private String bio;
    private EditProfileState state;

    public EditProfileViewModel() {
        this.state = new EditProfileState();
    }

    public void update(EditProfileOutputData outputData) {
        this.name = outputData.getName();
        this.bio = outputData.getBio();
        this.state.setSuccessful(true);
    }

    public void setError(String errorMessage) {
        this.state.setErrorMessage(errorMessage);
        this.state.setSuccessful(false);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public EditProfileState getState() {
        return state;
    }
}
