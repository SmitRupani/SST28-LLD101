public interface PricingStrategy {
    double calculate(long entryTime, long exitTime, SlotType slotType);
}