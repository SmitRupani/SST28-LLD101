public class Display {
    private int currentFloor;
    private Direction direction;

    public Display() {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }

    public void update(int floor, Direction dir) {
        this.currentFloor = floor;
        this.direction = dir;
        show();
    }

    public void show() {
        System.out.println("Display: Floor " + currentFloor + " [" + direction + "]");
    }
}
