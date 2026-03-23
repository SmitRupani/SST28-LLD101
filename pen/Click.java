public class Click implements IMechanismType {
    @Override
    public void open() {
        System.out.println("Pen clicked open");
    }

    @Override
    public void close() {
        System.out.println("Pen clicked closed");
    }
}