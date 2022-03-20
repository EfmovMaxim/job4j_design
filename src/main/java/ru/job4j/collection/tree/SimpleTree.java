package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<E>(root);
    }

    @Override
    public boolean add(E parent, E child) {
       Optional<Node<E>> parentNode = findBy(parent);
       if (parentNode.isEmpty() || !findBy(child).isEmpty()) {
           return false;
       }

       parentNode.get().children.add(new Node<>(child));

       return true;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {

        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;

    }

    public boolean isBinary() {
        return findByPredicate(v -> v.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(v -> v.equals(new Node<>(value)));
    }
}
