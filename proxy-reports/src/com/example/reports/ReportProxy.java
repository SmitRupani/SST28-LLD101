package com.example.reports;

/**
 * Proxy responsibilities:
 *   1. Access control  — checked before every display() call.
 *   2. Lazy loading    — RealReport is created only on first authorised access.
 *   3. Caching         — the same RealReport instance is reused for subsequent calls.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // null until first authorised access
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId       = reportId;
        this.title          = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Access check — never reaches real report if denied
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED — " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report " + reportId);
            return;
        }

        // 2. Lazy load — disk hit only on first authorised call
        if (realReport == null) {
            System.out.println("[proxy] First access — loading real report " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] Serving cached report " + reportId);
        }

        // 3. Delegate to real subject
        realReport.display(user);
    }
}