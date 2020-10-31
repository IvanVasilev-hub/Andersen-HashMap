package com.ivanvasilev;

public class Main {
  public static void main(String[] args) {
    CustomMap<Integer, String> map = new CustomMap<>(10);
    map.put(null, "Null");
    map.put(1, "one");
    map.put(11, "eleven");
    map.put(21, "twenty-one");
    map.put(31, "31");
//    System.out.println(map);
//    System.out.println(map.size());
//    map.remove(21);
//    map.remove(11);
//    System.out.println(map);
//    System.out.println(map.size());
    System.out.println(map.keySet());
    System.out.println(map.values());
    map.put(null, "Anther");
    for (Entry<Integer, String> entry : map.entrySet()) {
      System.out.println("Key = " + entry.getKey() + " value = " + entry.getValue());
    }
    System.out.println(map.containsKey(1));
    System.out.println(map.containsKey(100));
//    System.out.println(map.get(null));
//    map.put(2, "two");
//    map.put(3, "three");
//    map.put(null, "another null");
//    System.out.println(map.size());
//    map.put(4, "four");
//    map.remove(2);
//    map.put(5, "five");
//    map.put(6, "six");
//    System.out.println(map.get(1));
//    System.out.println(map.get(6));
//    map.put(7, "seven");
//    System.out.println(map);
//    System.out.println(map.containsKey(1));
//    System.out.println(map.containsKey(10));
//    System.out.println(map.get(1));
//    System.out.println("SIZE: " + map.size());
//    System.out.println(map);
//    System.out.println(map.keySet());
//    System.out.println(map.values());
//    map.put(10, "ten");
//    System.out.println(map);
//    System.out.println(map.size());
//    System.out.println(new Entry<Integer, String>(1, "null").equals(new Entry<>(1, "null")));
  }
}
