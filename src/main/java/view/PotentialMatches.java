package view;

import java.awt.*;

import javax.swing.*;

public class PotentialMatches extends JPanel {

    // CONSTANTS
    private static final int TITLE_FONT_SIZE = 16;
    private static final int FRAME_HEIGHT = 200;
    private static final int FRAME_WIDTH = 300;

    public PotentialMatches() {
        // Set the layout for the panel
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Potential Matches", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        // Create a panel for the title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Create a button and panel for the "Edit User Info"
        JPanel editButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("Edit User Info");
        editButtonPanel.add(editButton);

        // Add title and edit button to the top panel
        topPanel.add(editButtonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Create a panel for the potential match buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create buttons with different names based on potential matches
        JButton button1 = new JButton("Match 1");
        JButton button2 = new JButton("Match 2");

        // Add the potential matches buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add potential matches button panel to the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Main method to display the panel in a frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Match Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Add PotentialMatchesPanel to the frame
        frame.add(new PotentialMatches());

        // Display the frame
        frame.setVisible(true);
    }
}
