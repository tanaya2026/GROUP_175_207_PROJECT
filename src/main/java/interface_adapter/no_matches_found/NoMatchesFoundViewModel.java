package interface_adapter.no_matches_found;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoMatchesFoundViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private boolean isMatchBasedOnProgram;

    public NoMatchesFoundViewModel() {
        this.isMatchBasedOnProgram = false;
    }

    public void setMatchBasedOnProgram(boolean matchBasedOnProgram) {
        boolean oldValue = this.isMatchBasedOnProgram;
        this.isMatchBasedOnProgram = matchBasedOnProgram;
        support.firePropertyChange("isMatchBasedOnProgram", oldValue, matchBasedOnProgram);
    }

    public boolean isMatchBasedOnProgram() {
        return isMatchBasedOnProgram;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
