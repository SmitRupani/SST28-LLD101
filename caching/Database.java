import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, String> data = new HashMap<>();

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        System.out.println("[DB] Fetching key: " + key);
        return data.get(key);
    }
}
