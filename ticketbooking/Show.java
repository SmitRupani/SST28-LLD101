import java.util.HashMap;
import java.util.Map;

public class Show {
    String showId;
    Movie movie;
    Screen screen;
    java.time.LocalDateTime startTime;

    Map<String, ShowSeat> seats = new HashMap<>();
}