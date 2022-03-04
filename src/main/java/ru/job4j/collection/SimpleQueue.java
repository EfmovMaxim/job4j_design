package ru.job4j.collection;

import java.util.Iterator;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        Iterator<T> iteratorOut = out.iterator();

        if (!iteratorOut.hasNext()) {
            Iterator<T> iteratorIn = in.iterator();
            while (iteratorIn.hasNext()) {
                out.push(iteratorIn.next());
                in.pop();
            }
        }

        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}