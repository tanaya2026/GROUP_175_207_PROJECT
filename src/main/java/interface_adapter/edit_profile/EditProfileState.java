package interface_adapter.edit_profile;

public class EditProfileState {
    private boolean isSuccessful;
    private String errorMessage;

    public EditProfileState() {
        this.isSuccessful = false;
        this.errorMessage = null;
    }

    // Getters and setters
    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
