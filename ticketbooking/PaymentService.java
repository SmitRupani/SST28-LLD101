public class PaymentService {

    void makePayment(Booking booking) {

        for (ShowSeat seat : booking.seats) {
            if (SeatUtil.isExpired(seat)) {
                booking.status = BookingStatus.EXPIRED;
                throw new RuntimeException("Booking expired");
            }
        }

        // simulate payment success

        for (ShowSeat seat : booking.seats) {
            seat.status = SeatStatus.BOOKED;
        }

        booking.status = BookingStatus.CONFIRMED;
    }
}