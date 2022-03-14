package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {

        int hash = hash(Objects.hash(key));


        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }

        int index = indexFor(hash);

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            return true;
        } else {
            return false;
        }
    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return Math.abs(hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4));
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {

        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        count = 0;

        for (MapEntry<K, V> map : oldTable) {
            if (map != null) {
                put(map.key, map.value);
            }
        }

    }

    @Override
    public V get(K key) {

        int hash = hash(Objects.hash(key));
        int index = indexFor(hash);

        if (table[index] == null) {
            return null;
        } else {
            return table[index].value;
        }

    }

    @Override
    public boolean remove(K key) {
        int hash = hash(Objects.hash(key));
        int index = indexFor(hash);

        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            modCount++;
            count--;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {

                while (point < capacity && table[point] == null) {
                    point++;
                }

                return point < capacity;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return table[point++].key;
            }
        };



    }
    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}