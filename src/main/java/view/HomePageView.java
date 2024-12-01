package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.homepage.HomePageViewState;

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
    private HomePageController homePageController;

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
                if (e.getSource().equals(createAccount)) {
                    final HomePageViewState currentState = homePageViewModel.getState();
                    homePageController.execute();
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
        final HomePageViewState state = (HomePageViewState) evt.getNewValue();
    }
}
