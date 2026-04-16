import java.util.List;

public interface ElevatorCallStrategy {
    Elevator selectElevator(Request request, List<Elevator> elevators);
}
