import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheBetter {

    private Map<Integer, Integer> map;

    public LRUCacheBetter(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }

    public void set(int key, int value) {
        map.put(key, value);
    }

    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            super(maximumCapacity, 0.75f, true);
            this.maximumCapacity = maximumCapacity;
        }

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }
}
