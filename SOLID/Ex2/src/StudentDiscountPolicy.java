public class StudentDiscountPolicy implements DiscountPolicy {
    @Override
    public double calculate(double subtotal, int distinctLines) {
        return subtotal >= 180.0 ? 10.0 : 0.0;
    }
}