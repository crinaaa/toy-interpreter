package interpreter.model.adt.dictionary;

import interpreter.exceptions.ADTException;

import java.util.Map;

public interface IDictionary<K,V> {
    void put(K key, V value);
    void update(K key, V value) throws ADTException;
    V lookup(K key) throws ADTException;
    boolean isDefined(K key);
    void remove(K key) throws ADTException;
    Map<K, V> getContent();
    String toString();
    Map<K, V> toMap();
}
