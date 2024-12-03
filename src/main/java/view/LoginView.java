package view;

import java.awt.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

public class LoginView extends JPanel implements PropertyChangeListener {

    private static final String VIEW_NAME = "login";
    private final LoginViewModel loginViewModel;

    // Username and password input fields
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton login;

    public LoginView(LoginViewModel viewModel) {
        this.loginViewModel = viewModel;
        loginViewModel.addPropertyChangeListener(this);

        // Title Label
        final JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login Label
        final JLabel loginlabel = new JLabel(LoginViewModel.LOGIN_TITLE_BUTTON_LABEL);
        loginlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username and Password field
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.PASSWORD_LABEL), passwordInputField);

        // Login Button
        final JPanel buttons = new JPanel();
        login = new JButton(LoginViewModel.LOGIN_TITLE_BUTTON_LABEL);
        buttons.add(login);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState loginState = (LoginState) evt.getNewValue();
    }

    public static final String getViewName() {
        return VIEW_NAME;
    }
}
