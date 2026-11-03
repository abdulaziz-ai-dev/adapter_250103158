package taks1;

public class Base64EncryptDecorator extends ExporterDecorator {
    public Base64EncryptDecorator(Exporter wrappedExporter) {
        super(wrappedExporter);
    }
    @Override
    public void export(String destination, String data) {
        // Base64 encode the incoming stream before passing downstream
        String encoded = java.util.Base64.getEncoder().encodeToString(data.getBytes());
        String payload = "[BASE64_CIPHERTEXT:" + encoded + "]";
        super.export(destination, payload);
    }
}

