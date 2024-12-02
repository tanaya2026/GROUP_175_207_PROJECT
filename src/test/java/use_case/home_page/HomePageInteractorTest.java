package use_case.home_page;

import data_access.DataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

public class HomePageInteractorTest {

    @Test
    void successTest() {
        HomepageInputData inputData = new HomepageInputData();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomepageOutputBoundary successPresenter = new HomepageOutputBoundary() {
            @Override
            public void prepareSuccessView(HomepageOutputData outputData) {
                assert viewManagerModel.getViewName() == "HomePage";
            }
        };
        HomepageInteractor interactor = new HomepageInteractor(successPresenter);
        interactor.execute(inputData);

    }
}
