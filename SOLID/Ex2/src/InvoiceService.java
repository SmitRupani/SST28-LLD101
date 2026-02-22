import java.util.List;

public class InvoiceService {
    private final InvoiceStore store;
    private final PricingService pricingService;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public InvoiceService(InvoiceStore store, PricingService pricingService) {
        this.store = store;
        this.pricingService = pricingService;
        this.formatter = new PlainTextInvoiceFormatter();
    }

    public void generateInvoice(Menu menu, List<OrderLine> lines,
                                TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        String invId = "INV-" + (++invoiceSeq);
        Invoice invoice = pricingService.buildInvoice(invId, menu, lines, taxPolicy, discountPolicy);
        String text = formatter.format(invoice);
        System.out.print(text);
        store.save(invId, text);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}