public class Pen {

    private final IPenType penType;
    private final IMechanismType mechanism;
    private final IRefillType refillType;
    private String color;
    private boolean isOpen;

    public Pen(IPenType penType, IMechanismType mechanism, IRefillType refillType, String color) {
        this.penType = penType;
        this.mechanism = mechanism;
        this.refillType = refillType;
        this.color = color;
        this.isOpen = false;
    }

    public String write(String text) {
        if (!isOpen) {
            return "Cannot write. Pen is closed.";
        }
        return penType.write(text, color);
    }

    public void open() {
        mechanism.open();
        isOpen = true;
    }

    public void close() {
        mechanism.close();
        isOpen = false;
    }

    public void refill(String newColor) {
        refillType.refill(this, newColor);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}