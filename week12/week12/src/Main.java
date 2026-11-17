
public class Main {
    public static void main(String[] args) {
        // Notice: Main knows NOTHING about the 5 underlying subsystems.
        FlightBookingFacade bookingFacade = new FlightBookingFacade();

        System.out.println("=== TEST 1: Standard Successful Booking ===");
        String ticket1 = bookingFacade.bookFlight(
                "Amina Suleimenova",
                "KC101",
                "14A",
                320.00,
                2,
                23.0,
                "EUR",
                "4532015112830366"
        );
        System.out.println("Result 1: " + ticket1 + "\n");

        System.out.println("=== TEST 2: Blocked Crew Seat (Row 13) ===");
        String ticket2 = bookingFacade.bookFlight(
                "Daniyar Kassymov",
                "KC101",
                "13C",
                320.00,
                1,
                15.0,
                "USD",
                "4532015112830366"
        );
        System.out.println("Result 2: " + ticket2 + "\n");

        System.out.println("=== TEST 3: Payment Failure & Automatic Seat Rollback ===");
        String ticket3 = bookingFacade.bookFlight(
                "Timur Bekov",
                "KC101",
                "07D",
                320.00,
                1,
                18.0,
                "KZT",
                "9999" // Invalid short card number triggers rollback
        );
        System.out.println("Result 3: " + ticket3 + "\n");

        System.out.println("=== TEST 4: Silver Loyalty ===");
        String ticket4 = bookingFacade.bookFlight(
                "Ali Nur",
                "KC101",
                "15B",
                320.00,
                3,
                30.0,
                "USD",
                "4532015112830366",
                "SILVER"
        );
        System.out.println("Result 4: " + ticket4 + "\n");
    }
}
