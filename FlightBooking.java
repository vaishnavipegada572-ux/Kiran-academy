import java.util.ArrayList;
import java.util.Scanner;

// Class 1: Flight
class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline, String source,String destination, int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean reserveSeats(int tickets) {
        if (tickets > 0 && tickets <= availableSeats) {
            availableSeats -= tickets;
            return true;
        }
        return false;
    }

    public void displayFlight() {
        System.out.println("Flight Code: " + flightCode
                + " | Airline: " + airline
                + " | Route: " + source + " to " + destination
                + " | Seats: " + availableSeats
                + " | Base Fare: Rs. " + baseFare);
    }
}

// Class 2: Passenger (Abstract Class)
abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountPercentage();

    public abstract String getPassengerType();
}

// Class 3: RegularPassenger
class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}

// Class 4: StudentPassenger
class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 10.0;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}

// Class 5: Booking
class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;
    private double subtotal;
    private double discount;
    private double finalAmount;

    public Booking(String bookingId, Flight flight,Passenger passenger, int tickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;

        calculateFare();
    }

    public void calculateFare() {
        subtotal = flight.getBaseFare() * tickets;
        discount = subtotal * passenger.getDiscountPercentage() / 100;
        finalAmount = subtotal - discount;
    }

    public void displaySummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Code: " + flight.getFlightCode());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Route: " + flight.getSource()
                + " to " + flight.getDestination());
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Passenger Type: " + passenger.getPassengerType());
        System.out.println("Tickets: " + tickets);
        System.out.printf("Base Fare per Ticket: Rs. %.2f%n",
                flight.getBaseFare());
        System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%): Rs. %.2f%n",
                passenger.getDiscountPercentage(), discount);
        System.out.printf("Final Amount: Rs. %.2f%n", finalAmount);
        System.out.println("======================================");
    }
}

// Class 6: FlightBookingSystem
class FlightBookingSystem {
    private ArrayList<Flight> flights;
    private ArrayList<Booking> bookings;
    private Scanner scanner;
    private int nextBookingNumber;

    public FlightBookingSystem() {
        flights = new ArrayList<>();
        bookings = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextBookingNumber = 1001;

        flights.add(new Flight("PN101", "Air India",
                "Pune", "Delhi", 20, 4000));
        flights.add(new Flight("MB202", "IndiGo",
                "Mumbai", "Bengaluru", 25, 3500));
        flights.add(new Flight("PN303", "Akasa Air",
                "Pune", "Hyderabad", 15, 3000));
    }

    public void displayFlights() {
        System.out.println("\n========== AVAILABLE FLIGHTS ==========");
        for (Flight flight : flights) {
            flight.displayFlight();
        }
        System.out.println("=======================================");
    }

    public Flight findFlightByCode(String code) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }
        return null;
    }

    private Integer readInteger(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid whole number.");
            return null;
        }
    }

    public boolean validateBookingDetails(Flight flight,String name, int age,int tickets) {
        if (flight == null) {
            System.out.println("Error: Flight code does not exist.");
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Passenger name cannot be blank.");
            return false;
        }

        if (age <= 0) {
            System.out.println("Error: Age must be greater than zero.");
            return false;
        }

        if (tickets <= 0) {
            System.out.println("Error: Ticket count must be greater than zero.");
            return false;
        }

        if (tickets > flight.getAvailableSeats()) {
            System.out.println("Booking failed: Not enough seats available.");
            return false;
        }

        return true;
    }

    public void bookFlight() {
        System.out.println("\n========== BOOK A FLIGHT ==========");

        System.out.print("Enter flight code: ");
        String code = scanner.nextLine().trim();
        Flight flight = findFlightByCode(code);

        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine().trim();

        Integer age = readInteger("Enter age: ");
        if (age == null) {
            return;
        }

        System.out.print("Passenger type (Regular/Student): ");
        String type = scanner.nextLine().trim();

        Passenger passenger;

        if (type.equalsIgnoreCase("Regular")) {
            passenger = new RegularPassenger(name, age);
        } else if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else {
            System.out.println("Error: Choose Regular or Student.");
            return;
        }

        Integer tickets = readInteger("Enter number of tickets: ");
        if (tickets == null) {
            return;
        }

        // Validate all details before changing seat counts.
        if (!validateBookingDetails(flight, name, age, tickets)) {
            System.out.println("No booking was created.");
            return;
        }

        // Reserve seats only after all validation succeeds.
        if (flight.reserveSeats(tickets)) {
            String bookingId = "BK" + nextBookingNumber++;
            Booking booking = new Booking(
                    bookingId, flight, passenger, tickets);

            bookings.add(booking);

            System.out.println("\nBooking confirmed!");
            booking.displaySummary();
        } else {
            System.out.println("Booking failed: Seats are unavailable.");
            System.out.println("No booking was created.");
        }
    }

    public void viewBooking() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo confirmed bookings available.");
            return;
        }

        // Display the most recent confirmed booking.
        bookings.get(bookings.size() - 1).displaySummary();
    }

    public void start() {
        while (true) {
            System.out.println("\n===== WELCOME TO KIRAN AIR BOOKING =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");

            Integer choice = readInteger("Enter choice: ");

            if (choice == null) {
                continue;
            }

            switch (choice) {
                case 1:
                    displayFlights();
                    break;
                case 2:
                    bookFlight();
                    break;
                case 3:
                    viewBooking();
                    break;
                case 4:
                    System.out.println(
                            "Thank you for using Kiran Air Booking!");
                    return;
                default:
                    System.out.println(
                            "Invalid choice. Please select 1, 2, 3 or 4.");
            }
        }
    }
}

// Class 7: Main Class
public class FlightBooking {
    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.start();
    }
}

    

