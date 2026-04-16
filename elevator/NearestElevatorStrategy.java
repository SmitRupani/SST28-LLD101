import java.util.List;

public class NearestElevatorStrategy implements ElevatorCallStrategy {
    @Override
    public Elevator selectElevator(Request request, List<Elevator> elevators) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getState() == ElevatorState.UNDER_MAINTENANCE) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
            boolean canService = false;

            if (elevator.getState() == ElevatorState.IDLE) {
                canService = true;
            } else if (elevator.getCurrentDirection() == request.getDirection()) {
                if (elevator.getCurrentDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getFloor()) {
                    canService = true;
                } else if (elevator.getCurrentDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getFloor()) {
                    canService = true;
                }
            }

            if (canService && distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                if (elevator.getState() == ElevatorState.UNDER_MAINTENANCE) continue;
                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        return bestElevator;
    }
}
