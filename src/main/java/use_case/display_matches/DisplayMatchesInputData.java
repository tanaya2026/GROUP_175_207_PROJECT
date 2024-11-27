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

    /**
     * Returns the current user requesting to display their matches.
     * @return this User object.
     */
    User getUser() {
        return user;
    }

    /**
     * Returns the boolean indicating whether to match based on courses or program.
     * @return this boolean "expand".
     */
    boolean getExpand() {
        return expand;
    }

}
