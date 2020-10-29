package com.ivanvasilev;

public class Main {
  public static void main(String[] args) {
    CustomMap<Integer, String> map = new CustomMap<>(3);
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.remove(2);
    map.put(5, "five");
    map.put(6, "six");
    map.put(7, "seven");
    System.out.println(map.containsKey(1));
    System.out.println(map.containsKey(10));
    System.out.println(map.containsValue("one"));
    System.out.println(map.containsValue("zero"));
    System.out.println(map.get(1));
    System.out.println(map);
    System.out.println(map.keySet());
    System.out.println(map.values());
  }
}
