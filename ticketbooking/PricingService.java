import java.util.List;

public class PricingService {

    List<PricingStrategy> strategies = List.of(
            new SeatTypePricing(),
            new DayPricing(),
            new DemandPricing()
    );

    double calculateTotal(Show show, List<ShowSeat> seats) {

        double total = 0;

        for (ShowSeat seat : seats) {

            double base = 0;
            double multiplier = 1;

            for (PricingStrategy strategy : strategies) {
                double val = strategy.apply(show, seat);

                if (val > 10) base += val;
                else multiplier *= val;
            }

            total += base * multiplier;
        }

        return total;
    }
}