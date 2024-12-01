package use_case.no_matches_found;

import entity.User;
import java.util.Map;

public interface NoMatchesFoundDataAccessInterface {
    Map<User, String> findUsersByProgram(String program);
}
