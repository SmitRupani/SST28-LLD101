public class PlainTextInvoiceFormatter implements InvoiceFormatter {
    @Override
    public String format(Invoice inv) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(inv.id).append("\n");
        for (InvoiceLine l : inv.lines) {
            sb.append(String.format("- %s x%d = %.2f\n", l.itemName, l.qty, l.lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.tax));
        sb.append(String.format("Discount: -%.2f\n", inv.discount));
        sb.append(String.format("TOTAL: %.2f\n", inv.total));
        return sb.toString();
    }
}