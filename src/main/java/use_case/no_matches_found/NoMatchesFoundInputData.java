package use_case.no_matches_found;

public class NoMatchesFoundInputData {
    private final String username;
    private final String program;

    public NoMatchesFoundInputData(String username, String program) {
        this.username = username;
        this.program = program;
    }

    public String getUsername() {
        return username;
    }

    public String getProgram() {
        return program;
    }
}
