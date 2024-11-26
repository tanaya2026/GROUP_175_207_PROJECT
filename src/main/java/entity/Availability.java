package entity;

import data_access.DataAccessObject;

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
        DataAccessObject db = new DataAccessObject();
        return db.fetchAvailability(schedulerID);
    }

}
