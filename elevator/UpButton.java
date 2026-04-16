public class UpButton extends Button {
    private final int floor;

    public UpButton(int floor) {
        this.floor = floor;
    }

    @Override
    protected void onPress() {
        System.out.println("Up button pressed at floor " + floor);
    }
}
