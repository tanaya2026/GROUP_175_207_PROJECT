package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

        JPanel availabilityPanel = new JPanel(new GridLayout(7, 4));

        // Days of the week and time slots (morning, afternoon, evening)
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] times = {9, 10, 11, 12, 13, 14, 15, 16}; // 9 AM, 10 AM, ..., 4 PM slots
        JCheckBox[][] availabilityCheckboxes = new JCheckBox[7][8];

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

        final LabelTextPanel avaliablityInfo = new LabelTextPanel(
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

                    signupController.execute(
                            currentState.getUsername(),
                            currentState.getPassword(),
                            currentState.getEmail(),
                            currentState.getName(),
                            currentState.getCourses(),
                            currentState.getProgram(),
                            currentState.getAvaliability(),
                            currentState.getBio(),
                            currentState.getSlotifyService()
                    );
                }
            }

            //                // Parse courses from comma-separated input
//                List<Course> courses = new ArrayList<>();
//                for (String courseCode : coursesField.getText().split(",")) {
//                    courses.add(new Course(courseCode.trim()));
//                }
//
//                // Parse availability from selected checkboxes
//                Map<Timeslot, Boolean> availabilityMap = new HashMap<>();
//                for (int i = 0; i < days.length; i++) {
//                    for (int j = 0; j < times.length; j++) {
//                        if (availabilityCheckboxes[i][j].isSelected()) {
//                            Timeslot timeslot = new Timeslot(i + 1, times[j]); // i+1 for day, times[j] for hour
//                            availabilityMap.put(timeslot, true);
//                        }
//                        else {
//                            Timeslot timeslot = new Timeslot(i + 1, times[j]); // i+1 for day, times[j] for hour
//                            availabilityMap.put(timeslot, false);
//                        }
//                    }
//                }
//
//                // Create a User instance with the input data
//                User newUser = new User(
//                        usernameField.getText(),
//                        emailField.getText(),
//                        passwordField.getText(),
//                        nameField.getText(),
//                        courses,
//                        programField.getText(),
//                        bioField.getText(),
//                        availabilityMap
//                );
//
//                // Display confirmation message
//                JOptionPane.showMessageDialog(null, "Account created for: " + newUser.getUsername());
//                System.out.println(newUser.toString());  // Print user details to console for debugging
//            }
//        });
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            UserRegistration app = new UserRegistration();
//            app.setVisible(true);
//        });
//    }
//
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
