import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlightManagementSystemGUI extends JFrame {

    // list for flights, passengers and tickets which are booked
    private ArrayList<Flight> flightsList = new ArrayList<>();
    private ArrayList<Passenger> passengersList = new ArrayList<>();
    private ArrayList<Ticket> ticketsList = new ArrayList<>();


    public FlightManagementSystemGUI() {
        setTitle("Flight Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        //buttons
        JButton addFlightButton = new JButton("Add Flight");
        JButton addPassengerButton = new JButton("Add Passenger");
        JButton showFlightsButton = new JButton("Show Flights");
        JButton showPassengersButton = new JButton("Show Passengers");
        JButton bookTicketButton = new JButton("Book Ticket");
        JButton generateInvoiceButton = new JButton("Generate Invoice");

        panel.add(addFlightButton);
        panel.add(addPassengerButton);
        panel.add(showFlightsButton);
        panel.add(showPassengersButton);
        panel.add(bookTicketButton);
        panel.add(generateInvoiceButton);
        add(panel);

        // functions on press of button
        addFlightButton.addActionListener(e -> addFlight());
        showFlightsButton.addActionListener(e -> showFlights());
        showPassengersButton.addActionListener(e -> showPassengers());
        bookTicketButton.addActionListener(e -> bookTicket());
        generateInvoiceButton.addActionListener(e -> generateInvoice());
        addPassengerButton.addActionListener(e -> addPassenger());
        setVisible(true);
    }



    private void addFlight() {
        JTextField flightNumber = new JTextField(5);
        JTextField from = new JTextField(5);
        JTextField to = new JTextField(5);
        JTextField capacity = new JTextField(5);
        JTextField duration = new JTextField(5);

        JPanel addFlightPanel = new JPanel();
        addFlightPanel.setLayout(new GridLayout(5, 2));
        addFlightPanel.add(new JLabel("Flight Number:"));
        addFlightPanel.add(flightNumber);
        addFlightPanel.add(new JLabel("From:"));
        addFlightPanel.add(from);
        addFlightPanel.add(new JLabel("To:"));
        addFlightPanel.add(to);
        addFlightPanel.add(new JLabel("Capacity:"));
        addFlightPanel.add(capacity);
        addFlightPanel.add(new JLabel("Duration (hours):"));
        addFlightPanel.add(duration);


        int result = JOptionPane.showConfirmDialog(null, addFlightPanel,
                "Add Flight", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Flight flight = new Flight(flightNumber.getText(), from.getText(),
                    to.getText(), Integer.parseInt(capacity.getText()),
                    Integer.parseInt(duration.getText()));
            flightsList.add(flight);
            JOptionPane.showMessageDialog(this, "Flight added successfully.");
        }
    }

    private void addPassenger() {
        JTextField name = new JTextField(5);
        JTextField passportNumber = new JTextField(5);
        JTextField age = new JTextField(5);

        JPanel addPassengerPanel = new JPanel();
        addPassengerPanel.setLayout(new GridLayout(3, 2));
        addPassengerPanel.add(new JLabel("Name:"));
        addPassengerPanel.add(name);
        addPassengerPanel.add(new JLabel("Passport Number:"));
        addPassengerPanel.add(passportNumber);
        addPassengerPanel.add(new JLabel("Age:"));
        addPassengerPanel.add(age);

        int result = JOptionPane.showConfirmDialog(null, addPassengerPanel,
                "Add Passenger", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Passenger passenger = new Passenger(name.getText(), passportNumber.getText(),
                    Integer.parseInt(age.getText()));
            passengersList.add(passenger);
            JOptionPane.showMessageDialog(this, "Passenger added successfully.");
        }
    }

    private void bookTicket() {
        if (flightsList.isEmpty() || passengersList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flights or passengers available.");
            return;
        }

        Flight flight = (Flight) JOptionPane.showInputDialog(this, "Select Flight",
                "Book Ticket", JOptionPane.QUESTION_MESSAGE, null, flightsList.toArray(), flightsList.get(0));
        Passenger passenger = (Passenger) JOptionPane.showInputDialog(this, "Select Passenger",
                "Book Ticket", JOptionPane.QUESTION_MESSAGE, null, passengersList.toArray(), passengersList.get(0));

        if (flight != null && passenger != null) {
            Ticket ticket = new Ticket(flight, passenger);
            ticketsList.add(ticket);
            JOptionPane.showMessageDialog(this, "Ticket booked! Ticket No: " + ticket.getTicketNumber());
        }
    }

    private void generateInvoice() {
        String ticketNumberStr = JOptionPane.showInputDialog(this, "Enter Ticket Number:");

        int ticketNumber = Integer.parseInt(ticketNumberStr);
        Ticket foundTicket = null;

        for (Ticket ticket : ticketsList) {
            if (ticket.getTicketNumber() == ticketNumber) {
                foundTicket = ticket;
                break;
            }
        }

        if (foundTicket != null) {
            int duration = foundTicket.getFlight().getDuration();
            double price = duration * 20.0;
            String invoice = "Ticket Number: " + foundTicket.getTicketNumber() + "\n" +
                    "Passenger: " + foundTicket.getPassenger().getName() + "\n" +
                    "Flight: " + foundTicket.getFlight().getFlightNumber() + "\n" +
                    "Total Price: $" + price;
            JOptionPane.showMessageDialog(this, invoice);
        } else {
            JOptionPane.showMessageDialog(this, "Ticket not found.");
        }
    }

    private void showFlights() {
        if (flightsList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flights available.");
        } else {
            String flightsInfo = "";
            for (Flight flight : flightsList) {
                flightsInfo += "Flight Number: " + flight.getFlightNumber() + ", From: " +
                        flight.getFromDestination() + ", To: " + flight.getToDestination() +
                        ", Capacity: " + flight.getCapacity() + ", Duration: " +
                        flight.getDuration() + " hours\n";
            }
            JOptionPane.showMessageDialog(this, flightsInfo, "Available Flights", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showPassengers() {
        if (passengersList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No passengers available.");
        }
        else {
            StringBuilder passengersInfo = new StringBuilder();
            for (Passenger passenger : passengersList) {
                passengersInfo.append("Name: ").append(passenger.getName()).append(", ")
                        .append("Passport Number: ").append(passenger.getPassportNumber()).append(", ")
                        .append("Age: ").append(passenger.getAge()).append("\n");
            }

            JOptionPane.showMessageDialog(this, passengersInfo.toString(), "Available Passengers", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FlightManagementSystemGUI();
    }
}