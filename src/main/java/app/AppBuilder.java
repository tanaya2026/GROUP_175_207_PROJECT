package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder is putting together.
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private UserRegistration userRegistration;
    private UserRegistrationViewModel userRegistrationViewModel;
    private OpeningPage openingPage;
    private OpeningPageViewModel openingPageViewModel;
    private AccountSuccessfull accountSuccessfull;
    private AccountsuccessfullViewModel accountsuccessfullViewModel;
    private NoMatchesFound noMatchesFound;
    private NoMatchesFoundViewModel noMatchesFoundViewModel;
    private PotentialMatches potentialMatches;
    private PotentialMatchesViewModel potentialMatchesViewModel;
    private ViewProfile viewProfile;
    private ViewProfileViewModel viewProfileViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }
}
