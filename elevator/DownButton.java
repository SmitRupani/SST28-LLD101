public class DownButton extends Button {
    private final int floor;

    public DownButton(int floor) {
        this.floor = floor;
    }

    @Override
    protected void onPress() {
        System.out.println("Down button pressed at floor " + floor);
    }
}
