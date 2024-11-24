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

/**
 * The View for the Home page of the program.
 */
public class HomePageView extends JPanel {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;
    private final String viewName = "sign up";
    private final HomePageViewModel homePageViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton createAccount;
    private final JButton Login;

    public HomePageView(HomePageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;
        // homePageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(HomePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel explanation = new JLabel(HomePageViewModel.APP_LABEL);
        explanation.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel login = new JLabel(HomePageViewModel.LOGIN_TITLE_BUTTON_LABEL);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(HomePageViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(HomePageViewModel.PASSWORD_LABEL), passwordInputField);

        final JPanel buttons = new JPanel();
        createAccount = new JButton(HomePageViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(createAccount);
        Login = new JButton(HomePageViewModel.LOGIN_TITLE_BUTTON_LABEL);
        buttons.add(Login);

        // Action listener for the "Create an Account" button
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserRegistrationApp frame
                UserRegistration registrationApp = new UserRegistration();
                registrationApp.setVisible(true);

                // Close the current RegisterView frame
                //  frame.dispose();
            }
        });
    }

    public static final String getViewName() {
        return viewName;
    }
}
