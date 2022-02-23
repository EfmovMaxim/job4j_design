package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int modCount, size;
    Node<E> first, last;

    @Override
    public void add(E value) {
        Node<E> oldLast = last;
        last = new Node<E>(oldLast, value, null);

        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }

        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Iterator<E> iterator = iterator();
        E rsl = null;
        for (int i = 0; i <= index; i++) {
            rsl = iterator.next();
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> point = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node<E> tempPoint = point;
                point = point.next;
                return tempPoint.value;
            }
        };
    }

    private static class Node<E> {
        public E value;
        public Node<E> next, prev;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
