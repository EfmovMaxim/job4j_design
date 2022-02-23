package ru.job4j.collection;

public interface List<T> extends Iterable {
    void add(T value);
    T set(int index, T newValue);
    T get(int index);
    T remove(int index);
    int size();
}
