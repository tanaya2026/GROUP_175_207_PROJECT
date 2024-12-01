package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.display_matches.DisplayMatchesViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_profile.ViewProfileController;
import interface_adapter.view_profile.ViewProfileState;
import interface_adapter.view_profile.ViewProfileViewModel;

/**
 * The View for when the user is viewing a profile of a user they have matched with.
 */

public class ViewProfileView extends JPanel implements PropertyChangeListener {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;

    private final String viewName = "viewProfile";
    private final ViewProfileViewModel viewProfileViewModel;
    private ViewProfileController viewProfileController;
    private final JButton back;
    private final JButton viewProfileButton;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField emailInputField = new JPasswordField(15);
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField coursesInputField = new JTextField(15);
    private final JTextField programInputField = new JTextField(15);
    private final JTextField bioInputField = new JTextField(15);
    private final JTextField avaliabilityField = new JTextField(15);

    public ViewProfileView(ViewProfileViewModel viewProfileViewModel) {
        this.viewProfileViewModel = viewProfileViewModel;
        viewProfileViewModel.addPropertyChangeListener(this);

        JFrame frame = new JFrame("Replace ViewProfile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ViewProfileViewModel.WIDTH, ViewProfileViewModel.HEIGHT);
        frame.setLayout(new BorderLayout());

        final JLabel title = new JLabel(ViewProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);

        final LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.EMAIL_LABEL), emailInputField);

        final LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.NAME_LABEL), nameInputField);

        final LabelTextPanel coursesinfo = new LabelTextPanel(
                new JLabel(SignupViewModel.COURSES_LABEL), coursesInputField);

        final LabelTextPanel programInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PROGRAM_LABEL), programInputField);

        final LabelTextPanel bioInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.BIO_LABEL), bioInputField);

        final JPanel avaliablityInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.AVAIL_LABEL), avaliabilityField);

        final JPanel buttons = new JPanel();
        back = new JButton(ViewProfileViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        viewProfileButton = new JButton(ViewProfileViewModel.VIEW_PROFILE_LABEL);
        buttons.add(viewProfileButton);

        frame.add(buttons, BorderLayout.CENTER);
        frame.setVisible(true);

        // DisplayViewMatchesPresenter changes State to ViewProfileView

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(viewProfileButton)) {
                    // Go back to DisplayMatchesView
                    final ViewProfileState currentState = viewProfileViewModel.getState();
                    viewProfileController.execute();
                }
                else if (e.getSource().equals(back)) {
                    final DisplayMatchesViewModel displayMatchesViewModel = new DisplayMatchesViewModel();
                    final DisplayMatchesView displayMatchesView = new DisplayMatchesView(displayMatchesViewModel);
                    // Remove all components.
                    frame.getContentPane().removeAll();
                    // Add the new panel.
                    frame.add(displayMatchesView);
                    // Refresh the frame.
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
    }

    /**
     * Returns the viewName.
     * @return viewName the viewName.
     */
    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ViewProfileState state = (ViewProfileState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(ViewProfileState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
        emailInputField.setText(state.getEmail());
        nameInputField.setText(state.getName());
        programInputField.setText(state.getProgram());
        bioInputField.setText(state.getBio());
        coursesInputField.setText(state.getCourses());
        avaliabilityField.setText(state.getAvaliability());
    }

    /**
     * Intializes the controller.
     * @param controller The controller of ViewProfileController.
     */

    public void setSignupController(ViewProfileController controller) {
        this.viewProfileController = controller;
    }
}
