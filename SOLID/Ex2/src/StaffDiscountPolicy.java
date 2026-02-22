public class StaffDiscountPolicy implements DiscountPolicy {
    @Override
    public double calculate(double subtotal, int distinctLines) {
        return distinctLines >= 3 ? 15.0 : 5.0;
    }
}