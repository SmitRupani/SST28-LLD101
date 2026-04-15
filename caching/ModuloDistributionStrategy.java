public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public int selectNode(String key, int numNodes) {
        if (numNodes <= 0) return -1;
        return Math.abs(key.hashCode()) % numNodes;
    }
}
