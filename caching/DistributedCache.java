import java.util.List;

public class DistributedCache {
    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database database;

    public DistributedCache(List<CacheNode> nodes, DistributionStrategy distributionStrategy, Database database) {
        this.nodes = nodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    public String get(String key) {
        int nodeIndex = distributionStrategy.selectNode(key, nodes.size());
        CacheNode node = nodes.get(nodeIndex);
        String value = node.get(key);

        if (value != null) {
            System.out.println("[Cache] Hit on node " + node.getId() + " for key: " + key);
            return value;
        }

        System.out.println("[Cache] Miss for key: " + key);
        value = database.get(key);
        if (value != null) {
            System.out.println("[Cache] Storing key " + key + " in node " + node.getId());
            node.put(key, value);
        }
        return value;
    }

    public void put(String key, String value) {
        int nodeIndex = distributionStrategy.selectNode(key, nodes.size());
        CacheNode node = nodes.get(nodeIndex);
        System.out.println("[Cache] Putting key " + key + " in node " + node.getId());
        node.put(key, value);
        // Assuming DB is updated as well
        database.put(key, value);
    }
}
