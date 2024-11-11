package view;

import entity.Availability;
import entity.Course;
import entity.Timeslot;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRegistration extends JFrame {

    // Constructor to set up the GUI components and event handling
    public UserRegistration() {
        setTitle("User Registration");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the input fields and button
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));

        // User information fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField();

        JLabel programLabel = new JLabel("Program:");
        JTextField programField = new JTextField();

        JLabel bioLabel = new JLabel("Bio:");
        JTextField bioField = new JTextField();

        JLabel genLabel = new JLabel("Gender(female,male,other):");
        JTextField genField = new JTextField();

        JLabel coursesLabel = new JLabel("Courses (comma-separated):");
        JTextField coursesField = new JTextField();

        // Availability section with checkboxes for days and times
        JLabel availabilityLabel = new JLabel("Availability:");
        panel.add(availabilityLabel);
        JPanel availabilityPanel = new JPanel(new GridLayout(7, 4));

        // Days of the week and time slots (morning, afternoon, evening)
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] times = {9, 13, 17, 20}; // 9 AM, 1 PM, 5 PM, 8 PM slots
        JCheckBox[][] availabilityCheckboxes = new JCheckBox[7][4];

        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < times.length; j++) {
                availabilityCheckboxes[i][j] = new JCheckBox(days[i] + " " + times[j] + ":00");
                availabilityPanel.add(availabilityCheckboxes[i][j]);
            }
        }

        JButton createButton = new JButton("Create Account");

        // Add components to the main panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(programLabel);
        panel.add(programField);
        panel.add(bioLabel);
        panel.add(bioField);
        panel.add(genLabel);
        panel.add(genField);
        panel.add(coursesLabel);
        panel.add(coursesField);
        panel.add(availabilityLabel);
        panel.add(availabilityPanel);
        panel.add(new JLabel()); // spacer
        panel.add(createButton);

        // Add the panel to the frame
        add(panel);

        // Event handling for the create account button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse courses from comma-separated input
                List<Course> courses = new ArrayList<>();
                for (String courseCode : coursesField.getText().split(",")) {
                    courses.add(new Course(courseCode.trim()));
                }

                // Parse availability from selected checkboxes
                Map<Timeslot, Boolean> availabilityMap = new HashMap<>();
                for (int i = 0; i < days.length; i++) {
                    for (int j = 0; j < times.length; j++) {
                        if (availabilityCheckboxes[i][j].isSelected()) {
                            Timeslot timeslot = new Timeslot(i + 1, times[j]); // i+1 for day, times[j] for hour
                            availabilityMap.put(timeslot, true);
                        }
                    }
                }
                Availability availability = new Availability(availabilityMap);

                // Create a User instance with the input data
                User newUser = new User(
                        usernameField.getText(),
                        emailField.getText(),
                        passwordField.getText(),
                        courses,
                        programField.getText(),
                        bioField.getText(),
                        availability
                );

                // Display confirmation message
                JOptionPane.showMessageDialog(null, "Account created for: " + newUser.getUsername());
                System.out.println(newUser.toString());  // Print user details to console for debugging
            }
        });
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRegistration app = new UserRegistration();
            app.setVisible(true);
        });
    }
}
