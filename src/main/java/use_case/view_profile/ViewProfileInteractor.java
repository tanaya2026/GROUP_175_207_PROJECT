package use_case.view_profile;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final ViewProfileOutputBoundry viewprofilePresenter;

    public ViewProfileInteractor(ViewProfileOutputBoundry viewpageOutputBoundary) {
        this.viewprofilePresenter = viewpageOutputBoundary;
    }

    @Override
    public void execute(ViewProfileInputData viewprofileInputData) {
        final ViewprofileOutputData viewprofileOutputData = new ViewprofileOutputData();
        // Here first find the User, and send the information to presenter;
        // create OD with strings list of strings; instantoate Od
        viewprofilePresenter.prepareSuccessView(viewprofileOutputData);
    }
}
