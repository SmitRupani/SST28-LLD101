import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initializing Database
        Database database = new Database();
        database.put("key1", "value1");
        database.put("key2", "value2");
        database.put("key3", "value3");
        database.put("key4", "value4");
        database.put("key5", "value5");

        // Initializing Cache Nodes (3 nodes, capacity 2 each)
        List<CacheNode> nodes = new ArrayList<>();
        nodes.add(new CacheNode("1", 2, new LRUEvictionPolicy()));
        nodes.add(new CacheNode("2", 2, new LRUEvictionPolicy()));
        nodes.add(new CacheNode("3", 2, new LRUEvictionPolicy()));

        // Distributed Cache with Modulo Strategy
        DistributedCache cache = new DistributedCache(nodes, new ModuloDistributionStrategy(), database);

        System.out.println("--- Testing Get (Cache Miss & Hit) ---");
        cache.get("key1"); // Miss, fetched from DB
        cache.get("key1"); // Hit from Cache

        System.out.println("\n--- Testing Put & Distribution ---");
        cache.put("key6", "value6");
        cache.put("key7", "value7");
        cache.put("key8", "value8");

        System.out.println("\n--- Testing Eviction ---");
        // Filling node that key1 belongs to
        // If key1 is in node 1 (hash dependent), we add more keys to node 1 to trigger eviction.
        // For demonstration, let's just put many keys.
        for (int i = 10; i < 20; i++) {
            cache.put("key" + i, "value" + i);
        }

        System.out.println("\n--- Final Retrieval ---");
        cache.get("key1"); // Might be evicted depending on distribution
        cache.get("key19"); // Recently added, should be a hit
    }
}
