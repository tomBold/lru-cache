import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K, V> {
    private Map<K, V> map;
    private final int CAPACITY;

    public LRUCacheLinkedHashMap(int capacity) {
        CAPACITY = capacity;
        map =  Collections.synchronizedMap(new LinkedHashMap<K,V>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        });
    }
    public V get(K key) {
        return map.getOrDefault(key, null);
    }
    public void set(K key, V value) {
        map.put(key, value);
    }
}
