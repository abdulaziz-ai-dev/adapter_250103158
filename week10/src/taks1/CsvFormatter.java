package taks1;

public class CsvFormatter implements ReportFormatter {
    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("# TITLE: ").append(title).append("\n");
        sb.append(String.join(",", headers)).append("\n");
        for (String[] row : rows) {
            sb.append(String.join(",", row)).append("\n");
        }
        return sb.toString();
    }
}
