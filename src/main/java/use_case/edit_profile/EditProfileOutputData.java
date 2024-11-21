package use_case.edit_profile;

public class EditProfileOutputData {
    private final String name;
    private final String bio;

    public EditProfileOutputData(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }
}
