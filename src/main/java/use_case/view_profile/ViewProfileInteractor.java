package use_case.view_profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data_access.DataAccessObject;
import entity.Timeslot;
import entity.User;

/**
 * Interactor for the ViewProfile Use Case.
 */

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final ViewProfileOutputBoundry viewprofilePresenter;

    public ViewProfileInteractor(ViewProfileOutputBoundry viewpageOutputBoundary) {
        this.viewprofilePresenter = viewpageOutputBoundary;
    }

    @Override
    public void execute(ViewProfileInputData viewprofileInputData) {
        // Extract the Users who the current User has matched with.
        final DataAccessObject dataAccessObject = new DataAccessObject();
        User currentUser = dataAccessObject.getUserByUsername(dataAccessObject.getCurrentUsername());
        Map<User, List<Timeslot>> matches = currentUser.getMatches();
        // Loop through the matches of the current User, and create a list of Users.
        Set<User> onlyusers = matches.keySet();
        List<User> users = null;
        for (User user : onlyusers) {
            users = new ArrayList<User>();
            users.add(user);
        }
        // Inject that Data into ViewProfileOutputData
        final ViewprofileOutputData viewprofileOutputData = new ViewprofileOutputData(users);
        // Pass the OutputData to the Presenter
        viewprofilePresenter.prepareSuccessView(viewprofileOutputData);

    }
}
