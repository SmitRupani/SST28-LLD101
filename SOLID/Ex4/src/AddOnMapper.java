import java.util.Map;

public class AddOnMapper {
    private final Map<AddOn, AddOnPricing> pricing;

    public AddOnMapper(Map<AddOn, AddOnPricing> pricing) {
        this.pricing = pricing;
    }

    public Money priceFor(AddOn addOn) {
        AddOnPricing p = pricing.get(addOn);
        return p != null ? p.monthlyPrice() : new Money(0.0);
    }
}