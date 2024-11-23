package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NoMatchesFound extends JPanel {
    public NoMatchesFound() {
        // Set the layout for the panel
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("No users found in matching courses", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the main button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create a main button
        JButton button1 = new JButton("Match based on shared availability");

        // Add an action listener to the button
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create the PotentialMatches frame when button1 is clicked
                JFrame potentialMatchesFrame = new JFrame("Potential Matches");
                potentialMatchesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                potentialMatchesFrame.setSize(300, 200);
                potentialMatchesFrame.add(new PotentialMatches());
                potentialMatchesFrame.setVisible(true);
            }
        });

        // Add button1 to the button panel
        buttonPanel.add(button1);

        // Add button panel to the main panel
        add(buttonPanel, BorderLayout.CENTER);

        // Create a sign-out button and add it to the bottom
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the OpeningPage (welcome/login page)
                JFrame openingPageFrame = new JFrame("StudyBuddyFinder App");
                openingPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                openingPageFrame.setSize(1000, 300);
                openingPageFrame.add(new OpeningPage());
                openingPageFrame.setLocationRelativeTo(null);  // Center the window
                openingPageFrame.setVisible(true);

                // Close the current frame (NoMatchesFound)
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(NoMatchesFound.this);
                currentFrame.dispose();
            }
        });
        add(signOutButton, BorderLayout.SOUTH);
    }

    // Main method to display the panel in a frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Match Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);

        // Add NoMatchesFound panel to the frame
        frame.add(new NoMatchesFound());

        // Display the frame
        frame.setVisible(true);
    }
}
