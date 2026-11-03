package task3;

public class CriticalSystemAlert extends Alert {

    public CriticalSystemAlert(MessageChannel channel, DispatchStrategy strategy) {
        super(channel, strategy);
    }

    @Override
    public int getSeverityLevel() {
        return 5;
    }

    @Override
    public String formatAlertBody(String message) {
        return "!!! CRITICAL PRODUCTION ALERT !!!\n"
                + "Incident Report: " + message + "\n"
                + "ACTION REQUIRED: SRE on-call immediate intervention.";
    }
}