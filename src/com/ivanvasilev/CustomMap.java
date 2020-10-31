package com.ivanvasilev;

import java.util.HashSet;
import java.util.Set;

public class CustomMap<K, V> {
  private int size = 0;
  private final int DEFAULT_CAPACITY = 16;
  private int capacity;
  private final float loadFactor = 0.75f;
  private Entry<K, V>[] table;

  public CustomMap() {
    capacity = DEFAULT_CAPACITY;
    table = new Entry[DEFAULT_CAPACITY];
  }

  public CustomMap(int capacity) {
    this.capacity = capacity;
    table = new Entry[capacity];
  }

  public void put(K key, V value) {
    if (size + 1 > capacity * loadFactor) {
      rehash();
    }
    int index = getHash(key);
    Entry<K, V> currEntry = table[index];
    if (currEntry == null) {
      size++;
      table[index] = new Entry<>(key, value);
    } else {
      Entry<K, V> entryWithKey = chainContainsKey(currEntry, key);
      if (entryWithKey == null) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.setNext(currEntry);
        currEntry.setPrev(newEntry);
        table[index] = newEntry;
        size++;
      } else {
        entryWithKey.setValue(value);
      }
    }
  }

  private void putEntry(Entry<K, V> entry) {
    if (entry == null) {
      return;
    }
    put(entry.getKey(), entry.getValue());
  }

  private Entry<K, V> chainContainsKey(Entry<K, V> entry, K key) {
    if (key == null) {
      return entry;
    }
    return getEntryInChain(entry, key);
  }

  private int getHash(K key) {
    if (key == null) {
      return 0;
    }
    int hash = Math.abs(key.hashCode()) % capacity;
    return hash == 0 ? 1 : hash;
//    while (!(table[hash] == null || table[hash].getKey().equals(key))) {
//      hash = (hash + 1) % capacity;
//      hash = hash == 0 ? 1 : hash;
//      if (hash == key.hashCode() % capacity && table[hash] != null) {
//        return hash;
//      } else {
//        return -1;
//      }
//    }
  }

  private void rehash() {
    size = 0;
    Entry<K, V>[] buffer = table;
    capacity *= 2;
    table = new Entry[capacity];
    for (Entry<K, V> entry : buffer) {
      putEntry(entry);
      if (entry != null) {
        Entry<K, V> nextEntry = entry.getNext();
        while (nextEntry != null) {
          putEntry(nextEntry);
          nextEntry = nextEntry.getNext();
        }
      }
    }
  }

  public V get(K key) {
    Entry<K, V> entry = findEntry(key);
    return entry == null ? null : entry.getValue();
  }

  public boolean remove(K key) {
    int index = getHash(key);
    Entry<K, V> entry = findEntry(key);
    if (entry != null) {
      if (table[index] == entry && entry.getNext() == null) {
        table[index] = null;
        size--;
        return true;
      }
      Entry<K, V> prevEntry = entry.getPrev();
      Entry<K, V> nextEntry = entry.getNext();
      if (prevEntry != null && nextEntry != null) {
        prevEntry.setNext(nextEntry);
        nextEntry.setPrev(prevEntry);
        size--;
        return true;
      }
      if (prevEntry == null && nextEntry != null) {
        nextEntry.setPrev(null);
        table[index] = nextEntry;
        size--;
        return true;
      }
      if (prevEntry != null && nextEntry == null) {
        prevEntry.setNext(null);
        size--;
      }
    }
    return false;
  }

  private Entry<K, V> findEntry(K key) {
    if (key == null && table[0] != null) {
      return table[0];
    }
    int index = getHash(key);
    Entry<K, V> entry = table[index];
    if (entry != null) {
      Entry<K, V> nextEntry = getEntryInChain(entry, key);
      return nextEntry;
    }
    return null;
  }

  private Entry<K, V> getEntryInChain(Entry<K, V> entry, K key) {
    if (entry.getKey().equals(key)) {
      return entry;
    }
    Entry<K, V> nextEntry = entry.getNext();
    while (nextEntry != null) {
      if (nextEntry.getKey().equals(key)) {
        return nextEntry;
      }
      nextEntry = nextEntry.getNext();
    }
    return null;
  }

  public Set<K> keySet() {
    Set<K> set = new HashSet<>();
    for (int i = 0; i < capacity; i++) {
      Entry<K, V> entry = table[i];
      if (entry != null) {
        set.add(entry.getKey());
        Entry<K, V> nextEntry = entry.getNext();
        while (nextEntry != null) {
          set.add(nextEntry.getKey());
          nextEntry = nextEntry.getNext();
        }
      }
    }
    return set;
  }

  public Set<V> values() {
    Set<V> set = new HashSet<>();
    for (int i = 0; i < capacity; i++) {
      Entry<K, V> entry = table[i];
      if (entry != null) {
        set.add(entry.getValue());
        Entry<K, V> nextEntry = entry.getNext();
        while (nextEntry != null) {
          set.add(nextEntry.getValue());
          nextEntry = nextEntry.getNext();
        }
      }
    }
    return set;
  }

  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> set = new HashSet<>();
    for (int i = 0; i < capacity; i++) {
      Entry<K, V> entry = table[i];
      if (entry != null) {
        set.add(entry);
        Entry<K, V> nextEntry = entry.getNext();
        while (nextEntry != null) {
          set.add(nextEntry);
          nextEntry = nextEntry.getNext();
        }
      }
    }
    return set;
  }

  public boolean containsKey(K key) {
    return findEntry(key) != null;
  }

  public int size() {
    return size;
  }

  public int capacity() {
    return capacity;
  }

//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < capacity; i++) {
//      if (table[i] == null) {
//        sb.append(i + " : null\n");
//      } else {
//        sb.append(i +
//            " : key = " + table[i].getKey() +
//            ", value = " + table[i].getValue() + "\n"
//        );
//      }
//    }
//    return sb.toString();
//  }
}
