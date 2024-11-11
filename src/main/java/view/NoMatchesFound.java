package view;

import java.awt.*;
import javax.swing.*;

public class NoMatchesFound extends JPanel {
    public NoMatchesFound() {
        // Set the layout for the panel
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("No users found in matching courses", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the main buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create two main buttons with different names
        JButton button1 = new JButton("Match based on shared program");
        JButton button2 = new JButton("Match based on shared availability");

        // Add buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add button panel to the main panel
        add(buttonPanel, BorderLayout.CENTER);

        // Create a sign-out button and add it to the bottom
        JButton signOutButton = new JButton("Sign Out");
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
