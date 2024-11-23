package use_case.find_potential_matches;

import entity.User;

/**
 * The input data for the Find Potential Matches Use Case.
 */
public class FindPotentialMatchesInputData {

    private final User user;
    private final boolean expand;

    public FindPotentialMatchesInputData(User user, boolean expand) {
        this.user = user;
        this.expand = expand;
    }

//    String getPassword() {
//        return password;
//    }

//    String getUsername() {
//        return username;
//    }

}
