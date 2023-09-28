import java.util.Scanner;

public class ORS {
    private static String[] names = new String[50];
    private static String[] destinations = new String[50];
    private static int reservationCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Online Reservation System");
            System.out.println("1. Reserve a ticket");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your destination: ");
                    String destination = scanner.nextLine();
                    reserveTicket(name, destination);
                    break;
                case 2:
                    System.out.print("Enter your name to cancel the reservation: ");
                    String cancelName = scanner.nextLine();
                    cancelReservation(cancelName);
                    break;
                case 3:
                    System.out.println("Exiting the Online Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void reserveTicket(String name, String destination) {
        if (reservationCount < names.length) {
            names[reservationCount] = name;
            destinations[reservationCount] = destination;
            reservationCount++;
            System.out.println("Reservation successful for " + name + " to " + destination);
        } else {
            System.out.println("Sorry, the system is fully booked.");
        }
    }

    private static void cancelReservation(String name) {
        for (int i = 0; i < reservationCount; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(name)) {
                System.out.println("Reservation for " + name + " to " + destinations[i] + " canceled successfully.");
                names[i] = null;
                destinations[i] = null;
                return;
            }
        }
        System.out.println("Reservation not found for " + name);
    }
}
