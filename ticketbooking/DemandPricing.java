public class DemandPricing implements PricingStrategy {
    public double apply(Show show, ShowSeat seat) {

        long booked = show.seats.values().stream()
                .filter(s -> s.status == SeatStatus.BOOKED)
                .count();

        double ratio = (double) booked / show.seats.size();

        if (ratio > 0.8) return 1.5;
        if (ratio > 0.5) return 1.2;
        return 1.0;
    }
}