import java.util.Map;

public class RoomTypeMapper {
    private final Map<Integer, RoomType> rooms;
    private final RoomType defaultRoom;

    public RoomTypeMapper(Map<Integer, RoomType> rooms, RoomType defaultRoom) {
        this.rooms = rooms;
        this.defaultRoom = defaultRoom;
    }

    public RoomType resolve(int roomType) {
        return rooms.getOrDefault(roomType, defaultRoom);
    }
}