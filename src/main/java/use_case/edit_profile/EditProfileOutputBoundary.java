package use_case.edit_profile;

public interface EditProfileOutputBoundary {
    void presentSuccess(EditProfileOutputData outputData);

    void presentError(String errorMessage);
}
