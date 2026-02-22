public class MessAddOn implements AddOnPricing {
    @Override public AddOn type() { return AddOn.MESS; }
    @Override public Money monthlyPrice() { return new Money(1000.0); }
}