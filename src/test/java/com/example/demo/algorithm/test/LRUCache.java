package com.example.demo.algorithm.test;

import java.util.LinkedHashMap;

public class LRUCache {

    // 最久未使用的放在队首
    private LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();

    private int capacity;

    public LRUCache(int capacity) {
        //时间排序
        this.capacity = capacity;
    }

    public int get(int key) {
        //每次get时，需要调整该可以为最近使用
        if (!linkedHashMap.containsKey(key)) {
            return -1;
        }
        int value = linkedHashMap.remove(key);
        linkedHashMap.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
            return;
        }
        if (linkedHashMap.size() >= capacity) {
            int tempKey = linkedHashMap.keySet().iterator().next();
            linkedHashMap.remove(tempKey);
        }
        linkedHashMap.put(key, value);
    }
}