public class EmergencySensor extends Sensor {
    @Override
    public void detect() {
    }
    
    public void trigger() {
        notifyAlert("Emergency triggered manually!");
    }
}
