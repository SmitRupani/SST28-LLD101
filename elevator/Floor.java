public class Floor {
    private final int floorNumber;
    private final FloorPanel panel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.panel = new FloorPanel(floorNumber);
    }

    public void pressUp() {
        panel.getUpButton().press();
    }

    public void pressDown() {
        panel.getDownButton().press();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public FloorPanel getFloorPanel() {
        return panel;
    }
}
