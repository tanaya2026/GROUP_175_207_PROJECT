package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data_access.DataAccessObject;
import interface_adapter.no_matches_found.NoMatchesFoundViewModel;
import interface_adapter.no_matches_found.NoMatchesFoundController;
import use_case.display_matches.NoMatchesFoundInteractor;

public class NoMatchesFoundView extends JPanel {
    private final NoMatchesFoundViewModel viewModel;
    private final NoMatchesFoundController controller;

    public NoMatchesFoundView(NoMatchesFoundViewModel viewModel, NoMatchesFoundController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("No users found in matching courses", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Match based on shared program");

        // Action listener for the button
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the current username or session info to the controller
                String currentUsername = "currentUser";  // Fetch current username from session or context
                controller.handleMatchBasedOnProgramAction(currentUsername); // Delegate to controller
            }
        });

        buttonPanel.add(button1);
        add(buttonPanel, BorderLayout.CENTER);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sign-out logic
            }
        });
        add(signOutButton, BorderLayout.SOUTH);
    }

    // Main method for testing
    public static void main(String[] args) {
        JFrame frame = new JFrame("No Matches Found");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);

        NoMatchesFoundViewModel viewModel = new NoMatchesFoundViewModel();
        DataAccessObject dao = new DataAccessObject();
        NoMatchesFoundInteractor interactor = new NoMatchesFoundInteractor(dao);
        NoMatchesFoundController controller = new NoMatchesFoundController(interactor);

        frame.add(new NoMatchesFoundView(viewModel, controller));

        frame.setVisible(true);
    }
}
