package view;

import interface_adapter.edit_profile.EditProfileController;
import entity.Course;
import entity.Timeslot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileView extends JFrame {

    private final EditProfileController controller;

    public EditProfileView(EditProfileController controller) {
        this.controller = controller;

        setTitle("Edit Profile");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // Bio Field
        JLabel bioLabel = new JLabel("Bio:");
        JTextArea bioField = new JTextArea(3, 20);
        JScrollPane bioScroll = new JScrollPane(bioField);
        inputPanel.add(bioLabel);
        inputPanel.add(bioScroll);

        // Program Field
        JLabel programLabel = new JLabel("Program:");
        JTextField programField = new JTextField();
        inputPanel.add(programLabel);
        inputPanel.add(programField);

        // Courses Field
        JLabel coursesLabel = new JLabel("Courses (comma-separated):");
        JTextField coursesField = new JTextField();
        inputPanel.add(coursesLabel);
        inputPanel.add(coursesField);

        // Availability Section (Monday-Sunday, 9 AM to 5 PM)
        JLabel availabilityLabel = new JLabel("Availability:");
        JPanel availabilityPanel = new JPanel(new GridLayout(7, 8)); // 7 days, 8 time blocks (9 AM to 5 PM)
        JCheckBox[][] checkBoxes = new JCheckBox[7][8];

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                JCheckBox checkBox = new JCheckBox(days[i] + " " + (9 + j) + ":00");
                checkBoxes[i][j] = checkBox;
                availabilityPanel.add(checkBox);
            }
        }

        inputPanel.add(availabilityLabel);
        inputPanel.add(new JScrollPane(availabilityPanel));

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Button Actions
        saveButton.addActionListener(e -> {
            // Collect inputs
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String bio = bioField.getText();
            String program = programField.getText();
            String courses = coursesField.getText();

            // Convert courses to a List of Course objects
            List<Course> courseList = new ArrayList<>();
            for (String courseCode : courses.split(",")) {
                courseList.add(new Course(courseCode.trim(), "")); // Assuming course name is not required for now
            }

            // Collect only selected availability
            Map<Timeslot, Boolean> selectedAvailability = new HashMap<>();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if (checkBoxes[i][j].isSelected()) {
                        Timeslot timeslot = new Timeslot(i + 1, 9 + j);
                        selectedAvailability.put(timeslot, true);
                    }
                }
            }

            // Pass data to the controller
            controller.handleEditProfile(
                    "currentUsername", // Replace with actual username
                    email,
                    password,
                    name,
                    bio,
                    program,
                    courseList,
                    selectedAvailability,
                    "currentSchedulerID" // Replace with the actual schedulerID
            );

            JOptionPane.showMessageDialog(this, "Changes saved successfully.");
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
