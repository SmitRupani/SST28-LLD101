import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class Elevator {
    private final String id;
    private int currentFloor;
    private ElevatorState state;
    private Direction currentDirection;
    private double currentWeight;
    private final double maxWeight;
    private final Door door;
    private final InsidePanel insidePanel;
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;
    private final List<Sensor> sensors;
    private final List<ElevatorObserver> observers;

    public Elevator(String id, int totalFloors, double maxWeight) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.currentDirection = Direction.IDLE;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.door = new Door();
        this.insidePanel = new InsidePanel(totalFloors);
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
        this.sensors = new ArrayList<>();
        this.observers = new ArrayList<>();
        
        sensors.add(new WeightSensor(maxWeight));
        sensors.add(new EmergencySensor());
        addObserver(insidePanel);
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ElevatorObserver observer : observers) {
            observer.onStateUpdate(currentFloor, currentDirection);
        }
    }

    public void addInsideRequest(int floor) {
        if (state == ElevatorState.UNDER_MAINTENANCE) return;
        System.out.println("Elevator " + id + ": Inside request for floor " + floor);
        addRequest(floor);
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) {
            upRequests.add(floor);
        } else if (floor < currentFloor) {
            downRequests.add(floor);
        } else {
            door.open();
            door.close();
            return;
        }
        
        if (state == ElevatorState.IDLE) {
            if (floor > currentFloor) {
                state = ElevatorState.MOVING_UP;
                currentDirection = Direction.UP;
            } else if (floor < currentFloor) {
                state = ElevatorState.MOVING_DOWN;
                currentDirection = Direction.DOWN;
            }
        }
        notifyObservers();
    }

    public void move() {
        if (state == ElevatorState.IDLE || state == ElevatorState.UNDER_MAINTENANCE || state == ElevatorState.OVERLOADED) {
            if (state == ElevatorState.IDLE) {
                checkAndResume();
            }
            return;
        }

        if (currentDirection == Direction.UP) {
            if (upRequests.isEmpty()) {
                if (downRequests.isEmpty()) {
                    state = ElevatorState.IDLE;
                    currentDirection = Direction.IDLE;
                } else {
                    currentDirection = Direction.DOWN;
                    state = ElevatorState.MOVING_DOWN;
                }
            } else {
                currentFloor++;
                System.out.println("Elevator " + id + " moved UP to floor " + currentFloor);
                if (upRequests.contains(currentFloor)) {
                    stopAtFloor(currentFloor);
                    upRequests.remove(currentFloor);
                } else {
                    notifyObservers();
                }
            }
        } else if (currentDirection == Direction.DOWN) {
            if (downRequests.isEmpty()) {
                if (upRequests.isEmpty()) {
                    state = ElevatorState.IDLE;
                    currentDirection = Direction.IDLE;
                } else {
                    currentDirection = Direction.UP;
                    state = ElevatorState.MOVING_UP;
                }
            } else {
                currentFloor--;
                System.out.println("Elevator " + id + " moved DOWN to floor " + currentFloor);
                if (downRequests.contains(currentFloor)) {
                    stopAtFloor(currentFloor);
                    downRequests.remove(currentFloor);
                } else {
                    notifyObservers();
                }
            }
        }
    }

    private void checkAndResume() {
        if (!upRequests.isEmpty()) {
            Integer nextUp = upRequests.ceiling(currentFloor);
            if (nextUp != null) {
                state = ElevatorState.MOVING_UP;
                currentDirection = Direction.UP;
            } else if (!downRequests.isEmpty()) {
                state = ElevatorState.MOVING_DOWN;
                currentDirection = Direction.DOWN;
            }
        } else if (!downRequests.isEmpty()) {
            state = ElevatorState.MOVING_DOWN;
            currentDirection = Direction.DOWN;
        }
        notifyObservers();
    }

    private void stopAtFloor(int floor) {
        state = ElevatorState.IDLE;
        System.out.println("Elevator " + id + " stopped at floor " + floor);
        door.open();
        door.close();
        
        if (!upRequests.isEmpty() || !downRequests.isEmpty()) {
             if (currentDirection == Direction.UP && !upRequests.tailSet(currentFloor, false).isEmpty()) {
                 state = ElevatorState.MOVING_UP;
             } else if (currentDirection == Direction.DOWN && !downRequests.headSet(currentFloor, false).isEmpty()) {
                 state = ElevatorState.MOVING_DOWN;
             } else {
                 if (!upRequests.isEmpty() || !downRequests.isEmpty()) {
                     currentDirection = (currentDirection == Direction.UP) ? Direction.DOWN : Direction.UP;
                     state = (currentDirection == Direction.UP) ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
                 } else {
                     state = ElevatorState.IDLE;
                     currentDirection = Direction.IDLE;
                 }
             }
        } else {
            state = ElevatorState.IDLE;
            currentDirection = Direction.IDLE;
        }
        notifyObservers();
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        if (underMaintenance) {
            state = ElevatorState.UNDER_MAINTENANCE;
            System.out.println("Elevator " + id + " is now UNDER MAINTENANCE");
        } else {
            state = ElevatorState.IDLE;
            System.out.println("Elevator " + id + " is now OPERATIONAL");
            checkAndResume();
        }
        notifyObservers();
    }

    public void updateWeight(double weight) {
        this.currentWeight = weight;
        for (Sensor sensor : sensors) {
            if (sensor instanceof WeightSensor) {
                ((WeightSensor) sensor).updateWeight(weight);
            }
        }
        if (currentWeight > maxWeight) {
            state = ElevatorState.OVERLOADED;
            door.open();
        } else if (state == ElevatorState.OVERLOADED) {
            state = ElevatorState.IDLE;
            door.close();
            checkAndResume();
        }
        notifyObservers();
    }

    public String getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }
    public Direction getCurrentDirection() { return currentDirection; }
    public InsidePanel getInsidePanel() { return insidePanel; }
}
