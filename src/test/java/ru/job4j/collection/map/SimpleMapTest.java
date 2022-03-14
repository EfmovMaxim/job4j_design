package ru.job4j.collection.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        SimpleMap<User, Integer> map = new SimpleMap<>();
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Anton", 1, calendar);

        map.put(user1, 10);
        map.put(user2, 20);

        assertThat(map.get(user1), is(10));
        assertThat(map.get(user2), is(20));

    }

    @Test
    public void whenPutAndRemove() {
        SimpleMap<User, Integer> map = new SimpleMap<>();
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Anton", 1, calendar);

        map.put(user1, 10);
        map.put(user2, 20);
        map.remove(user2);

        Assert.assertNull(map.get(user2));
        Assert.assertThat(10, is(map.get(user1)));


    }

    @Test
    public void whenExpand() {
        SimpleMap<User, Integer> map = new SimpleMap<>();
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Anton", 1, calendar);
        User user3 = new User("Petr", 1, calendar);
        User user4 = new User("Vasya", 1, calendar);
        User user5 = new User("Maxim", 1, calendar);
        User user6 = new User("Vladimir", 1, calendar);
        User user7 = new User("Elena", 1, calendar);

        map.put(user1, 10);
        map.put(user2, 20);
        map.put(user3, 30);
        map.put(user4, 40);
        map.put(user5, 50);
        map.put(user6, 60);
        map.put(user7, 70);

        Assert.assertThat(20, is(map.get(user2)));
    }

    @Test
    public void whenIterator() {
        SimpleMap<User, Integer> map = new SimpleMap<>();
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Anton", 1, calendar);

        map.put(user1, 10);
        map.put(user2, 20);

        Iterator<User> iterator = map.iterator();
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
    }
}