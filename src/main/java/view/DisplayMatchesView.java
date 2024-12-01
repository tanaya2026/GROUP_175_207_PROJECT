package view;

import interface_adapter.display_matches.DisplayMatchesController;
import interface_adapter.display_matches.DisplayMatchesState;
import interface_adapter.display_matches.DisplayMatchesViewModel;
import interface_adapter.edit_profile.EditProfileController;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.view_profile.ViewProfileViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class DisplayMatchesView extends JPanel implements PropertyChangeListener {

    // CONSTANTS
    private static final int TITLE_FONT_SIZE = 16;

    private final String viewName = "display matches";

    private final DisplayMatchesViewModel displayMatchesViewModel;
    private DisplayMatchesController displayMatchesController;

    public DisplayMatchesView(DisplayMatchesViewModel displayMatchesViewModel) {
        this.displayMatchesViewModel = displayMatchesViewModel;
        displayMatchesViewModel.addPropertyChangeListener(this);

        // Create JFrame
        JFrame frame = new JFrame("Display Matches");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DisplayMatchesViewModel.WIDTH, DisplayMatchesViewModel.HEIGHT);
        frame.setLayout(new BorderLayout());

        // Set the layout for the panel
        setLayout(new BorderLayout());
        // Create a label for the title
        JLabel titleLabel = new JLabel("Matches", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        // Create a panel for the title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Create a button and panel for the "Edit User Info"
        JPanel editButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("Edit User Info");
        editButtonPanel.add(editButton);

        // Create a button and panel for the "Sign Out"
        JPanel signOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton signOut = new JButton("Edit User Info");
        signOutPanel.add(signOut);

        // Add title and edit button to the top panel
        topPanel.add(editButtonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Create a panel for the match buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create buttons with different names based on matches
        JButton button1 = new JButton("Match 1");
        JButton button2 = new JButton("Match 2");

        // Add the matches buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add matches button panel to the main panel
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create a sign-out button and add it to the bottom
        JButton signOutButton = new JButton("Sign Out");
        add(signOutButton, BorderLayout.SOUTH);

        // Add sign out panel to main panel at the bottom
        frame.add(signOutButton, BorderLayout.SOUTH);

        // Make frame visible
        frame.setVisible(true);

        // Change to EditProfileView if editButton is clicked
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(editButton)) {
                    final EditProfileInputBoundary interactor = new EditProfileInteractor();
                    final EditProfileController editProfileController = new EditProfileController(interactor);
                    final EditProfileView editProfileView = new EditProfileView(editProfileController);

                    frame.getContentPane().removeAll();
                    // Add the new panel.
                    frame.add(editProfileView);
                    // Refresh the frame.
                    frame.revalidate();
                    frame.repaint();

                }
            }
        });

        signOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(signOut)) {
                    final HomePageViewModel homePageViewModel = new HomePageViewModel();
                    final HomePageView homePageView = new HomePageView(homePageViewModel);

                    frame.getContentPane().removeAll();
                    // Add the new panel.
                    frame.add(homePageView);
                    // Refresh the frame.
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DisplayMatchesState state = (DisplayMatchesState) evt.getNewValue();
    }

    public class EditProfileInteractor implements EditProfileInputBoundary {
        @Override
        public void editProfile(String username, EditProfileInputData inputData) {

        }
    }
}
