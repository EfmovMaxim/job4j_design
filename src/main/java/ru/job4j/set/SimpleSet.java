package ru.job4j.set;
import ru.job4j.collection.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);

        if (rsl) {
            set.add(value);
        }

        return rsl;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iterator = set.iterator();

        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}