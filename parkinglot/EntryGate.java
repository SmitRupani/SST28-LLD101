import java.util.Comparator;
import java.util.TreeSet;

public class EntryGate {
    String gateId;

    TreeSet<GateSlotWrapper> smallSlots;
    TreeSet<GateSlotWrapper> mediumSlots;
    TreeSet<GateSlotWrapper> largeSlots;

    EntryGate(String gateId) {
        this.gateId = gateId;

        Comparator<GateSlotWrapper> comparator = (a, b) -> {
            if (a.distance != b.distance) {
                return a.distance - b.distance;
            }
            return a.slot.slotId.compareTo(b.slot.slotId);
        };

        smallSlots = new TreeSet<>(comparator);
        mediumSlots = new TreeSet<>(comparator);
        largeSlots = new TreeSet<>(comparator);
    }

    TreeSet<GateSlotWrapper> getSet(SlotType type) {
        switch (type) {
            case SMALL: return smallSlots;
            case MEDIUM: return mediumSlots;
            case LARGE: return largeSlots;
        }
        return null;
    }
}