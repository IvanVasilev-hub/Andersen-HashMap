package com.ivanvasilev;

public class Entry<K, V> {
  private final K key;
  private V value;
  private Entry<K, V> next = null;
  private Entry<K, V> prev = null;

  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public Entry<K, V> getNext() {
    return next;
  }

  public void setNext(Entry<K, V> next) {
    this.next = next;
  }

  public Entry<K, V> getPrev() {
    return prev;
  }

  public void setPrev(Entry<K, V> prev) {
    this.prev = prev;
  }

  @Override
  public boolean equals(Object obj) {
    Entry<K, V> entry = (Entry<K, V>) obj;
    if (key == null && entry.key == null) {
      return value.equals(entry.value);
    }
    if (key == null || entry.key == null) {
      return false;
    }
    return key.equals(entry.key) && value.equals(entry.value);
  }
}
