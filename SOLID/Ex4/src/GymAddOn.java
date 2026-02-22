public class GymAddOn implements AddOnPricing {
    @Override public AddOn type() { return AddOn.GYM; }
    @Override public Money monthlyPrice() { return new Money(300.0); }
}