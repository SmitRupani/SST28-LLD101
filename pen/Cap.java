public class Cap implements IMechanismType {
    @Override
    public void open() {
        System.out.println("Cap removed");
    }

    @Override
    public void close() {
        System.out.println("Cap closed");
    }
}