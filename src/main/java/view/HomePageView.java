package view;
import interface_adapter.homepage.HomePageViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.LabelTextPanel;

/**
 * The View for the Home page of the program.
 */
public class HomePageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home page";
    private final HomePageViewModel homePageViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton createAccount;
    private final JButton login;

    public HomePageView(HomePageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(HomePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel explanation = new JLabel(HomePageViewModel.APP_LABEL);
        explanation.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel loginlabel = new JLabel(HomePageViewModel.LOGIN_TITLE_BUTTON_LABEL);
        loginlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(HomePageViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(HomePageViewModel.PASSWORD_LABEL), passwordInputField);

        final JPanel buttons = new JPanel();
        createAccount = new JButton(HomePageViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(createAccount);
        login = new JButton(HomePageViewModel.LOGIN_TITLE_BUTTON_LABEL);
        buttons.add(login);

        // Action listener for the "Create an Account" button
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserRegistrationApp frame
                UserRegistration registrationApp = new UserRegistration();
                registrationApp.setVisible(true);

                // Close the current RegisterView frame
                registrationApp.dispose();
            }
        });

        // ActionListener for "Login" button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Login UseCase frame

                // Include code here!

                // Close the current RegisterView frame
                //  frame.dispose();
            }
        });

    }

    // Include a action listener button for Login Use Case!


    public static final String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
