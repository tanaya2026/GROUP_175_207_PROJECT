package use_case.display_matches;

import entity.User;

/**
 * The input data for the Display Matches Use Case.
 */
public class DisplayMatchesInputData {

    private final User user;
    private final boolean expand;

    public DisplayMatchesInputData(User user, boolean expand) {
        this.user = user;
        this.expand = expand;
    }

    User getUser() {
        return user;
    }

    boolean getExpand() {
        return expand;
    }

}
