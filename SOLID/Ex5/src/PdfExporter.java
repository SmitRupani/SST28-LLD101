import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_BODY_LENGTH = 20;

    @Override
    public boolean canExport(ExportRequest req) {
        return req != null
            && req.body != null
            && req.body.length() <= MAX_BODY_LENGTH;
    }

    @Override
    public String rejectionReason(ExportRequest req) {
        return "PDF cannot handle content > " + MAX_BODY_LENGTH + " chars";
    }

    @Override
    public ExportResult export(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}