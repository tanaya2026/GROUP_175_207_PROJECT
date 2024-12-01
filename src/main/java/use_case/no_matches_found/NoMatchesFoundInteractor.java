package use_case.display_matches;

import java.util.List;
import java.util.Map;
import data_access.DataAccessObject;
import entity.User;
import entity.Timeslot;

/**
 * Interactor to handle the logic of finding matches by program.
 */

public class NoMatchesFoundInteractor {
    private final DataAccessObject dao;

    public NoMatchesFoundInteractor(DataAccessObject dao) {
        this.dao = dao;
    }

    /**
     * This method handles the use case of finding matches based on the program.
     * @param currentUser the logged-in user.
     */
    //
    public Map<User, List<Timeslot>> findMatchesBasedOnProgram(User currentUser) {
        // Interact with the data access layer to find matches based on program
        return dao.findMatches(currentUser, true);
    }
}
