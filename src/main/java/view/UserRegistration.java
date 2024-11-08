package view;

import entity.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRegistration extends JFrame {

    public UserRegistration() {
        setTitle("User Registration");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the input fields and button
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Create input fields and labels
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel coursesLabel = new JLabel("Courses (comma-separated, no spaces):");
        JTextField coursesField = new JTextField();

        JLabel programLabel = new JLabel("Program:");
        JTextField programField = new JTextField();

        JLabel bioLabel = new JLabel("Bio:");
        JTextField bioField = new JTextField();

        JLabel availabilityLabel = new JLabel("Availability:");
        JTextField availabilityField = new JTextField();

        JButton createButton = new JButton("Create Account");

        // Add components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(coursesLabel);
        panel.add(coursesField);
        panel.add(programLabel);
        panel.add(programField);
        panel.add(bioLabel);
        panel.add(bioField);
        panel.add(availabilityLabel);
        panel.add(availabilityField);
        panel.add(createButton);

        // Add the panel to the frame
        add(panel);

        // Event handling for the create account button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse the courses input
                String coursesInput = coursesField.getText();
                List<entity.Course> courses = new ArrayList<>();
                if (!coursesInput.isEmpty()) {
                    List<String> courseCodes = Arrays.asList(coursesInput.split(","));
                    for (String code : courseCodes) {
                        courses.add(new entity.Course(code.trim(), "description"));
                    }
                }

                // Create a User instance with the input data
                User newUser = new User(
                        usernameField.getText(),
                        emailField.getText(),
                        passwordField.getText(),
                        nameField.getText(),
                        courses,
                        programField.getText(),
                        bioField.getText(),
                        availabilityField.getText()
                );

                // Display confirmation message
                JOptionPane.showMessageDialog(null, "Account created for: " + newUser.getUsername());
            }
        });
    }

    // Course class placeholder
    public static class Course {
        private String courseCode;

        public Course(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseCode() {
            return courseCode;
        }
    }

    // Availability class placeholder
    public static class Availability {
        private String availability;

        public Availability(String availability) {
            this.availability = availability;
        }

        @Override
        public String toString() {
            return availability;
        }
    }

    // User class (assuming the same as provided)
    // ... User class implementation from your code here ...

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRegistration app = new UserRegistration();
            app.setVisible(true);
        });
    }
}
