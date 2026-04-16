import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    private final List<Elevator> elevators;
    private final List<Floor> floors;
    private ElevatorCallStrategy callStrategy;

    public ElevatorManager(int totalFloors, int elevatorCount, double defaultMaxWeight, ElevatorCallStrategy strategy) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator("E" + (i + 1), totalFloors, defaultMaxWeight));
        }
        
        this.floors = new ArrayList<>();
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i));
        }
        
        this.callStrategy = strategy;

        for (Elevator elevator : elevators) {
            for (Floor floor : floors) {
                elevator.addObserver(floor.getFloorPanel());
            }
        }
    }

    public void handleOutsideRequest(Request request) {
        Elevator bestElevator = callStrategy.selectElevator(request, elevators);
        if (bestElevator != null) {
            System.out.println("Manager: Assigned Request (Floor " + request.getFloor() + ", Dir " + request.getDirection() + ") to Elevator " + bestElevator.getId());
            bestElevator.addRequest(request.getFloor());
        } else {
            System.out.println("Manager: No elevator available for request at floor " + request.getFloor());
        }
    }

    public void setCallStrategy(ElevatorCallStrategy strategy) {
        this.callStrategy = strategy;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getTotalFloors() {
        return floors.size();
    }
}
