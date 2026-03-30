public class SeatUtil {

    static final long LOCK_TIMEOUT = 5 * 60 * 1000;

    static boolean isExpired(ShowSeat seat) {
        return seat.status == SeatStatus.LOCKED &&
                System.currentTimeMillis() - seat.lockTime > LOCK_TIMEOUT;
    }

    static void refreshSeat(ShowSeat seat) {
        if (isExpired(seat)) {
            seat.status = SeatStatus.AVAILABLE;
        }
    }
}