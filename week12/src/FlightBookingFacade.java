
public class FlightBookingFacade {

    // Encapsulated subsystem references
    private final SeatInventorySystem seatSystem;
    private final BaggagePricingEngine baggageEngine;
    private final CurrencyConverter currencyConverter;
    private final PaymentProcessor paymentProcessor;
    private final TicketIssuer ticketIssuer;

    public FlightBookingFacade() {
        this.seatSystem = new SeatInventorySystem();
        this.baggageEngine = new BaggagePricingEngine();
        this.currencyConverter = new CurrencyConverter();
        this.paymentProcessor = new PaymentProcessor();
        this.ticketIssuer = new TicketIssuer();
    }

    /**
     * Single entry point for booking a complete flight itinerary.
     * Coordinates seat locking, baggage pricing, currency conversion,
     * payment execution, rollback on failure, and ticket issuance.
     */
    public String bookFlight(String passengerName,
                             String flightCode,
                             String seatNumber,
                             double baseFareUSD,
                             int bagCount,
                             double bagWeightKg,
                             String currency,
                             String cardNumber) {

        return bookFlight(passengerName, flightCode, seatNumber,
                baseFareUSD, bagCount, bagWeightKg,
                currency, cardNumber, "STANDARD");
    }

    public String bookFlight(String passengerName,
                             String flightCode,
                             String seatNumber,
                             double baseFareUSD,
                             int bagCount,
                             double bagWeightKg,
                             String currency,
                             String cardNumber,
                             String loyaltyTier) {

        String lockToken = null;

        try {
            // TODO 1: Connect to seat server and lock the requested seat.
            seatSystem.connectToReservationServer();
            lockToken = seatSystem.lockSeat(flightCode, seatNumber, passengerName);

            // TODO 2: Compute baggage fee in USD and add to baseFareUSD.
            double baggageFee = 0.0;

            if (!"SILVER".equalsIgnoreCase(loyaltyTier)
                    && !"GOLD".equalsIgnoreCase(loyaltyTier)) {
                baggageFee = baggageEngine.calculateBaggageFeeUSD(bagCount, bagWeightKg);
            }

            double totalUSD = baseFareUSD + baggageFee;

            // TODO 3: Convert total USD amount into target currency.
            double convertedTotal = currencyConverter.convertFromUSD(totalUSD, currency);

            // TODO 4: Initialize payment gateway and charge the card.
            paymentProcessor.initializeGateway();

            if ("SILVER".equalsIgnoreCase(loyaltyTier)) {
                paymentProcessor.applyPromoCode("SILVER-FREE-BAG");
            }

            String receiptId = paymentProcessor.chargeCard(cardNumber, convertedTotal, currency);

            // TODO 5: Generate and return the final e-ticket code.
            return ticketIssuer.generateTicket(passengerName, flightCode, lockToken, receiptId);

        } catch (RuntimeException ex) {
            // TODO 6: If a seat was already locked, release it before reporting failure.
            if (lockToken != null) {
                seatSystem.releaseSeatLock(lockToken);
            }

            return "BOOKING_FAILED: " + ex.getMessage();

        } finally {
            // TODO 7: Ensure seat server disconnects regardless of success or failure.
            seatSystem.disconnect();
        }
    }
}
