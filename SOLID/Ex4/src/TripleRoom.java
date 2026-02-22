public class TripleRoom implements RoomType {
    @Override public int type() { return LegacyRoomTypes.TRIPLE; }
    @Override public Money basePrice() { return new Money(12000.0); }
    @Override public Money deposit() { return new Money(5000.0); }
}