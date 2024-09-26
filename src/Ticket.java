public class Ticket {
    private static int ticketCounter = 1000;
    private int ticketNumber;
    private Flight flight;
    private Passenger passenger;

    public Ticket(Flight flight, Passenger passenger) {
        this.ticketNumber = ticketCounter++;
        this.flight = flight;
        this.passenger = passenger;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}