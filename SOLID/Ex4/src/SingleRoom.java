public class SingleRoom implements RoomType {
    @Override public int type() { return LegacyRoomTypes.SINGLE; }
    @Override public Money basePrice() { return new Money(14000.0); }
    @Override public Money deposit() { return new Money(5000.0); }
}