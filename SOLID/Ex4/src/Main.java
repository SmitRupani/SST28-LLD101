import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        RoomTypeMapper roomRegistry = new RoomTypeMapper(
            Map.of(
                LegacyRoomTypes.SINGLE, new SingleRoom(),
                LegacyRoomTypes.DOUBLE, new DoubleRoom(),
                LegacyRoomTypes.TRIPLE, new TripleRoom()
            ),
            new DeluxeRoom()  // default fallback
        );

        AddOnMapper addOnRegistry = new AddOnMapper(
            Map.of(
                AddOn.MESS,    new MessAddOn(),
                AddOn.LAUNDRY, new LaundryAddOn(),
                AddOn.GYM,     new GymAddOn()
            )
        );

        HostelFeeCalculator calc = new HostelFeeCalculator(
            new FakeBookingRepo(), roomRegistry, addOnRegistry
        );

        BookingRequest req = new BookingRequest(
            LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS)
        );

        calc.process(req);
    }
}