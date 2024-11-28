package view;

import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_profile.ViewProfileViewModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/**
 * The View for when the user is viewing a profile of a user they have matched with.
 */

public class ViewProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int WIDTH_FRAME = 1000;
    private static final int HEIGHT_FRAME = 300;

    private final String viewName = "viewProfile";
    private final ViewProfileViewModel viewProfileViewModel;

    /**
     * Builds and runs the CA architecture of the application.
     *
     * @param args unused arguments
     */
    public static void main(String[] args) {


    public static int getViewName() {
        return viewName;
        }
    }
}
