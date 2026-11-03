package task3;

public class SmsChannel implements MessageChannel {

    @Override
    public void transmit(String recipient, String message) {
        String smsText = message.replace("\n", " -- ");

        System.out.println("[SMS PROTOCOL -> " + recipient + "] " + smsText);
    }
}