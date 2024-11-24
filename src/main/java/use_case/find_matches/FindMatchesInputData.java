package use_case.find_matches;

import entity.User;

/**
 * The input data for the Find Matches Use Case.
 */
public class FindMatchesInputData {

    private final User user;
    private final boolean expand;

    public FindMatchesInputData(User user, boolean expand) {
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
