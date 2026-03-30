public interface PricingStrategy {
    double apply(Show show, ShowSeat seat);
}