public class ParkingTicket {
    String ticketId;
    Vehicle vehicle;
    ParkingSlot slot;
    long entryTime;

    ParkingTicket(String ticketId, Vehicle vehicle, ParkingSlot slot, long entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
    }
}