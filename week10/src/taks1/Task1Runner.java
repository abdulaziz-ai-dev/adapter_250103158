package taks1;

public class Task1Runner {
    public static void main(String[] args) {
        String[] headers = {"ID", "User", "Role"};
        String[][] rows = {
                {"101", "sufyan", "Admin"},
                {"102", "alex", "Dev"}
        };
        // 1. Select Strategy
        ReportFormatter jsonStrategy = new JsonFormatter();
        String rawJson = jsonStrategy.format("UserAuditReport", headers, rows);

        Exporter securePipeline = new HeaderFooterDecorator(
                new Base64EncryptDecorator(
                        new BaseFileExporter()
                ),
                "2026-11-02 10:00:00 UTC"
        );
        securePipeline.export("/var/logs/audit.sec", rawJson);
    }
}
