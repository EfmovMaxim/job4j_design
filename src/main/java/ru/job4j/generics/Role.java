package ru.job4j.generics;

public class Role extends Base {
    public String id;

    public Role(String id) {
        super(id);
    }

    @Override
    public String getId() {
        return id;
    }
}
