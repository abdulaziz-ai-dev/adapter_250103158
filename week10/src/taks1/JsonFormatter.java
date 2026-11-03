package taks1;

public class JsonFormatter implements ReportFormatter {
    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n \"report\": \"").append(title).append("\",\n \"records\": [\n");
        for (int i = 0; i < rows.length; i++) {
            sb.append(" {");
            for (int j = 0; j < headers.length; j++) {
                sb.append("\"").append(headers[j]).append("\": \"").append(rows[i][j]).append("\"");
                if (j < headers.length - 1) sb.append(", ");
            }
            sb.append("}").append(i < rows.length - 1 ? ",\n" : "\n");
        }
        sb.append(" ]\n}");
        return sb.toString();
    }
}
