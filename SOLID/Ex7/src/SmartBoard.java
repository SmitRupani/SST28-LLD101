public class SmartBoard implements InputConnectable {

    @Override 
    public void powerOn() {
        System.out.println("SmartBoard ON");
    }

    @Override 
    public void powerOff() {
        System.out.println("SmartBoard OFF");
    }

    @Override
    public void connectInput(String port) {
        System.out.println("SmartBoard connected to " + port);
    }
}
