public class Invoice {
    public static String generateInvoice(Ticket ticket, double price) {
        Flight flight = ticket.getFlight();
        Passenger passenger = ticket.getPassenger();
        return "Invoice:\n" +
                "Ticket Number: " + ticket.getTicketNumber() + "\n" +
                "Flight Number: " + flight.getFlightNumber() + "\n" +
                "Passenger Name: " + passenger.getName() + "\n" +
                "Price: $" + price;
    }
}