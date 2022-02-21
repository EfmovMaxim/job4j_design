package ru.job4j.generics;

public class User extends Base {
    private String id;

    public User(String id) {
        super(id);
    }

    @Override
    public String getId() {
        return id;
    }
}
