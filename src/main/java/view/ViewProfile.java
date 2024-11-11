package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The View for when the user is viewing a profile of a user they have matched with.
 */

public class ViewProfile {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;

    /**
     * Builds and runs the CA architecture of the application.
     *
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Potential Matches");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel namelabel = new JLabel("Name", JLabel.CENTER);
        JTextField usernameField = new JTextField();
        JLabel empty = new JLabel("\n", JLabel.CENTER);

        JLabel emaillabel = new JLabel("Email", JLabel.CENTER);
        JTextField EmailField = new JTextField();
        JLabel empties = new JLabel("\n", JLabel.CENTER);


        JLabel programlabel = new JLabel("Program", JLabel.CENTER);
        JTextField ProgramField = new JTextField();
        JLabel emptied = new JLabel("\n", JLabel.CENTER);


        JLabel courseslabel = new JLabel("Courses", JLabel.CENTER);
        JTextField CourseField = new JTextField();
        JLabel emptiedd = new JLabel("\n", JLabel.CENTER);

        JLabel biolabel = new JLabel("Bio", JLabel.CENTER);
        JTextField BioField = new JTextField();
        JLabel emp = new JLabel("\n", JLabel.CENTER);

        JLabel genderlabel = new JLabel("Gender", JLabel.CENTER);
        JTextField GenderField = new JTextField();
        JLabel emtr = new JLabel("\n", JLabel.CENTER);

        JButton NextButton = new JButton("Next");
        JButton PreviousButton = new JButton("Previous");

        panel.add(namelabel);
        panel.add(usernameField);
        panel.add(empty);

        panel.add(emaillabel);
        panel.add(EmailField);
        panel.add(empties);

        panel.add(programlabel);
        panel.add(ProgramField);
        panel.add(emptied);

        panel.add(courseslabel);
        panel.add(CourseField);
        panel.add(emptied);

        panel.add(biolabel);
        panel.add(BioField);
        panel.add(emp);

        panel.add(NextButton);
        panel.add(PreviousButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
