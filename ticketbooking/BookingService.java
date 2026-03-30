import java.util.ArrayList;
import java.util.List;

public class BookingService {

    PricingService pricingService = new PricingService();

    Booking createBooking(Show show, List<String> seatIds) {

        List<ShowSeat> selectedSeats = new ArrayList<>();

        for (String seatId : seatIds) {

            ShowSeat seat = show.seats.get(seatId);

            SeatUtil.refreshSeat(seat);

            if (seat.status != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat not available");
            }

            seat.status = SeatStatus.LOCKED;
            seat.lockTime = System.currentTimeMillis();

            selectedSeats.add(seat);
        }

        Booking booking = new Booking();
        booking.bookingId = java.util.UUID.randomUUID().toString();
        booking.show = show;
        booking.seats = selectedSeats;
        booking.status = BookingStatus.PAYMENT_PENDING;

        booking.totalAmount =
                pricingService.calculateTotal(show, selectedSeats);

        return booking;
    }

    void cancelBooking(Booking booking) {
        for (ShowSeat seat : booking.seats) {
            seat.status = SeatStatus.AVAILABLE;
        }
        booking.status = BookingStatus.CANCELLED;
    }
}