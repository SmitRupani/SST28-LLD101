import java.util.List;

public class CafeteriaSystem {
    private final Menu menu;
    private final PolicyResolver resolver;
    private final InvoiceService invoiceService;

    public CafeteriaSystem(Menu menu, InvoiceStore store, PricingService pricingService, PolicyResolver resolver) {
        this.menu = menu;
        this.resolver = resolver;
        this.invoiceService = new InvoiceService(store, pricingService);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        CustomerPolicy policy = resolver.resolve(customerType);
        invoiceService.generateInvoice(menu, lines, policy.tax, policy.discount);
    }
}