package view;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Course;
import entity.Timeslot;
import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;

@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings"})
public class EditProfileView extends JFrame {
    @SuppressWarnings({"checkstyle:ModifierOrder", "checkstyle:SuppressWarnings", "checkstyle:ConstantName"})
    public final static String viewName = "edit profile";
    private EditProfileController controller;
    private final EditProfileViewModel viewModel;

    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JTextField nameField;
    private final JTextArea bioField;
    private final JTextField programField;
    private final JTextField coursesField;
    private final JCheckBox[][] checkBoxes;

    @SuppressWarnings({"checkstyle:TrailingComment", "checkstyle:MagicNumber", "checkstyle:LambdaParameterName", "checkstyle:LambdaBodyLength", "checkstyle:ExecutableStatementCount", "checkstyle:VariableDeclarationUsageDistance", "checkstyle:JavaNCSS"})
    public EditProfileView(EditProfileViewModel viewModel) {
        this.viewModel = viewModel;

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
        emailField = new JTextField();
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // Bio Field
        JLabel bioLabel = new JLabel("Bio:");
        bioField = new JTextArea(3, 20);
        JScrollPane bioScroll = new JScrollPane(bioField);
        inputPanel.add(bioLabel);
        inputPanel.add(bioScroll);

        // Program Field
        JLabel programLabel = new JLabel("Program:");
        programField = new JTextField();
        inputPanel.add(programLabel);
        inputPanel.add(programField);

        // Courses Field
        JLabel coursesLabel = new JLabel("Courses (comma-separated):");
        coursesField = new JTextField();
        inputPanel.add(coursesLabel);
        inputPanel.add(coursesField);

        // Availability Section (Monday-Sunday, 9 AM to 5 PM)
        JLabel availabilityLabel = new JLabel("Availability:");
        JPanel availabilityPanel = new JPanel(new GridLayout(7, 8)); // 7 days, 8 time blocks (9 AM to 5 PM)
        checkBoxes = new JCheckBox[7][8];

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
                    email,
                    password,
                    name,
                    bio,
                    program,
                    courseList,
                    selectedAvailability,
                    "currentSchedulerID"  // This should come from the current user's data
            );

            if (viewModel.getState().isSuccessful()) {
                JOptionPane.showMessageDialog(this, "Changes saved successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        viewModel.getState().getErrorMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        // Initialize fields with current values from ViewModel
        updateFields();
    }

    @SuppressWarnings({"checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity"})
    private void updateFields() {
        if (viewModel.getEmail() != null) {
            emailField.setText(viewModel.getEmail());
        }
        if (viewModel.getPassword() != null) {
            passwordField.setText(viewModel.getPassword());
        }
        if (viewModel.getName() != null) {
            nameField.setText(viewModel.getName());
        }
        if (viewModel.getBio() != null) {
            bioField.setText(viewModel.getBio());
        }
        if (viewModel.getProgram() != null) {
            programField.setText(viewModel.getProgram());
        }

        // Update courses field
        if (viewModel.getCourses() != null) {
            StringBuilder coursesText = new StringBuilder();
            for (Course course : viewModel.getCourses()) {
                if (coursesText.length() > 0) {
                    coursesText.append(", ");
                }
                coursesText.append(course.getCourseCode());
            }
            coursesField.setText(coursesText.toString());
        }

        // Update availability checkboxes
        if (viewModel.getAvailability() != null) {
            Map<Timeslot, Boolean> availability = viewModel.getAvailability();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    Timeslot timeslot = new Timeslot(i + 1, 9 + j);
                    checkBoxes[i][j].setSelected(availability.getOrDefault(timeslot, false));
                }
            }
        }
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public void setController(EditProfileController controller) {
        this.controller = controller;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public String getViewName() {
        return viewName;
    }
}