package task3;

public class EmailChannel implements MessageChannel {

    @Override
    public void transmit(String recipient, String message) {
        System.out.println("[EMAIL PROTOCOL -> " + recipient + "]");
        System.out.println(message);
    }
}