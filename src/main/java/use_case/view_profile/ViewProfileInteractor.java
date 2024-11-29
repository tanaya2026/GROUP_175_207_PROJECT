package use_case.view_profile;

import use_case.home_page.HomepageInputBoundary;
import use_case.home_page.HomepageInputData;
import use_case.home_page.HomepageOutputBoundary;
import use_case.home_page.HomepageOutputData;

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
