public class InkFill implements IRefillType {
    @Override
    public void refill(Pen pen, String newColor) {
        System.out.println("Refilling using ink bottle with color " + newColor);
        pen.setColor(newColor);
    }
}