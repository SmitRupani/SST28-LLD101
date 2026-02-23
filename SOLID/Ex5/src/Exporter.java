public abstract class Exporter {
    /*
    Called before export() to check if this exporter can handle the request.
     */
    public abstract boolean canExport(ExportRequest req);

    /*
    Exports the request. Only called if canExport() returns true.
     */
    public abstract ExportResult export(ExportRequest req);

    /*
    Describes why this exporter cannot handle the request.
     */
    public String rejectionReason(ExportRequest req) {
        return getClass().getSimpleName() + " cannot handle this request";
    }
}