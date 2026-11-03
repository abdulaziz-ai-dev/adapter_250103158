package taks1;

public abstract class ExporterDecorator implements Exporter {
    protected final Exporter wrappedExporter;
    public ExporterDecorator(Exporter wrappedExporter) {
        if (wrappedExporter == null) {
            throw new IllegalArgumentException("Wrapped exporter cannot be null");
        }
        this.wrappedExporter = wrappedExporter;
    }
    @Override
    public void export(String destination, String data) {
        wrappedExporter.export(destination, data);
    }
}