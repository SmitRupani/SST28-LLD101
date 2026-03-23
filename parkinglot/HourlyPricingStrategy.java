
import java.util.HashMap;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {

    Map<SlotType, Double> rates = new HashMap<>();

    HourlyPricingStrategy() {
        rates.put(SlotType.SMALL, 10.0);
        rates.put(SlotType.MEDIUM, 20.0);
        rates.put(SlotType.LARGE, 30.0);
    }

    public double calculate(long entryTime, long exitTime, SlotType slotType) {
        long hours = Math.max(1, (exitTime - entryTime) / (1000 * 60 * 60));
        return hours * rates.get(slotType);
    }
}