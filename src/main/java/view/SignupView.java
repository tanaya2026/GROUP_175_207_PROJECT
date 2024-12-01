package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Timeslot;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */

public class SignupView extends JPanel implements PropertyChangeListener {

    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField emailInputField = new JPasswordField(15);
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField coursesInputField = new JTextField(15);
    private final JTextField programInputField = new JTextField(15);
    private final JTextField bioInputField = new JTextField(15);

    private SignupController signupController;
    private final JButton createAccount;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        JPanel availabilityPanel = new JPanel(new GridLayout(SignupViewModel.AVAIL_ROWS, SignupViewModel.AVAIL_COLS));
        // JPanel availabilityPanel = new JPanel(new GridLayout(7, 4));

        // Days of the week and time slots (morning, afternoon, evening)
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        // 9 AM, 10 AM, ..., 4 PM slots
        int[] times = {SignupViewModel.TIME_9, SignupViewModel.TIME_10, SignupViewModel
                .TIME_11, SignupViewModel.TIME_12, SignupViewModel.TIME_13, SignupViewModel
                .TIME_14, SignupViewModel.TIME_15, SignupViewModel.TIME_16};
        // int[] times = {9, 10, 11, 12, 13, 14, 15, 16};
        JCheckBox[][] availabilityCheckboxes = new JCheckBox[SignupViewModel.AVAIL_ROWS][SignupViewModel.CHECK_BOX_COLS];
        // JCheckBox[][] availabilityCheckboxes = new JCheckBox[7][8];

        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < times.length; j++) {
                availabilityCheckboxes[i][j] = new JCheckBox(days[i] + " " + times[j] + ":00-"
                        + times[j + 1] + ":00");
                availabilityPanel.add(availabilityCheckboxes[i][j]);
            }
        }

        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
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
                new JLabel(SignupViewModel.AVAIL_LABEL), availabilityPanel);

        final JPanel buttons = new JPanel();
        createAccount = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(createAccount);

        // Event handling for the create account button
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(createAccount)) {
                    final SignupState currentState = signupViewModel.getState();

                    List<String> courses = new ArrayList<>();
                    for (String courseCode : coursesInputField.getText().split(",")) {
                        courses.add(courseCode.trim());
                    }

                    Map<Timeslot, Boolean> availabilityMap = new HashMap<>();
                    for (int i = 0; i < days.length; i++) {
                        for (int j = 0; j < times.length; j++) {
                            if (availabilityCheckboxes[i][j].isSelected()) {
                                // i+1 for day, times[j] for hour
                                Timeslot timeslot = new Timeslot(i + 1, times[j]);
                                availabilityMap.put(timeslot, true);
                                }
                            else {
                                // i+1 for day, times[j] for hour
                                Timeslot timeslot = new Timeslot(i + 1, times[j]);
                                availabilityMap.put(timeslot, false);
                                }
                            }
                        }

                    signupController.execute(
                            usernameInputField.getText(),
                            passwordInputField.getText(),
                            emailInputField.getText(),
                            nameInputField.getText(),
                            courses,
                            programInputField.getText(),
                            bioInputField.getText(),
                            availabilityMap,
                            currentState.getSlotifyService()
                    );
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
        final SignupState state = (SignupState) evt.getNewValue();
    }

    /**
     * Initializes the controller.
     * @param controller The SignUpcontroller.
     */

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
