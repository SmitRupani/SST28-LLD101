public class CartridgeFill implements IRefillType {
    @Override
    public void refill(Pen pen, String newColor) {
        System.out.println("Refilling using cartridge with color " + newColor);
        pen.setColor(newColor);
    }
}