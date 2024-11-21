package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.edit_profile.EditProfileOutputData;

public class EditProfilePresenter implements EditProfileOutputBoundary {
    private final EditProfileViewModel viewModel;

    public EditProfilePresenter(EditProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSuccess(EditProfileOutputData outputData) {
        viewModel.update(outputData);
    }

    @Override
    public void presentError(String errorMessage) {
        viewModel.setError(errorMessage);
    }

    public EditProfileViewModel getViewModel() {
        return viewModel;
    }
}
