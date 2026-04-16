import java.util.List;

public class FCFSStrategy implements ElevatorCallStrategy {
    @Override
    public Elevator selectElevator(Request request, List<Elevator> elevators) {
        if (elevators.isEmpty()) return null;
        return elevators.get(0);
    }
}
