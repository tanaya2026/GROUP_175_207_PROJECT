package interface_adapter.no_matches_found;

import java.util.List;
import java.util.Map;

import javax.swing.*;

import data_access.DataAccessObject;
import entity.Timeslot;
import entity.User;
import use_case.display_matches.NoMatchesFoundInteractor;
import view.DisplayMatchesView;

public class NoMatchesFoundController {
    private final NoMatchesFoundInteractor interactor;

    public NoMatchesFoundController(NoMatchesFoundInteractor interactor) {
        this.interactor = interactor;
    }

    // Handle action to match based on shared program
    public void handleMatchBasedOnProgramAction(String currentUsername) {
        // Retrieve current user (could be fetched from a session or DAO)
        User currentUser = new DataAccessObject().getUserByUsername(currentUsername);

        // Use the interactor to find matches
        Map<User, List<Timeslot>> programMatches = interactor.findMatchesBasedOnProgram(currentUser);

        // Check if matches were found
        if (programMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users found with shared programs.");
        } else {
            // Show matches in a new view
            showMatchesInNewView(programMatches);
        }
    }

    private void showMatchesInNewView(Map<User, List<Timeslot>> programMatches) {
        // Logic to display the matches in a new view
        JFrame potentialMatchesFrame = new JFrame("Potential Matches");
        potentialMatchesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        potentialMatchesFrame.setSize(600, 400);
        potentialMatchesFrame.add(new DisplayMatchesView(programMatches));
        potentialMatchesFrame.setVisible(true);
    }
}
