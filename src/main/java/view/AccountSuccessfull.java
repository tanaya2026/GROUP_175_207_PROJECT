package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The View for when the user has successfully created an account into the program.
 * And for when they request all their matches
 */

public class AccountSuccessfull {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;

    /**
     * Builds and runs the CA architecture of the application.
     *
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Account Successfull!");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Account Succesfully Created!", JLabel.CENTER);
        JLabel empty = new JLabel("\n", JLabel.CENTER);
        JLabel empties = new JLabel("\n", JLabel.CENTER);

        JButton matchesButton = new JButton("Find Potential Matches");

        panel.add(label1);
        panel.add(empty);
        panel.add(empties);
        panel.add(matchesButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
