package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Ivan", 1, calendar);

//        Map<User, Object> map = new HashMap<>();
//        map.put(user1, new Object());
//        map.put(user2, new Object());
//
//        map.entrySet().stream().forEach(System.out::println);
//        map.entrySet().stream().forEach(i -> System.out.println(i.getKey().hashCode()));



    }
}
