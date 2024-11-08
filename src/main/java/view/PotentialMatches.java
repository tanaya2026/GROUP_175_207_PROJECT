package view;

import java.awt.*;

import javax.swing.*;

public class PotentialMatches extends JPanel {
    public PotentialMatches() {
        // Set the layout for the panel
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Potential Matches", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create two buttons with different names
        JButton button1 = new JButton("Match 1");
        JButton button2 = new JButton("Match 2");

        // Add buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add button panel to the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Main method to display the panel in a frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Match Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Add PotentialMatchesPanel to the frame
        frame.add(new PotentialMatches());

        // Display the frame
        frame.setVisible(true);
    }
}
