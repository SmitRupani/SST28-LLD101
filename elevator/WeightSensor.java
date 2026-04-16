public class WeightSensor extends Sensor {
    private double currentWeight;
    private final double maxWeight;

    public WeightSensor(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void updateWeight(double weight) {
        this.currentWeight = weight;
        detect();
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    @Override
    public void detect() {
        if (currentWeight > maxWeight) {
            notifyAlert("Overweight detected! Current: " + currentWeight + "kg, Max: " + maxWeight + "kg");
        }
    }
}
