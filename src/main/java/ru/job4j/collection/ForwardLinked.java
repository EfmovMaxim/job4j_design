package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node(value, null);

        if (head == null) {
            head = node;
            return;
        }

        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        T rsl = head.value;
        head = head.next;

        return rsl;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }

        Node<T> point = head;
        Node<T> newNext = null, oldNext;

        do {
            oldNext = point.next;
            point.next = newNext;
            newNext = point;
            point = oldNext;

        } while (oldNext != null);

        head = newNext;

        return true;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ForwardLinked.Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
