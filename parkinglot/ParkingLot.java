import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

public class ParkingLot {

    Map<String, EntryGate> gates = new HashMap<>();

    // gateId -> (slotId -> wrapper)
    Map<String, Map<String, GateSlotWrapper>> gateSlotMap = new HashMap<>();

    Map<String, ParkingTicket> activeTickets = new HashMap<>();

    PricingStrategy pricingStrategy = new HourlyPricingStrategy();

    // ---------------- PARK ----------------
    public ParkingTicket park(Vehicle vehicle, long entryTime, String gateId) {
        EntryGate gate = gates.get(gateId);

        GateSlotWrapper wrapper = allocateSlot(vehicle, gate);

        if (wrapper == null) {
            throw new RuntimeException("No slots available");
        }

        String ticketId = UUID.randomUUID().toString();

        ParkingTicket ticket = new ParkingTicket(
                ticketId,
                vehicle,
                wrapper.slot,
                entryTime
        );

        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    // ---------------- ALLOCATION ----------------
    private GateSlotWrapper allocateSlot(Vehicle vehicle, EntryGate gate) {
        if (null == vehicle.type) {
            return getSlot(gate.largeSlots);
        } else return switch (vehicle.type) {
            case BIKE -> getSlot(gate.smallSlots, gate.mediumSlots, gate.largeSlots);
            case CAR -> getSlot(gate.mediumSlots, gate.largeSlots);
            default -> getSlot(gate.largeSlots);
        };
    }

    private GateSlotWrapper getSlot(TreeSet<GateSlotWrapper>... sets) {
        for (TreeSet<GateSlotWrapper> set : sets) {
            if (!set.isEmpty()) {
                GateSlotWrapper wrapper = set.first();
                set.remove(wrapper);
                return wrapper;
            }
        }
        return null;
    }

    // ---------------- EXIT ----------------
    public double exit(String ticketId, long exitTime) {
        ParkingTicket ticket = activeTickets.remove(ticketId);

        if (ticket == null) {
            throw new RuntimeException("Invalid ticket");
        }

        double amount = pricingStrategy.calculate(
                ticket.entryTime,
                exitTime,
                ticket.slot.type
        );

        freeSlot(ticket.slot);

        return amount;
    }

    // ---------------- FREE SLOT ----------------
    private void freeSlot(ParkingSlot slot) {
        for (EntryGate gate : gates.values()) {
            GateSlotWrapper wrapper =
                    gateSlotMap.get(gate.gateId).get(slot.slotId);

            addBack(gate, wrapper);
        }
    }

    private void addBack(EntryGate gate, GateSlotWrapper wrapper) {
        switch (wrapper.slot.type) {
            case SMALL -> gate.smallSlots.add(wrapper);
            case MEDIUM -> gate.mediumSlots.add(wrapper);
            case LARGE -> gate.largeSlots.add(wrapper);
        }
    }

    // ---------------- STATUS ----------------
    public void status() {
        for (String gateId : gates.keySet()) {
            EntryGate gate = gates.get(gateId);

            System.out.println("Gate: " + gateId);
            System.out.println("Small: " + gate.smallSlots.size());
            System.out.println("Medium: " + gate.mediumSlots.size());
            System.out.println("Large: " + gate.largeSlots.size());
        }
    }
}