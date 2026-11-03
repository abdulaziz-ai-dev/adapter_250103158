package task3;

public abstract class Alert {

    protected final MessageChannel channel;
    protected final DispatchStrategy strategy;

    public Alert(MessageChannel channel, DispatchStrategy strategy) {
        if (channel == null || strategy == null) {
            throw new IllegalArgumentException("Channel and strategy are required");
        }

        this.channel = channel;
        this.strategy = strategy;
    }

    public void processAlert(String recipient, String message, int currentHour) {

        int severity = getSeverityLevel();

        if (!strategy.shouldDispatch(severity, currentHour)) {
            System.out.printf(
                    "[DISPATCH SUPPRESSED] Alert blocked by policy '%s' at hour %02d:00 (Severity: %d)%n",
                    strategy.getStrategyName(), currentHour, severity
            );
            return;
        }

        channel.transmit(recipient, formatAlertBody(message));
    }

    public abstract int getSeverityLevel();

    public abstract String formatAlertBody(String message);
}