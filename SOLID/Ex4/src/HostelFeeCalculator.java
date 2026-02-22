import java.util.Random;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomTypeMapper roomRegistry;
    private final AddOnMapper addOnRegistry;

    public HostelFeeCalculator(FakeBookingRepo repo,
                               RoomTypeMapper roomRegistry,
                                AddOnMapper addOnRegistry) {
        this.repo = repo;
        this.roomRegistry = roomRegistry;
        this.addOnRegistry = addOnRegistry;
    }

    public void process(BookingRequest req) {
        RoomType room = roomRegistry.resolve(req.roomType);

        Money monthly = room.basePrice();
        for (AddOn addOn : req.addOns) {
            monthly = monthly.plus(addOnRegistry.priceFor(addOn));
        }

        Money deposit = room.deposit();

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}