package entity;

import java.util.Map;

/**
 * This class holds the schedulerID of a Slotify scheduler, and retrieves the current
 * scheduler from Slotify in a Map format
 */
public class Availability {

    private String schedulerID;

    public Availability(String schedulerID) {
        this.schedulerID = schedulerID;
    }

    /**
     * Returns the availability in Map format.
     * @return the availability in Map format, from its Slotify schedulerID for this user.
     * // @throws IllegalArgumentException if the day is an unexpected value
     */
    public Map<Timeslot, Boolean> getAvailability() {
        // Need to figure out how to retrieve the scheduler availability from Slotify
        // Store in JSONObejct and then convert to Map with for loop?
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Availability {");
        for (Map.Entry<Timeslot, Boolean> entry : getAvailability().entrySet()) {
            Timeslot timeslot = entry.getKey();
            Boolean isAvailable = entry.getValue();
            if (isAvailable) {
                sb.append("\n.             ").append(timeslot.toString());
                count = count + 1;
            }
        }

        if (count == 0) {
            return "No availability";
        }

        sb.append("\n}");
        return sb.toString();
    }

    /**
     * Returns the schedulerID of this Availability object.
     * @return the schedulerID string.
     */
    public String getSchedulerID() {
        return schedulerID;
    }
}
