package interface_adapter.view_profile;

import interface_adapter.ViewManagerModel;
import use_case.view_profile.ViewProfileOutputBoundry;
import use_case.view_profile.ViewprofileOutputData;

/**
 * The Presenter for the View Profile Use Case.
 */

public class ViewProfilePresenter implements ViewProfileOutputBoundry {

    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewProfilePresenter(ViewManagerModel viewManagerModel,
                             ViewProfileViewModel viewProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewProfileViewModel = viewProfileViewModel;

    }

    @Override
    public void prepareSuccessView(ViewprofileOutputData response) {
        // Update ViewModel to contain new Information.

        final ViewProfileState viewProfileState = viewProfileViewModel.getState();
        viewProfileState.setUsername(response.getUsername());
        viewProfileState.setPassword(response.getPassword());
        viewProfileState.setEmail(response.getEmail());
        viewProfileState.setName(response.getName());
        viewProfileState.setAvaliability(response.getAvaliability());
        viewProfileState.setCourses(response.getCourses());
        viewProfileState.setBio(response.getBio());
        viewProfileState.setProgram(response.getProgram());

        this.viewProfileViewModel.setState(viewProfileState);
        viewProfileViewModel.firePropertyChanged();

        viewManagerModel.setState(viewProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
