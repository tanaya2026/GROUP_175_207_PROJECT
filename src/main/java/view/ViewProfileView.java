package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    public ViewProfileView(ViewProfileViewModel viewProfileViewModel) {
        this.viewProfileViewModel = viewProfileViewModel;
        viewProfileViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ViewProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton(ViewProfileViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    final ViewProfileState currentState = ViewProfileViewModel.getState();
                    viewProfileController.execute();
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
    }

    /**
     * Intializes the controller.
     * @param controller The controller of ViewProfileController.
     */

    public void setSignupController(ViewProfileController controller) {
        this.viewProfileController = controller;
    }
}
