public class Door {
    private boolean isOpen;

    public Door() {
        this.isOpen = false;
    }

    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Elevator door opened.");
        }
    }

    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Elevator door closed.");
        }
    }

    public boolean isOpen() {
        return isOpen;
    }
}
