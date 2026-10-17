
import java.util.Scanner;
import java.util.Random;

public class cab_booking {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {

                System.out.println("\nChoose Cab Type");
                System.out.println("1. Auto - Rs.12 per km");
                System.out.println("2. Sedan - Rs.18 per km");
                System.out.println("3. SUV - Rs.25 per km");
                System.out.print("Select cab type: ");

                String cabChoice = sc.nextLine();

                String cabType;
                int rate;

                if (cabChoice.equals("1")) {
                    cabType = "Auto";
                    rate = 12;
                } else if (cabChoice.equals("2")) {
                    cabType = "Sedan";
                    rate = 18;
                } else if (cabChoice.equals("3")) {
                    cabType = "SUV";
                    rate = 25;
                } else {
                    System.out.println("Invalid cab choice!");
                    continue;
                }

                System.out.println("\nChoose Route");
                System.out.println("1. Pune Station to Shivajinagar - 5 km");
                System.out.println("2. Swargate to Kothrud - 8 km");
                System.out.println("3. Hinjawadi to Wakad - 10 km");
                System.out.print("Select route: ");

                String routeChoice = sc.nextLine();

                String source;
                String destination;
                int distance;

                if (routeChoice.equals("1")) {
                    source = "Pune Station";
                    destination = "Shivajinagar";
                    distance = 5;
                } else if (routeChoice.equals("2")) {
                    source = "Swargate";
                    destination = "Kothrud";
                    distance = 8;
                } else if (routeChoice.equals("3")) {
                    source = "Hinjawadi";
                    destination = "Wakad";
                    distance = 10;
                } else {
                    System.out.println("Invalid route choice!");
                    continue;
                }

                int fare = rate * distance;

                System.out.println("\n===== RIDE DETAILS =====");
                System.out.println("Cab Type: " + cabType);
                System.out.println("Source: " + source);
                System.out.println("Destination: " + destination);
                System.out.println("Distance: " + distance + " km");
                System.out.println("Rate per km: Rs." + rate);
                System.out.println("Total Fare: Rs." + fare);

                System.out.println("\n1. Confirm and Share OTP");
                System.out.println("2. Cancel Ride");
                System.out.print("Enter your choice: ");

                String confirmChoice = sc.nextLine();

                if (confirmChoice.equals("2")) {
                    System.out.println("Ride cancelled successfully.");
                    System.out.println("No OTP has been shared.");
                    continue;
                } else if (confirmChoice.equals("1")) {

                    int otp = random.nextInt(9000) + 1000;

                    System.out.println("\nYour OTP is: " + otp);
                    System.out.println("OTP has been shared with the user.");
                    System.out.println(
                        "Cancellation is NOT allowed after OTP sharing."
                    );
                    System.out.println("Ride booking confirmed successfully!");

                } else {
                    System.out.println("Invalid confirmation choice.");
                    System.out.println("Ride not confirmed. No OTP shared.");
                }

            } else if (choice.equals("2")) {
                System.out.println("Thank you for using Cab Booking Chatbot!");
                System.out.println("Bye, visit again and Book Ride");
                break;
            } else {
                System.out.println("Invalid menu choice! Please enter 1 or 2.");
            }
        }

        sc.close();
    }
}
