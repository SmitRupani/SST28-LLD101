import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private final String id;
    private final int capacity;
    private final Map<String, String> storage;
    private final EvictionPolicy evictionPolicy;

    public CacheNode(String id, int capacity, EvictionPolicy evictionPolicy) {
        this.id = id;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        if (!storage.containsKey(key)) return null;
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public void put(String key, String value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            String evictedKey = evictionPolicy.evict();
            if (evictedKey != null) {
                storage.remove(evictedKey);
                System.out.println("[Node " + id + "] Evicted key: " + evictedKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAdded(key);
    }

    public String getId() { return id; }
}
