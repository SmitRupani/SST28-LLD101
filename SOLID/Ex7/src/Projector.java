public class Projector implements InputConnectable {

    @Override public void powerOn() { System.err.println("Projector ON"); }
    @Override public void powerOff() { System.out.println("Projector OFF"); }

    @Override public void connectInput(String port) { System.out.println("Projector ON (" + port + ")"); }
}