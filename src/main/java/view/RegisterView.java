package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The View for when the user is creating an account into the program.
 */
public class RegisterView {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;

    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("StudyBuddyFinder App");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Hello! Welcome to StudyBuddyFinder!", JLabel.CENTER);
        JLabel empty = new JLabel("\n", JLabel.CENTER);
        JLabel label2 = new JLabel("Our application aims to pair students with study partners,", JLabel.CENTER);
        JLabel label3 = new JLabel(
                "who have similar courses, programs, and availability to enhance academic "
                        + "performance of our users.", JLabel.CENTER);
        JLabel empties = new JLabel("\n", JLabel.CENTER);

        JButton button = new JButton("Create an Account");

        panel.add(label1);
        panel.add(empty);
        panel.add(label2);
        panel.add(label3);
        panel.add(empties);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Action listener for the "Create an Account" button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserRegistrationApp frame
                UserRegistration registrationApp = new UserRegistration();
                registrationApp.setVisible(true);

                // Close the current RegisterView frame
                frame.dispose();
            }
        });
    }
}
