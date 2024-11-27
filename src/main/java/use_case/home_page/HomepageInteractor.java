package use_case.home_page;

import entity.User;
import use_case.signup.SignupInputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class HomepageInteractor implements HomepageInputBoundary{
    private final HomepageOutputBoundary homepagePresenter;

    @Override
    public void execute(HomepageInputData homepageInputData) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData();
        homepagePresenter.prepareSuccessView(homepageOutputData);
    }

}
