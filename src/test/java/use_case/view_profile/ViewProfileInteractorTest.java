package use_case.view_profile;

import data_access.DataAccessObject;

import entity.Timeslot;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.home_page.HomepageInteractor;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ViewProfileInteractorTest {

    @Test
    void successTest() {
        // Check if matches variable is getting populated by the Users in the matches.

        ViewProfileInputData inputData = new ViewProfileInputData();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ViewProfileOutputBoundry successPresenter = new ViewProfileOutputBoundry() {
            @Override
            public void prepareSuccessView(ViewprofileOutputData data) {
                final DataAccessObject dataAccessObject = new DataAccessObject();
                User currentUser = dataAccessObject.getUserByUsername(dataAccessObject.getCurrentUsername());
                Map<User, List<Timeslot>> matches = currentUser.getMatches();
                assertEquals(currentUser.getMatches(), matches);
            }
        };
        ViewProfileInteractor interactor = new ViewProfileInteractor(successPresenter);
        interactor.execute(inputData);
    }


}

