public class CustomerPolicy {
    public final TaxPolicy tax;
    public final DiscountPolicy discount;

    public CustomerPolicy(TaxPolicy tax, DiscountPolicy discount) {
        this.tax = tax; this.discount = discount;
    }
}