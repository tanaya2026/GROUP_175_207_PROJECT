package app.gui;

import entity.Timeslot;
import entity.User;
import interface_adapter.EditProfileController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EditProfileView extends JFrame {

    private final User currentUser;

    public EditProfileView(EditProfileController controller, User user) {
        this.currentUser = user;

        setTitle("Edit Profile");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(user.getName());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel bioLabel = new JLabel("Bio:");
        JTextArea bioField = new JTextArea(user.getBio(), 5, 20);
        JScrollPane bioScroll = new JScrollPane(bioField);
        inputPanel.add(bioLabel);
        inputPanel.add(bioScroll);

        JLabel availabilityLabel = new JLabel("Availability:");
        JPanel availabilityPanel = new JPanel(new GridLayout(7, 8));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] times = {9, 10, 11, 12, 13, 14, 15, 16};
        JCheckBox[][] availabilityCheckboxes = new JCheckBox[7][8];

        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < times.length; j++) {
                Timeslot timeslot = new Timeslot(i + 1, times[j]);
                JCheckBox checkBox = new JCheckBox(days[i] + " " + times[j] + ":00 - " + (times[j] + 1) + ":00");
                checkBox.setSelected(user.getAvailability().getOrDefault(timeslot, false));
                availabilityCheckboxes[i][j] = checkBox;
                availabilityPanel.add(checkBox);
            }
        }

        inputPanel.add(availabilityLabel);
        inputPanel.add(new JScrollPane(availabilityPanel));

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        saveButton.addActionListener(e -> {
            String newName = nameField.getText();
            String newBio = bioField.getText();

            Map<Timeslot, Boolean> newAvailability = new HashMap<>();
            for (int i = 0; i < days.length; i++) {
                for (int j = 0; j < times.length; j++) {
                    Timeslot timeslot = new Timeslot(i + 1, times[j]);
                    newAvailability.put(timeslot, availabilityCheckboxes[i][j].isSelected());
                }
            }

            controller.handleEditProfile(newName, newBio, newAvailability);
            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
