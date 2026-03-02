public class LightsPanel implements Dimmable {
    @Override public void powerOn() { System.out.println("Lights ON"); }
    @Override public void powerOff() { System.out.println("Lights OFF"); }
    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }
}