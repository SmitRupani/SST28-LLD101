import java.util.*;

public class PricingService {
    public Invoice buildInvoice(String invId, Menu menu, List<OrderLine> orderLines,
                                TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        List<InvoiceLine> lines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine ol : orderLines) {
            MenuItem item = menu.get(ol.itemId);
            double lineTotal = item.price * ol.qty;
            subtotal += lineTotal;
            lines.add(new InvoiceLine(item.name, ol.qty, lineTotal));
        }

        double tax      = taxPolicy.calculateTax(subtotal);
        double taxPct   = taxPolicy.getPercent();
        double discount = discountPolicy.calculate(subtotal, orderLines.size());
        double total    = subtotal + tax - discount;

        return new Invoice(invId, lines, subtotal, taxPct, tax, discount, total);
    }
}