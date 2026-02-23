import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public boolean canExport(ExportRequest req) {
        return req != null;
    }

    @Override
    public ExportResult export(ExportRequest req) {
        String title = req.title == null ? "" : req.title;
        String body  = req.body  == null ? "" : req.body;
        // Properly quote fields containing commas or newlines
        String csv = "title,body\n" + quote(title) + "," + quote(body) + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    protected String quote(String s) {
        if (s.contains(",") || s.contains("\n") || s.contains("\"")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}