public class FareCalculator implements IFareCalculator {
    @Override
    public double calculate(double km){
        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        return fare;
    }
}
