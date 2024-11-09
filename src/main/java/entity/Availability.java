package entity;

import java.util.Map;

public class Availability {

    private Map<Timeslot, Boolean> availability;

    public Availability(Map<Timeslot, Boolean> availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Availability{");
        for (Map.Entry<Timeslot, Boolean> entry : availability.entrySet()) {
            Timeslot timeslot = entry.getKey();
            Boolean isAvailable = entry.getValue();
            sb.append("\n  ").append(timeslot.toString()).append(": ").append(isAvailable ? "Available" : "Unavailable");
        }
        sb.append("\n}");
        return sb.toString();
    }
}
