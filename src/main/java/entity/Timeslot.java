package entity;

/**
 * Represents a timeslot which is used to define the availability of users.
 */
public class Timeslot {

    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 3;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;

    // Weekday in int format, where 1 = Monday, 2 = Tuesday, ..., 7 = Saturday.
    private final int day;
    // Start time in 24-hour int format, where 0 = 00:00, 1 = 01:00, ..., 23 = 23:00
    private final int time;

    public Timeslot(int day, int time) {
        this.day = day;
        this.time = time;
    }

    @Override
    public String toString() {
        String name = this.dayName();
        String times = this.timeName();
        return "Timeslot { day: " + name + ", time: " + times + '}';
    }

    /**
     * Returns the int of day of the timeslot.
     * @return the int day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the int of start time of the timeslot.
     * @return the int time.
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the name of day of the timeslot.
     * @return the name of the day, from its int value.
     * @throws IllegalArgumentException if the day is an unexpected value
     */
    public String dayName() {
        String name;
        switch (day) {
            case MONDAY:
                name = "Monday";
                break;
            case TUESDAY:
                name = "Tuesday";
                break;
            case WEDNESDAY:
                name = "Wednesday";
                break;
            case THURSDAY:
                name = "Thursday";
                break;
            case FRIDAY:
                name = "Friday";
                break;
            case SATURDAY:
                name = "Saturday";
                break;
            case SUNDAY:
                name = "Sunday";
                break;
            default:
                throw new IllegalArgumentException("Invalid day: " + day);
        }
        return name;
    }

    /**
     * Returns the start and end time of the timeslot.
     * @return the start and end time of the timeslot in format HH:00-HH:00.
     */
    public String timeName() {
        String times = String.valueOf(time) + ":00-" + String.valueOf(time + 1) + ":00";
        return times;
    }

}
