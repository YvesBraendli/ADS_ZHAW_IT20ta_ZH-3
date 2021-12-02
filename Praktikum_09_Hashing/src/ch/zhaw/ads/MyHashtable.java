import java.util.*;

public class MyHashtable<K, V> implements java.util.Map<K, V> {
    private K[] keys;
    private V[] values;
    private Object removedMarker = new Object();

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        Arrays.fill(keys, null);
        Arrays.fill(values, null);
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        if (size() >= keys.length) {
            doubleHashTableSize();
        }
        int currentHash;
        currentHash = findPos(key);
        if (currentHash == -1) {
            currentHash = getEmptyPos(key);
        }

        keys[currentHash] = key;
        values[currentHash] = value;
        return value;
    }

    private void doubleHashTableSize() {
        int tableSize = keys.length;
        K[] oldKeys = keys.clone();
        V[] oldValues = values.clone();
        clear();
        keys = (K[]) new Object[tableSize * 2];
        values = (V[]) new Object[tableSize * 2];
        rehashHashTable(oldKeys, oldValues);
    }

    private void rehashHashTable(K[] oldKeys, V[] oldValues){
        for (int i = 0; i < oldKeys.length; i++) {
            put(oldKeys[i], oldValues[i]);
        }
    }

    private int getEmptyPos(Object key) {
        int currentHash = hash(key);
        while (keys[currentHash] != null && !removedMarker.equals(keys[currentHash])) {
            currentHash++;
            currentHash %= keys.length;
        }
        return currentHash;
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        int currentHash = findPos(key);
        if (currentHash == -1) return null;
        return values[currentHash];
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return Arrays.stream(keys).allMatch(Objects::isNull);
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int currentHash = findPos(key);
        if (currentHash == -1) return null;
        V value = values[currentHash];
        keys[currentHash] = (K) removedMarker;
        values[currentHash] = null;
        return value;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return (int) Arrays.stream(keys).filter(k -> k != null && k != removedMarker).count();
    }

    private int findPos(Object x) {
        int currentHash = hash(x);
        int index = 0;
        while (keys[currentHash] != null && !keys[currentHash].equals(x)) {
            if (index >= keys.length) return -1;
            currentHash = (currentHash + 1) % keys.length;
            index++;
        }
        return keys[currentHash] != null ? currentHash : -1;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}