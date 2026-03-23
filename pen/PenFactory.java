public class PenFactory {

    public static Pen createPen(String type, String mechanismType, String refillType, String color) {

        IPenType penType = switch (type.toLowerCase()) {
            case "ball" -> new Ballpoint();
            case "gel" -> new Gel();
            case "ink" -> new Ink();
            default -> throw new IllegalArgumentException("Invalid pen type");
        };

        IMechanismType mechanism = switch (mechanismType.toLowerCase()) {
            case "cap" -> new Cap();
            case "click" -> new Click();
            default -> throw new IllegalArgumentException("Invalid mechanism type");
        };

        IRefillType refill = switch (refillType.toLowerCase()) {
            case "cartridge" -> new CartridgeFill();
            case "ink" -> new InkFill();
            default -> throw new IllegalArgumentException("Invalid refill type");
        };

        return new Pen(penType, mechanism, refill, color);
    }
}