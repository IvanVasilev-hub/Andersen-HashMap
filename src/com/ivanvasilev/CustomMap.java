package com.ivanvasilev;

import java.util.HashSet;
import java.util.Set;

public class CustomMap<K, V> {
  private int size;
  private final int DEFAULT_SIZE = 16;
  private MapEntry<K, V>[] table;

  public CustomMap() {
    size = DEFAULT_SIZE;
    table = new MapEntry[DEFAULT_SIZE];
  }

  public CustomMap(int size) {
    this.size = size;
    table = new MapEntry[size];
  }

  public void put(K key, V value) {
    if (key == null) {
      throw new NullPointerException();
    }
    int index = findKey(key);
    if (index == -1) {
      rehash();
      index = findKey(key);
    }
    table[index] = new MapEntry<>(key, value);
  }

  private void putEntry(MapEntry<K, V> entry) {
    if (entry == null) {
      return;
    }
    put(entry.getKey(), entry.getValue());
  }

  private int findKey(K key) {
    int hash = Math.abs(key.hashCode()) % size;
    while (!(table[hash] == null || table[hash].getKey().equals(key))) {
      hash = (hash + 1) % size;
      if (hash == key.hashCode() % size) {
        return -1;
      }
    }
    return hash;
  }

  private void rehash() {
    MapEntry<K, V>[] buffer = table;
    size *= 2;
    table = new MapEntry[size];
    for (MapEntry<K, V> entry : buffer) {
      putEntry(entry);
    }
  }

  public V get(K key) {
    if (key == null) {
      throw new NullPointerException();
    }
    int index = findKey(key);
    if (index == -1) {
      return null;
    }
    return table[index].getValue();
  }

  public void remove(K key) {
    int index = findKey(key);
    if (index != -1 && table[index] != null) {
      table[index] = null;
    }
  }

  public Set<K> keySet() {
    Set<K> set = new HashSet<>();
    for (int i = 0; i < size; i++) {
      MapEntry<K, V> entry = table[i];
      if (entry != null) {
        set.add(table[i].getKey());
      }
    }
    return set;
  }

  public Set<V> values() {
    Set<V> set = new HashSet<>();
    for (int i = 0; i < size; i++) {
      MapEntry<K, V> entry = table[i];
      if (entry != null) {
        set.add(table[i].getValue());
      }
    }
    return set;
  }

  public boolean containsKey(K key) {
    int index = findKey(key);
    if (index != -1) {
      return table[index] == null ? false : table[index].getKey() == key;
    }
    return false;
  }

  public boolean containsValue(V value) {
    for (int i = 0; i < size; i++) {
      if (table[i] != null && table[i].getValue().equals(value)) {
        return true;
      }
    }
    return false;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      if (table[i] == null) {
        sb.append(i + " : null\n");
      } else {
        sb.append(i +
            " : key = " + table[i].getKey() +
            ", value = " + table[i].getValue() + "\n"
        );
      }
    }
    return sb.toString();
  }
}
