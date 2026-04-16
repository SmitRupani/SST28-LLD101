public abstract class Sensor {
    public abstract void detect();
    
    protected void notifyAlert(String message) {
        System.out.println("Sensor Alert: " + message);
    }
}
