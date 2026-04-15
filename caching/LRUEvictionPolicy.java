import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {

    private static class Node {
        String key;
        Node prev, next;
        Node(String key) { this.key = key; }
    }

    private final Map<String, Node> map;
    private final Node head, tail;

    public LRUEvictionPolicy() {
        map = new HashMap<>();
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void keyAccessed(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
        }
    }

    @Override
    public void keyAdded(String key) {
        if (map.containsKey(key)) {
            keyAccessed(key);
        } else {
            Node newNode = new Node(key);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    @Override
    public String evict() {
        Node last = tail.prev;
        if (last == head) return null;
        removeNode(last);
        map.remove(last.key);
        return last.key;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}
