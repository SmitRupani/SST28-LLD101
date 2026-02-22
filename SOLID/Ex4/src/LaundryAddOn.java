public class LaundryAddOn implements AddOnPricing {
    @Override public AddOn type() { return AddOn.LAUNDRY; }
    @Override public Money monthlyPrice() { return new Money(500.0); }
}