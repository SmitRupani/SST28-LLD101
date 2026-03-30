public class SeatTypePricing implements PricingStrategy {
    public double apply(Show show, ShowSeat seat) {
        switch (seat.seat.type) {
            case GOLD: return 200;
            case SILVER: return 150;
            case PLATINUM: return 300;
        }
        return 100;
    }
}