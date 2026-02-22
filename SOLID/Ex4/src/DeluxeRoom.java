public class DeluxeRoom implements RoomType {
    @Override public int type() { return LegacyRoomTypes.DELUXE; }
    @Override public Money basePrice() { return new Money(16000.0); }
    @Override public Money deposit() { return new Money(5000.0); }
}