import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int CACHE_SIZE;
    private final Map<K, Node<K, V>> cache = new HashMap<>();
    private Node<K, V> head, end;

    public LRUCache(int cache_size) {
        CACHE_SIZE = cache_size;
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            remove(n);
            setHead(n);
            return (V) n.value;
        }

        return null;
    }

    private void remove(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.prev = n.prev;
        } else {
            end = n.prev;
        }
    }

    private void setHead(Node n) {
        n.next = head;
        n.prev = null;

        if (head != null) {
            head.prev = n;
        }

        head = n;

        if (end == null) {
            end = head;
        }
    }

    public void set(K key, V value) {
        if (cache.containsKey(key)) {
            Node old = cache.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (cache.size() >= CACHE_SIZE) {
                cache.remove(end.key);
                remove(end);
                setHead(created);

            } else {
                setHead(created);
            }

            cache.put(key, created);
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
