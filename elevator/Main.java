public class Main {
    public static void main(String[] args) {
        System.out.println("=== Elevator System Comprehensive Simulation ===\n");

        ElevatorManager manager = new ElevatorManager(10, 3, 700.0, new NearestElevatorStrategy());
        List<Elevator> elevators = manager.getElevators();

        printStatus(elevators);

        System.out.println("\n--- Scenario 1: Multiple Outside Requests ---");
        manager.handleOutsideRequest(new Request(3, Direction.UP, RequestType.OUTSIDE));
        manager.handleOutsideRequest(new Request(7, Direction.DOWN, RequestType.OUTSIDE));
        manager.handleOutsideRequest(new Request(1, Direction.UP, RequestType.OUTSIDE));
        
        simulateTicks(manager, 5);

        System.out.println("\n--- Scenario 2: Inside Requests & Concurrency ---");
        elevators.get(0).addInsideRequest(9);
        elevators.get(1).addInsideRequest(0);
        manager.handleOutsideRequest(new Request(5, Direction.UP, RequestType.OUTSIDE));

        simulateTicks(manager, 10);

        System.out.println(" Scenario 3: Maintenance Mode");
        System.out.println("Taking Elevator 1 (E1) out for maintenance.");
        elevators.get(0).setUnderMaintenance(true);
        manager.handleOutsideRequest(new Request(8, Direction.DOWN, RequestType.OUTSIDE));

        simulateTicks(manager, 5);
        elevators.get(0).setUnderMaintenance(false);

        System.out.println("Scenario 4: Overload Protection ---");
        Elevator e3 = elevators.get(2);
        System.out.println("E3 detects overload (800kg > 700kg limit)");
        e3.updateWeight(800.0);
        e3.addInsideRequest(4);
        e3.move();
        
        System.out.println("Shedding weight to 600kg...");
        e3.updateWeight(600.0);
        simulateTicks(manager, 5);

        System.out.println("\n--- Scenario 5: Emergency Alarm ---");
        System.out.println("Triggering emergency alarm in E2");
        elevators.get(1).getInsidePanel().getAlarmButton().press();

        System.out.println("\nSimulation Finished.");
    }

    private static void simulateTicks(ElevatorManager manager, int ticks) {
        for (int i = 0; i < ticks; i++) {
            for (Elevator e : manager.getElevators()) {
                e.move();
            }
        }
        printStatus(manager.getElevators());
    }

    private static void printStatus(List<Elevator> elevators) {
        System.out.println("\n+" + "-".repeat(10) + "+" + "-".repeat(10) + "+" + "-".repeat(15) + "+" + "-".repeat(12) + "+");
        System.out.printf("| %-8s | %-8s | %-13s | %-10s |\n", "Elevator", "Floor", "State", "Direction");
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(10) + "+" + "-".repeat(15) + "+" + "-".repeat(12) + "+");
        for (Elevator e : elevators) {
            System.out.printf("| %-8s | %-8s | %-13s | %-10s |\n", 
                e.getId(), e.getCurrentFloor(), e.getState(), e.getCurrentDirection());
        }
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(10) + "+" + "-".repeat(15) + "+" + "-".repeat(12) + "+");
    }
}
