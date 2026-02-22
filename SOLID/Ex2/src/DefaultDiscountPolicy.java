public class DefaultDiscountPolicy implements DiscountPolicy {
    @Override
    public double calculate(double subtotal, int distinctLines) { return 0.0; }
}