package view;

import javax.swing.*;
import java.awt.*;

public class EditProfileView extends JFrame {

    public EditProfileView() {
        setTitle("Edit Profile");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // Bio Field
        JLabel bioLabel = new JLabel("Bio:");
        JTextArea bioField = new JTextArea(5, 20);
        JScrollPane bioScroll = new JScrollPane(bioField);
        inputPanel.add(bioLabel);
        inputPanel.add(bioScroll);

        // Availability Section Placeholder
        JLabel availabilityLabel = new JLabel("Availability:");
        JPanel availabilityPanel = new JPanel(new GridLayout(7, 8));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                JCheckBox checkBox = new JCheckBox("Day " + (i + 1) + " Slot " + (j + 1));
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
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Changes Saved!"));
        cancelButton.addActionListener(e -> dispose());
    }