public class FloorButton extends Button {
    private final int destinationFloor;

    public FloorButton(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    protected void onPress() {
        System.out.println("Floor button " + destinationFloor + " pressed");
    }
    
    public int getDestinationFloor() {
        return destinationFloor;
    }
}
