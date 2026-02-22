public class DoubleRoom implements RoomType {
    @Override public int type() { return LegacyRoomTypes.DOUBLE; }
    @Override public Money basePrice() { return new Money(15000.0); }
    @Override public Money deposit() { return new Money(5000.0); }
}