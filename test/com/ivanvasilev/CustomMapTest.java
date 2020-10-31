package com.ivanvasilev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomMapTest {
  public static CustomMap<Integer, String> map = new CustomMap<>(10);

  @BeforeEach
  public void fillMap() {
    map = new CustomMap<>(10);
    map.put(null, "null");
    map.put(1, "1");
    map.put(2, "2");
    map.put(3, "3");
    map.put(4, "4");
    map.put(11, "11");
    map.put(21, "21");
  }

  @Test
  public void testGet() {
    assertEquals("1", map.get(1));
    assertNull(map.get(100));
    assertNotEquals("3", map.get(2));
  }

  @Test
  public void testRemove() {
    assertTrue(map.remove(2));
    assertFalse(map.remove(100));
    assertTrue(map.remove(21));
    assertNull(map.get(2));
  }

  @Test
  public void testSize() {
    assertEquals(map.size(), 7);
  }

  @Test
  public void testContainsKey() {
    assertTrue(map.containsKey(1));
    assertFalse(map.containsKey(100));
  }

  @Test
  public void testCapacity() {
    map.put(8, "8");
    assertEquals(map.capacity(), 20);
  }


}
