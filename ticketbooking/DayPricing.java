public class DayPricing implements PricingStrategy {
    public double apply(Show show, ShowSeat seat) {
        java.time.DayOfWeek day = show.startTime.getDayOfWeek();
        if (day == java.time.DayOfWeek.SATURDAY ||
            day == java.time.DayOfWeek.SUNDAY) {
            return 1.2;
        }
        return 1.0;
    }
}