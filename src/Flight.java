public class Flight {
    private String flightNumber;
    private String fromDestination;
    private String toDestination;
    private int capacity;
    private int duration;

    public Flight(String flightNumber, String fromDestination, String toDestination, int capacity, int duration) {
        this.flightNumber = flightNumber;
        this.fromDestination = fromDestination;
        this.toDestination = toDestination;
        this.capacity = capacity;
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public String getToDestination() {
        return toDestination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return flightNumber + " (" + fromDestination + " to " + toDestination + ")";
    }
}