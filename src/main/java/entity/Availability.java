package entity;

import java.util.Map;

/**
 * Represents the availability of a user.
 * This class provides a builder for creating instances of Availability.
 */
public class Availability {

    // Refer to the API documentation for the meaning of these fields.
    private Map<Timeslot, Boolean> availability;

    public Availability(Map<Timeslot, Boolean> availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Availability{" + '}';
    }

}
