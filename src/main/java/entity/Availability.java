package entity;

import java.util.Map;

/**
 * This is a utility class which retrieves the current availability of a user according to their schedulerID.
 */
public final class Availability {

    private Availability() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Returns the availability from the provided schedulerID in Map format.
     * @param schedulerID The Slotify schedulerID of the scheduler representation the user's availability.
     * @return the availability in Map format, from its Slotify schedulerID for this user.
     */
    public static Map<Timeslot, Boolean> getAvailability(String schedulerID) {
        // Need to figure out how to retrieve the scheduler availability from Slotify
        // Store in JSONObejct and then convert to Map with for loop?
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//        sb.append("Availability {");
//        for (Map.Entry<Timeslot, Boolean> entry : getAvailability().entrySet()) {
//            Timeslot timeslot = entry.getKey();
//            Boolean isAvailable = entry.getValue();
//            if (isAvailable) {
//                sb.append("\n.             ").append(timeslot.toString());
//                count = count + 1;
//            }
//        }
//
//        if (count == 0) {
//            return "No availability";
//        }
//
//        sb.append("\n}");
//        return sb.toString();
//    }

}
